package com.aleksieienko.brainfuck.compiler.commands;

import com.aleksieienko.brainfuck.compiler.data.Data;
import com.aleksieienko.brainfuck.compiler.visitor.Visitor;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Command {
    public static Map<Character, Command> commandMap;

    static {
        commandMap = new HashMap<>();
        commandMap.put('>', new NextCellCommand());
        commandMap.put('<', new PrevCellCommand());
        commandMap.put('-', new DecrementCommand());
        commandMap.put('+', new IncrementCommand());
        commandMap.put('[', new CycleCommand());
        commandMap.put('.', new PrintCommand());
    }

    public abstract void execute(Data data);

    public abstract Object accept(Visitor visitor) throws IOException;

    @Override
    public abstract boolean equals(Object obj);
}
