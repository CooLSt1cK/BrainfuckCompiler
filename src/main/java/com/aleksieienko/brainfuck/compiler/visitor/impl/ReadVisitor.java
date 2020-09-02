package com.aleksieienko.brainfuck.compiler.visitor.impl;

import com.aleksieienko.brainfuck.compiler.commands.Command;
import com.aleksieienko.brainfuck.compiler.commands.impl.CycleCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.DecrementCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.EndCycleCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.IncrementCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.NextCellCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.PrevCellCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.PrintCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.StartCycleCommand;
import com.aleksieienko.brainfuck.compiler.visitor.Visitor;
import java.util.Deque;
import java.util.List;

public class ReadVisitor implements Visitor {
    private Deque<Command> stack;

    public ReadVisitor(List<Command> stack) throws Exception {
        if(!(stack instanceof Deque)){
            throw new Exception("Incorrect type of list");
        }
        this.stack = (Deque<Command>) stack;
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
    public Object visitStartCycleCommand(StartCycleCommand startCycleCommand) {
        return startCycleCommand;
    }

    @Override
    public Command visitEndCycleCommand(EndCycleCommand endCycleCommand) throws Exception {
        CycleCommand cycleCommand = new CycleCommand();
        Command command;

        while( (command = stack.pollLast()) != null && !command.equals(new StartCycleCommand())) {
            cycleCommand.addFirst(command);
        }

        if(command == null){
            throw new Exception("Incorrect cycle command");
        }

        return cycleCommand;
    }

    @Override
    public Command visitPrintCommand(PrintCommand printCommand) {
        return printCommand;
    }

    @Override
    public Object visitCycleCommand(CycleCommand cycleCommand) {
        return cycleCommand;
    }
}
