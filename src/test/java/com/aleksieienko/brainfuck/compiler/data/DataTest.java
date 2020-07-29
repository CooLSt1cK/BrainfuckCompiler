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
        data.setIndex(data.getIndex() - 1);
        data.setIndex(data.getIndex() + 1);
        int actual = data.getIndex();
        int expected = 0;
        Assert.assertEquals(expected, actual);
    }
}
