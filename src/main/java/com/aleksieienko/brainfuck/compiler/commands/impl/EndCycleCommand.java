package com.aleksieienko.brainfuck.compiler.commands.impl;

import com.aleksieienko.brainfuck.compiler.commands.Command;
import com.aleksieienko.brainfuck.compiler.data.Data;
import com.aleksieienko.brainfuck.compiler.visitor.Visitor;

public class EndCycleCommand extends Command {
    @Override
    public void execute(Data data) {

    }

    @Override
    public Object accept(Visitor visitor) throws Exception {
        return visitor.visitEndCycleCommand(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof EndCycleCommand;
    }
}
