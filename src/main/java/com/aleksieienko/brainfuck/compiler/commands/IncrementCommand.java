package com.aleksieienko.brainfuck.compiler.commands;

import com.aleksieienko.brainfuck.compiler.data.Data;
import com.aleksieienko.brainfuck.compiler.visitor.Visitor;

public class IncrementCommand extends Command {
    @Override
    public void execute(Data data) {
        data.setCurrentCell((char) (data.getCurrentCell() + 1));
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visitIncrementCommand(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IncrementCommand;
    }
}
