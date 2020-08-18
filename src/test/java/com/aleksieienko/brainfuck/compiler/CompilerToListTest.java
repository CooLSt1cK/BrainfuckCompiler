package com.aleksieienko.brainfuck.compiler;

import com.aleksieienko.brainfuck.compiler.commands.Command;
import com.aleksieienko.brainfuck.compiler.commands.CycleCommand;
import com.aleksieienko.brainfuck.compiler.commands.DecrementCommand;
import com.aleksieienko.brainfuck.compiler.commands.IncrementCommand;
import com.aleksieienko.brainfuck.compiler.commands.NextCellCommand;
import com.aleksieienko.brainfuck.compiler.commands.PrevCellCommand;
import com.aleksieienko.brainfuck.compiler.commands.PrintCommand;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CompilerToListTest {
    private CompilerToList compilerToList;

    @Before
    public void setCompilerToList() {
        this.compilerToList = new CompilerToList();
    }

    @Test
    public void compile(){
        List<Command> expected = Arrays.asList(new IncrementCommand(), new IncrementCommand(), new IncrementCommand(), new IncrementCommand(),
                new CycleCommand(Arrays.asList(new NextCellCommand(), new IncrementCommand(), new IncrementCommand(),
                        new CycleCommand(Arrays.asList(new NextCellCommand(), new IncrementCommand(), new IncrementCommand(),
                                new PrevCellCommand(), new DecrementCommand(), new DecrementCommand(), new DecrementCommand())),
                        new PrevCellCommand(), new DecrementCommand())), new NextCellCommand(), new PrintCommand());
        List<Command> actual = null;
        try(Reader reader = new StringReader("++++[>++[>++<---]<-]>.")){
            actual = compilerToList.compile(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(expected, actual);
    }
}
