package com.aleksieienko.brainfuck.compiler.data;

public class Data {
    private char[] memory = new char[30000];
    private Integer pointer = 0;

    public Integer getPointer() {
        return pointer;
    }

    public void setPointer(Integer pointer) {
        if(pointer >= 0 && pointer < 30000) {

            this.pointer = pointer;
        } else {

            this.pointer = (pointer >= 30000)?(0):(29999);
        }
    }

    public char getCurrentCell(){
        if(pointer >= 0 && pointer < 30000) {

            return memory[pointer];
        } else {

            return 0;
        }
    }

    public void setCurrentCell(char x){
        if(pointer >= 0 && pointer < 30000) {

            memory[pointer] = x;
        }
    }
}
