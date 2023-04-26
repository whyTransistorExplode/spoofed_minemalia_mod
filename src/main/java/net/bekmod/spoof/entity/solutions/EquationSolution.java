package net.bekmod.spoof.entity.solutions;

import net.bekmod.spoof.entity.solutions.supertype.Mark;
import net.bekmod.spoof.entity.solutions.supertype.Maths;

import java.util.List;

public class EquationSolution extends Maths {
    private List<Mark> marksR;
    private int SignSizeR;
    private List<Mark> marksL;
    private int signSizeL;

    public EquationSolution(List<Mark> marksR, int signSizeR, List<Mark> marksL, int signSizeL) {
        this.marksR = marksR;
        SignSizeR = signSizeR;
        this.marksL = marksL;
        this.signSizeL = signSizeL;
    }
    public EquationSolution(){}

    public List<Mark> getMarksR() {
        return marksR;
    }

    public List<Mark> getMarksL() {
        return marksL;
    }

    public void setMarksL(List<Mark> marksL) {
        this.marksL = marksL;
    }

    public void setMarksR(List<Mark> marksR) {
        this.marksR = marksR;
    }

    public int getSignSizeR() {
        return SignSizeR;
    }

    public void setSignSizeR(int signSizeR) {
        SignSizeR = signSizeR;
    }
}
