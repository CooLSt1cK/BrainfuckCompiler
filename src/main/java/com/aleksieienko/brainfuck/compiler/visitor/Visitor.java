package com.aleksieienko.brainfuck.compiler.visitor;

import com.aleksieienko.brainfuck.compiler.commands.CycleCommand;
import com.aleksieienko.brainfuck.compiler.commands.DecrementCommand;
import com.aleksieienko.brainfuck.compiler.commands.IncrementCommand;
import com.aleksieienko.brainfuck.compiler.commands.NextCellCommand;
import com.aleksieienko.brainfuck.compiler.commands.PrevCellCommand;
import com.aleksieienko.brainfuck.compiler.commands.PrintCommand;

public interface Visitor {
    String visitCycleCommand(CycleCommand cycleCommand);
    String visitDecrementCommand(DecrementCommand decrementCommand);
    String visitIncrementCommand(IncrementCommand incrementCommand);
    String visitNextCellCommand(NextCellCommand nextCellСommand);
    String visitPrevCellCommand(PrevCellCommand prevCellCommand);
    String visitPrintCommand(PrintCommand printCommand);
}
