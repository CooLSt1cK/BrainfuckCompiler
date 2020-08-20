package com.aleksieienko.brainfuck.compiler;

import com.aleksieienko.brainfuck.compiler.commands.Command;
import com.aleksieienko.brainfuck.compiler.visitor.Visitor;
import com.aleksieienko.brainfuck.compiler.visitor.impl.ReadVisitor;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CompilerToList {

    public List<Command> compile(Reader in) throws IOException {
        List<Command> result = new LinkedList<>();
        Visitor visitor = new ReadVisitor(in);
        for(int chr; ((chr = in.read()) != '\n') && chr != -1;) {
            if(Command.commandMap.get((char)chr) == null) {
                return Collections.emptyList();
            } else {
                Command command = (Command) Command.commandMap.get((char) chr).accept(visitor);
                if(command == null){
                    return Collections.emptyList();
                }
                result.add(command);
            }
        }
        return result;
    }

}
