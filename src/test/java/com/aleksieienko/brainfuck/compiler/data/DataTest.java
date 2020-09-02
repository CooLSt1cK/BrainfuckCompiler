package com.aleksieienko.brainfuck.compiler.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DataTest {
    private Data data;

    @Before
    public void setData(){
        data = new Data();
    }

    @Test
    public void cycledMemory() {
        data.setPointer(data.getPointer() - 1);
        data.setPointer(data.getPointer() + 1);
        int actual = data.getPointer();
        int expected = 0;
        Assert.assertEquals(expected, actual);
    }
}
