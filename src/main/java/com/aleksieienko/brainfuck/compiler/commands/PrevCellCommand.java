package com.aleksieienko.brainfuck.compiler.commands;

import com.aleksieienko.brainfuck.compiler.data.Data;
import com.aleksieienko.brainfuck.compiler.visitor.Visitor;

public class PrevCellCommand extends Command {
    @Override
    public void execute(Data data) {
        data.setIndex(data.getIndex() - 1);
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitPrevCellCommand(this);
    }
}
