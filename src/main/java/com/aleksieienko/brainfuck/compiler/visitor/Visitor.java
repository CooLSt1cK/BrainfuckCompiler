package com.aleksieienko.brainfuck.compiler.visitor;

import com.aleksieienko.brainfuck.compiler.commands.CycleCommand;
import com.aleksieienko.brainfuck.compiler.commands.DecrementCommand;
import com.aleksieienko.brainfuck.compiler.commands.IncrementCommand;
import com.aleksieienko.brainfuck.compiler.commands.NextCellCommand;
import com.aleksieienko.brainfuck.compiler.commands.PrevCellCommand;
import com.aleksieienko.brainfuck.compiler.commands.PrintCommand;
import java.io.IOException;

public interface Visitor {
    Object visitCycleCommand(CycleCommand cycleCommand) throws IOException;
    Object visitDecrementCommand(DecrementCommand decrementCommand);
    Object visitIncrementCommand(IncrementCommand incrementCommand);
    Object visitNextCellCommand(NextCellCommand nextCellСommand);
    Object visitPrevCellCommand(PrevCellCommand prevCellCommand);
    Object visitPrintCommand(PrintCommand printCommand);
}
