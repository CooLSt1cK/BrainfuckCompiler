package com.aleksieienko.brainfuck.compiler.commands;

import com.aleksieienko.brainfuck.compiler.data.Data;
import com.aleksieienko.brainfuck.compiler.visitor.Visitor;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CycleCommand extends Command {
    private List<Command> commands = new LinkedList<>();

    public void add(Command command){
        commands.add(command);
    }

    public CycleCommand() {
    }

    public CycleCommand(List<Command> commands) {
        this.commands = commands;
    }

    public List<Command> getCommands() {
        return commands;
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
