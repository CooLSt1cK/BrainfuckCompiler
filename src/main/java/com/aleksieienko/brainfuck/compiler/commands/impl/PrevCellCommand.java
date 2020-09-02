package com.aleksieienko.brainfuck.compiler.commands.impl;

import com.aleksieienko.brainfuck.compiler.commands.Command;
import com.aleksieienko.brainfuck.compiler.data.Data;
import com.aleksieienko.brainfuck.compiler.visitor.Visitor;

public class PrevCellCommand extends Command {
    @Override
    public void execute(Data data) {
        data.setPointer(data.getPointer() - 1);
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visitPrevCellCommand(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PrevCellCommand;
    }
}
