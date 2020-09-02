package com.aleksieienko.brainfuck.compiler.commands.impl;

import com.aleksieienko.brainfuck.compiler.commands.Command;
import com.aleksieienko.brainfuck.compiler.data.Data;
import com.aleksieienko.brainfuck.compiler.visitor.Visitor;

public class PrintCommand extends Command {
    @Override
    public void execute(Data data) {
        System.out.print(data.getCurrentCell());
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visitPrintCommand(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PrintCommand;
    }
}
