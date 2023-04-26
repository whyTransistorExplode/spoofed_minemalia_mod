package net.bekmod.spoof.entity.solutions;


import net.bekmod.spoof.entity.solutions.supertype.Mark;

public class Number extends Mark {
    private double number;
    private double power;
    private NumberState state;
    private char unkownVariableChar;
    public enum NumberState{ KNOWN, UNKNOWN, ILLEGAL_STATE}

    public Number(){}

    public NumberState getState() {
        return state;
    }

    public void setState(NumberState state) {
        this.state = state;
    }

    public char getUnkownVariableChar() {
        return unkownVariableChar;
    }

    public void setUnkownVariableChar(char unkownVariableChar) {
        this.unkownVariableChar = unkownVariableChar;
    }

    public double getNumber() {
        if (power != 0) {
            return Math.pow(number, power);
        }
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public Number(double number) {
        this.number = number;
    }

    public void addNum(Number num){
        this.number += num.getNumber();
    }
    public void multiplyNum(Number number){ this.number *= number.getNumber();}
    public void divideNum(Number number){ this.number /= number.getNumber();}

    @Override
    public void showYourself() {
        if (state == NumberState.UNKNOWN)
            System.out.print("[N " + unkownVariableChar  + ",level: "+ bracketLevel+",id: " + bracketId+"]");
        else
            System.out.print("[N " + number  + ",level: "+ bracketLevel+",id: " + bracketId+"]");
    }

}
