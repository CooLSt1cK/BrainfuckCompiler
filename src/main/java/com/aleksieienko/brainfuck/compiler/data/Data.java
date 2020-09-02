package com.aleksieienko.brainfuck.compiler.data;

public class Data {
    private char[] arr = new char[30000];
    private Integer index = 0;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        if(index >= 0 && index < 30000) {
            this.index = index;
        } else {
            this.index = (index >= 30000)?(0):(29999);
        }
    }

    public char getCurrentCell(){
        if(index >= 0 && index < 30000) {

            return arr[index];
        } else {

            return 0;
        }
    }

    public void setCurrentCell(char x){
        if(index >= 0 && index < 30000) {
            arr[index] = x;
        }
    }
}
