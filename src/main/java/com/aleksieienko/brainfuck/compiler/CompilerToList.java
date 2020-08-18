package com.aleksieienko.brainfuck.compiler;

import com.aleksieienko.brainfuck.compiler.commands.Command;
import com.aleksieienko.brainfuck.compiler.commands.CycleCommand;
import com.aleksieienko.brainfuck.compiler.commands.DecrementCommand;
import com.aleksieienko.brainfuck.compiler.commands.IncrementCommand;
import com.aleksieienko.brainfuck.compiler.commands.NextCellCommand;
import com.aleksieienko.brainfuck.compiler.commands.PrevCellCommand;
import com.aleksieienko.brainfuck.compiler.commands.PrintCommand;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CompilerToList {
    private Map<Character,Command> commands;

    {
        commands = new HashMap<>();
        commands.put('>', new NextCellCommand());
        commands.put('<', new PrevCellCommand());
        commands.put('-', new DecrementCommand());
        commands.put('+', new IncrementCommand());
        commands.put('[', new CycleCommand());
        commands.put('.', new PrintCommand());
    }

    public List<Command> compile(Reader in) throws IOException {
            List<Command> result = new LinkedList<>();
            for(int chr; ((chr = in.read()) != '\n') && chr != -1;) {
                if (chr == '[') {
                    Command command;
                    if((command = cycleCompile(in)) == null){
                        return Collections.emptyList();
                    } else {
                        result.add(command);
                    }
                } else {
                    if(commands.get((char)chr) == null) {
                        return Collections.emptyList();
                    } else {
                        result.add(commands.get((char)chr));
                    }
                }
            }
        return result;
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

}
