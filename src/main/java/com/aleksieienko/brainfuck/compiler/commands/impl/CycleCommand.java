package com.aleksieienko.brainfuck.compiler.commands.impl;

import com.aleksieienko.brainfuck.compiler.commands.Command;
import com.aleksieienko.brainfuck.compiler.data.Data;
import com.aleksieienko.brainfuck.compiler.visitor.Visitor;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CycleCommand extends Command {
    private LinkedList<Command> commands = new LinkedList<>();

    public CycleCommand() {
    }

    public CycleCommand(LinkedList<Command> commands) {
        this.commands = commands;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void addLast(Command command){
        commands.addLast(command);
    }

    public void addFirst(Command command){
        commands.addFirst(command);
    }

    @Override
    public void execute(Data data) {
        while(data.getCurrentCell()!=0){
            for(Command c : commands){
                c.execute(data);
            }
        }
    }

    @Override
    public Object accept(Visitor visitor) throws IOException {
        return visitor.visitCycleCommand(this);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CycleCommand){
            CycleCommand anotherObject = (CycleCommand) obj;
            return this.getCommands().equals(anotherObject.getCommands());
        } else {
            return false;
        }
    }
}
