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
import java.util.LinkedList;
import java.util.List;

public class ReadVisitor implements Visitor {
    private Deque<List<Command>> stack = new LinkedList<>();

    public ReadVisitor(List<Command> result) throws Exception {
        this.stack.addLast(result);
    }

    @Override
    public Command visitDecrementCommand(DecrementCommand decrementCommand) {
        stack.getLast().add(decrementCommand);
        return decrementCommand;
    }

    @Override
    public Command visitIncrementCommand(IncrementCommand incrementCommand) {
        stack.getLast().add(incrementCommand);
        return incrementCommand;
    }

    @Override
    public Command visitNextCellCommand(NextCellCommand nextCellСommand) {
        stack.getLast().add(nextCellСommand);
        return nextCellСommand;
    }

    @Override
    public Command visitPrevCellCommand(PrevCellCommand prevCellCommand) {
        stack.getLast().add(prevCellCommand);
        return prevCellCommand;
    }

    @Override
    public Object visitStartCycleCommand(StartCycleCommand startCycleCommand) {
        stack.addLast(new LinkedList<>());
        return startCycleCommand;
    }

    @Override
    public Command visitEndCycleCommand(EndCycleCommand endCycleCommand) throws Exception {
        CycleCommand cycleCommand = new CycleCommand((LinkedList<Command>) stack.removeLast());
        cycleCommand.accept(this);
        return cycleCommand;
    }

    @Override
    public Command visitPrintCommand(PrintCommand printCommand) {
        stack.getLast().add(printCommand);
        return printCommand;
    }

    @Override
    public Object visitCycleCommand(CycleCommand cycleCommand) {
        stack.getLast().add(cycleCommand);
        return cycleCommand;
    }
}
