package com.aleksieienko.brainfuck.compiler.visitor.impl;

import com.aleksieienko.brainfuck.compiler.commands.Command;
import com.aleksieienko.brainfuck.compiler.commands.CycleCommand;
import com.aleksieienko.brainfuck.compiler.commands.DecrementCommand;
import com.aleksieienko.brainfuck.compiler.commands.IncrementCommand;
import com.aleksieienko.brainfuck.compiler.commands.NextCellCommand;
import com.aleksieienko.brainfuck.compiler.commands.PrevCellCommand;
import com.aleksieienko.brainfuck.compiler.commands.PrintCommand;
import com.aleksieienko.brainfuck.compiler.visitor.Visitor;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;

public class ReadVisitor implements Visitor {
    private Reader in;

    public ReadVisitor(Reader in){
        this.in = in;
    }

    @Override
    public Command visitCycleCommand(CycleCommand cycleCommand) throws IOException {
        CycleCommand newCycleCommand = new CycleCommand();
        for(int chr; ((chr = in.read()) != ']');) {
            if(Command.commandMap.get((char)chr) == null) {
                return null;
            } else {
                Command command = (Command) Command.commandMap.get((char) chr).accept(this);
                if(command == null){
                    return null;
                }
                newCycleCommand.add(command);
            }
        }
        return newCycleCommand;
    }

    @Override
    public Command visitDecrementCommand(DecrementCommand decrementCommand) {
        return decrementCommand;
    }

    @Override
    public Command visitIncrementCommand(IncrementCommand incrementCommand) {
        return incrementCommand;
    }

    @Override
    public Command visitNextCellCommand(NextCellCommand nextCellСommand) {
        return nextCellСommand;
    }

    @Override
    public Command visitPrevCellCommand(PrevCellCommand prevCellCommand) {
        return prevCellCommand;
    }

    @Override
    public Command visitPrintCommand(PrintCommand printCommand) {
        return printCommand;
    }
}
