package com.aleksieienko.brainfuck.compiler;

import com.aleksieienko.brainfuck.compiler.commands.Command;
import com.aleksieienko.brainfuck.compiler.commands.CycleCommand;
import com.aleksieienko.brainfuck.compiler.commands.DecrementCommand;
import com.aleksieienko.brainfuck.compiler.commands.IncrementCommand;
import com.aleksieienko.brainfuck.compiler.commands.NextCellCommand;
import com.aleksieienko.brainfuck.compiler.commands.PrevCellCommand;
import com.aleksieienko.brainfuck.compiler.commands.PrintCommand;
import com.aleksieienko.brainfuck.compiler.visitor.Visitor;
import com.aleksieienko.brainfuck.compiler.visitor.impl.PrintToJavaVisitor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CompilerToClass {
    private Map<Character,Command> commands;
    private String resultClassName;
    private File file;

    {
        commands = new HashMap<>();
        commands.put('>', new NextCellCommand());
        commands.put('<', new PrevCellCommand());
        commands.put('-', new DecrementCommand());
        commands.put('+', new IncrementCommand());
        commands.put('[', new CycleCommand());
        commands.put('.', new PrintCommand());
    }

    public CompilerToClass(String resultClassName) throws IOException {
        this.resultClassName = resultClassName;
        file = new File( "src/main/java/com/aleksieienko/brainfuck/compiler/result/" + resultClassName + ".java");
        file.createNewFile();
    }

    public CompilerToClass() throws IOException {
        resultClassName = "ProgrammNumber" + ((long)new Random().nextInt() + (1L<<31));
        file = new File( "src/main/java/com/aleksieienko/brainfuck/compiler/result/" + resultClassName + ".java");
        file.createNewFile();
    }

    public File getFile() {
        return file;
    }

    public String getResultClassName() {
        return resultClassName;
    }

    public void setResultClassName(String resultClassName) {
        this.resultClassName = resultClassName;
    }

    public boolean compile(Reader in) throws IOException {
        if(file.length() != 0)
        {
            return false;
        }

        try(Writer out = new BufferedWriter(new FileWriter(file))){
            header(out);
            Visitor visitor = new PrintToJavaVisitor();
            for(int chr; (chr = in.read()) != '\n';) {
                if (chr == '[') {
                    Command command;
                    if((command = cycleCompile(in)) == null){
                        footer(out);
                        return false;
                    } else {
                        out.write("\t\tcommands.add(" + command.accept(visitor) + ");\n");
                    }
                } else {
                    if(commands.get((char)chr) == null) {
                        footer(out);
                        return false;
                    } else {
                        out.write("\t\tcommands.add(" + commands.get((char) chr).accept(visitor) + ");\n");
                    }
                }
            }
            footer(out);
        }

        return true;
    }

    private CycleCommand cycleCompile(Reader in) throws IOException {
        CycleCommand command = new CycleCommand();
        for(int chr;(chr = in.read()) != ']';){
            if(chr == '[') {
                Command c;
                if((c = cycleCompile(in)) == null) {
                    return null;
                } else {
                    command.add(c);
                }
            } else {
                if(commands.get((char)chr) == null) {
                    return null;
                } else {
                    command.add(commands.get((char) chr));
                }
            }
        }
        return command;
    }

    private void header(Writer out) throws IOException {
        out.write("package com.aleksieienko.brainfuck.compiler.result;\n" +
                "\n" +
                "import com.aleksieienko.brainfuck.compiler.data.Data;\n" +
                "import com.aleksieienko.brainfuck.compiler.commands.Command;\n" +
                "import com.aleksieienko.brainfuck.compiler.commands.CycleCommand;\n" +
                "import com.aleksieienko.brainfuck.compiler.commands.DecrementCommand;\n" +
                "import com.aleksieienko.brainfuck.compiler.commands.IncrementCommand;\n" +
                "import com.aleksieienko.brainfuck.compiler.commands.NextCellCommand;\n" +
                "import com.aleksieienko.brainfuck.compiler.commands.PrevCellCommand;\n" +
                "import com.aleksieienko.brainfuck.compiler.commands.PrintCommand;\n" +
                "import java.util.Arrays;\n" +
                "import java.util.LinkedList;\n" +
                "import java.util.Queue;\n\n" +
                "@SuppressWarnings(\"unchecked\")\n" +
                "public class " + resultClassName + "{\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\t\tData data = new Data();\n" +
                "\t\tQueue<Command> commands = new LinkedList();\n");
    }

    private void footer(Writer out) throws IOException {
        out.write("\t\tCommand command;\n" +
                    "\t\twhile((command = commands.poll()) != null) {\n" +
                    "\t\t\tcommand.execute(data);\n" +
                    "\t\t}\n" +
                    "\t}\n}");
    }
}
