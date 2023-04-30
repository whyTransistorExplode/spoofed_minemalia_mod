package net.bekmod.spoof.entity.solutions;


import net.bekmod.spoof.entity.solutions.supertype.Mark;
import net.bekmod.spoof.entity.solutions.supertype.Maths;

import java.util.List;

public class Solution extends Maths {
    private List<Mark> marks;
    private int SignSize;

    public Solution() {
    }

    public Solution(List<Mark> marks, int signSize) {
        this.marks = marks;
        SignSize = signSize;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public int getSignSizes() {
        return SignSize;
    }

    public void setSignSize(int signSize) {
        SignSize = signSize;
    }
}
