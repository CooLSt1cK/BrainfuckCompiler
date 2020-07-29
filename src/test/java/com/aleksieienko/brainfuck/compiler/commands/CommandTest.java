package com.aleksieienko.brainfuck.compiler.commands;

import com.aleksieienko.brainfuck.compiler.data.Data;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CommandTest {
    private Data data;

    @Before
    public void setData(){
        data = new Data();
    }

    @Test
    public void nextCell(){
        new NextCellCommand().execute(data);

        int expected = 1;
        int actual = data.getIndex();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void previousCell(){
        new PrevCellCommand().execute(data);

        int expected = 29999;
        int actual = data.getIndex();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void increment(){
        new IncrementCommand().execute(data);

        int expected = 1;
        int actual = data.getCurrentCell();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void decrement(){
        new IncrementCommand().execute(data);
        new DecrementCommand().execute(data);

        int expected = 0;
        int actual = data.getCurrentCell();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void incrementA(){
        Command command = new IncrementCommand();
        for(int i = 0; i < 65; i++){
            command.execute(data);
        }

        int expected = 'A';
        int actual = data.getCurrentCell();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void cycle(){
        Command command = new IncrementCommand();
        for(int i = 0; i < 10; i++){
            command.execute(data);
        }
        new CycleCommand(Arrays.asList( new NextCellCommand(), command,
                command, command, command, command, command,
                new PrevCellCommand(), new DecrementCommand())).execute(data);
        new NextCellCommand().execute(data);
        for(int i = 0; i < 5; i++){
            command.execute(data);
        }

        int expected = 'A';
        int actual = data.getCurrentCell();

        Assert.assertEquals(expected, actual);
    }
}
