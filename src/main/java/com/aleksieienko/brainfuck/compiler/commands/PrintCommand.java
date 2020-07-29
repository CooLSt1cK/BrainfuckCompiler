package com.aleksieienko.brainfuck.compiler.commands;

import com.aleksieienko.brainfuck.compiler.data.Data;
import com.aleksieienko.brainfuck.compiler.visitor.Visitor;

public class PrintCommand extends Command {
    @Override
    public void execute(Data data) {
        System.out.print(data.getCurrentCell());
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitPrintCommand(this);
    }
}
