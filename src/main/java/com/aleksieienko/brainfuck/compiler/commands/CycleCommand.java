package com.aleksieienko.brainfuck.compiler.commands;

import com.aleksieienko.brainfuck.compiler.data.Data;
import com.aleksieienko.brainfuck.compiler.visitor.Visitor;
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
    public String accept(Visitor visitor) {
        return visitor.visitCycleCommand(this);
    }
}
