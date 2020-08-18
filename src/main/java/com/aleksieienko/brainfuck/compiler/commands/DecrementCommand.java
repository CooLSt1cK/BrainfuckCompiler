package com.aleksieienko.brainfuck.compiler.commands;

import com.aleksieienko.brainfuck.compiler.data.Data;
import com.aleksieienko.brainfuck.compiler.visitor.Visitor;

public class DecrementCommand extends Command {
    @Override
    public void execute(Data data) {
        data.setCurrentCell((char) (data.getCurrentCell() - 1));
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitDecrementCommand(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DecrementCommand;
    }
}
