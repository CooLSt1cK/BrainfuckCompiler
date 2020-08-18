package com.aleksieienko.brainfuck.compiler;

import com.aleksieienko.brainfuck.compiler.commands.Command;
import com.aleksieienko.brainfuck.compiler.data.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class DemoToList {

    public static void main(String[] args) {
        try(Reader in = new BufferedReader(new InputStreamReader(System.in))){
            CompilerToList compilerToList = new CompilerToList();
            List<Command> commands;
            if((commands = compilerToList.compile(in)).isEmpty()) {
                System.err.println("Wrong input line or problem!");
            } else {
                Data data = new Data();
                for(Command command : commands){
                    command.execute(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
