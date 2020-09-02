package com.aleksieienko.brainfuck.compiler.commands;

import com.aleksieienko.brainfuck.compiler.commands.impl.DecrementCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.EndCycleCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.IncrementCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.NextCellCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.PrevCellCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.PrintCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.StartCycleCommand;
import com.aleksieienko.brainfuck.compiler.data.Data;
import com.aleksieienko.brainfuck.compiler.visitor.Visitor;
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
        commandMap.put('[', new StartCycleCommand());
        commandMap.put(']', new EndCycleCommand());
        commandMap.put('.', new PrintCommand());
    }

    public abstract void execute(Data data);

    public abstract Object accept(Visitor visitor) throws Exception;

    @Override
    public abstract boolean equals(Object obj);
}
