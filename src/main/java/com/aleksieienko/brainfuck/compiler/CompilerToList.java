package com.aleksieienko.brainfuck.compiler;

import com.aleksieienko.brainfuck.compiler.commands.Command;
import com.aleksieienko.brainfuck.compiler.visitor.Visitor;
import com.aleksieienko.brainfuck.compiler.visitor.impl.ReadVisitor;
import java.util.LinkedList;
import java.util.List;

public class CompilerToList {

    public List<Command> compile(String brainfuckLine) throws Exception {

        List<Command> result = new LinkedList<>();
        Visitor visitor = new ReadVisitor(result);

        for(char brainfuckLexeme : brainfuckLine.toCharArray()) {
            if(Command.commandMap.get(brainfuckLexeme) == null) {
                throw new Exception("Wrong brainfuck lexeme");
            } else {
                result.add((Command) Command.commandMap.get(brainfuckLexeme).accept(visitor));
            }
        }

        return result;
    }

}
