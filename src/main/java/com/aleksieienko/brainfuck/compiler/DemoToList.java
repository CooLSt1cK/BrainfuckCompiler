package com.aleksieienko.brainfuck.compiler;

import com.aleksieienko.brainfuck.compiler.commands.Command;
import com.aleksieienko.brainfuck.compiler.data.Data;

public class DemoToList {

    public static void main(String[] args) throws Exception {
        CompilerToList compilerToList = new CompilerToList();
        Data data = new Data();

        for(Command command : compilerToList.compile("++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.")){
            command.execute(data);
        }

    }
}
