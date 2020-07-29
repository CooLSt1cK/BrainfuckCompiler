package com.aleksieienko.brainfuck.compiler.visitor.impl;

import com.aleksieienko.brainfuck.compiler.commands.Command;
import com.aleksieienko.brainfuck.compiler.commands.CycleCommand;
import com.aleksieienko.brainfuck.compiler.commands.DecrementCommand;
import com.aleksieienko.brainfuck.compiler.commands.IncrementCommand;
import com.aleksieienko.brainfuck.compiler.commands.NextCellCommand;
import com.aleksieienko.brainfuck.compiler.commands.PrevCellCommand;
import com.aleksieienko.brainfuck.compiler.commands.PrintCommand;
import com.aleksieienko.brainfuck.compiler.visitor.Visitor;
import java.util.Iterator;

public class PrintToJavaVisitor implements Visitor {

    @Override
    public String visitCycleCommand(CycleCommand cycleCommand) {
        StringBuilder result = new StringBuilder();
        result.append("new CycleCommand(Arrays.asList(");
        Iterator<Command> iterator = cycleCommand.getCommands().iterator();
        result.append(iterator.next().accept(this));
        while(iterator.hasNext()){
            result.append(',');
            result.append(iterator.next().accept(this));
        }
        result.append("))");
        return result.toString();
    }

    @Override
    public String visitDecrementCommand(DecrementCommand decrementCommand) {
        return "new DecrementCommand()";
    }

    @Override
    public String visitIncrementCommand(IncrementCommand incrementCommand) {
        return "new IncrementCommand()";
    }

    @Override
    public String visitNextCellCommand(NextCellCommand nextCellСommand) {
        return "new NextCellCommand()";
    }

    @Override
    public String visitPrevCellCommand(PrevCellCommand prevCellCommand) {
        return "new PrevCellCommand()";
    }

    @Override
    public String visitPrintCommand(PrintCommand printCommand) {
        return "new PrintCommand()";
    }
}
