package net.bekmod.spoof.entity.solutions;

import net.bekmod.spoof.entity.solutions.supertype.Mark;
import net.bekmod.spoof.enums.Arithmetics;

public class ArithmeticSign extends Mark {
    private int priority;
    private Arithmetics mark;

    public ArithmeticSign() {
    }

    public ArithmeticSign(int priority, Arithmetics mark) {
        this.priority = priority;
        this.mark = mark;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Arithmetics getMark() {
        return mark;
    }

    public void setMark(Arithmetics mark) {
        this.mark = mark;
    }

    @Override
    public void showYourself() {
        System.out.print("[" + getMark() + " " + priority + ",level: "+ bracketLevel+"]");
    }
}
