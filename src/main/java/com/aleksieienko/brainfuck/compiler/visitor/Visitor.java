package com.aleksieienko.brainfuck.compiler.visitor;

import com.aleksieienko.brainfuck.compiler.commands.impl.CycleCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.DecrementCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.EndCycleCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.IncrementCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.NextCellCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.PrevCellCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.PrintCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.StartCycleCommand;

public interface Visitor {

    Object visitDecrementCommand(DecrementCommand decrementCommand);
    Object visitIncrementCommand(IncrementCommand incrementCommand);
    Object visitNextCellCommand(NextCellCommand nextCellСommand);
    Object visitPrevCellCommand(PrevCellCommand prevCellCommand);
    Object visitPrintCommand(PrintCommand printCommand);
    Object visitEndCycleCommand(EndCycleCommand endCycleCommand);
    Object visitStartCycleCommand(StartCycleCommand startCycleCommand);
    Object visitCycleCommand(CycleCommand cycleCommand);
}
