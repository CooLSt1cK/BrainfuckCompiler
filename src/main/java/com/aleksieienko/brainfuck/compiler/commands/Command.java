package com.aleksieienko.brainfuck.compiler.commands;

import com.aleksieienko.brainfuck.compiler.data.Data;
import com.aleksieienko.brainfuck.compiler.visitor.Visitor;

public abstract class Command {

    public abstract void execute(Data data);

    public abstract String accept(Visitor visitor);
}
