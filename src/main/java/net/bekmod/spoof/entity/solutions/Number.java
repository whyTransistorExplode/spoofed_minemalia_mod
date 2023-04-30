package net.bekmod.spoof.entity.solutions;


import net.bekmod.spoof.entity.solutions.supertype.Mark;

public class Number extends Mark {
    private double number;

    public Number(){}

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public Number(double number) {
        this.number = number;
    }


    @Override
    public void showYourself() {
            System.out.print("[N " + number  + ",level: "+ bracketLevel+",id: "+"]");
    }

}
