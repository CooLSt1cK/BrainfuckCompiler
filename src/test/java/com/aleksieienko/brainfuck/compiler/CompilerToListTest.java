package com.aleksieienko.brainfuck.compiler;

import com.aleksieienko.brainfuck.compiler.commands.Command;
import com.aleksieienko.brainfuck.compiler.commands.impl.CycleCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.DecrementCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.IncrementCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.NextCellCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.PrevCellCommand;
import com.aleksieienko.brainfuck.compiler.commands.impl.PrintCommand;
import java.util.Arrays;
import java.util.Collections;
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
    public void correctInput() throws Exception {
        List<Command> expected = Arrays.asList(new IncrementCommand(), new IncrementCommand(), new IncrementCommand(), new IncrementCommand(),
                new CycleCommand(new LinkedList<>(Arrays.asList(new NextCellCommand(), new IncrementCommand(), new IncrementCommand(),
                        new CycleCommand(new LinkedList<>(Arrays.asList(new NextCellCommand(), new IncrementCommand(), new IncrementCommand(),
                                new PrevCellCommand(), new DecrementCommand(), new DecrementCommand(), new DecrementCommand()))),
                        new PrevCellCommand(), new DecrementCommand()))), new NextCellCommand(), new PrintCommand());
        List<Command> actual = compilerToList.compile("++++[>++[>++<---]<-]>.");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void incorrectInput() throws Exception {
        List<Command> expected = Collections.emptyList();
        List<Command> actual = compilerToList.compile("++++*[>++[>++<---]<-]>.");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void incorrectInputIntoCycle() throws Exception {
        List<Command> expected = Collections.emptyList();
        List<Command> actual = compilerToList.compile("++++[>++[>+*+<---]<-]>.");
        Assert.assertEquals(expected, actual);
    }
}
