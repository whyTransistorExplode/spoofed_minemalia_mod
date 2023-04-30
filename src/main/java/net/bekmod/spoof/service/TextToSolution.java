package net.bekmod.spoof.service;

import net.bekmod.spoof.entity.solutions.ArithmeticSign;
import net.bekmod.spoof.entity.solutions.Solution;
import net.bekmod.spoof.entity.solutions.supertype.Mark;
import net.bekmod.spoof.entity.solutions.supertype.Maths;
import net.bekmod.spoof.enums.Arithmetics;
import net.bekmod.spoof.entity.solutions.Number;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextToSolution {
    private static TextToSolution instance;
    boolean digitStartPoint = false;
    StringBuilder numberCollectBox = null;

    int bracketCounter = 0;

//    Map<Integer, Integer> bracketLevelKeeper = new HashMap<>();

    int signsCounter = 0;
    boolean isEquation = false;

    List<Mark> marks;

    public static TextToSolution self() {
        if (instance == null) instance = new TextToSolution();
        return instance;
    }

    private TextToSolution() {
    }


    // router
    public Maths proceed(String data) {

        List<Mark> marks = stringDataToMarkList(data);
        return new Solution(marks, signsCounter);
    }

    public ArithmeticSign prioritizeArithmetics(char c, int bracketCounter) {
        switch (c) {
            case '*':
                return new ArithmeticSign((2 + bracketCounter * 10), Arithmetics.MULTIPLY);
            case '/':
                return new ArithmeticSign((2 + bracketCounter * 10), Arithmetics.DIVIDE);
            case '+':
                return new ArithmeticSign((1 + bracketCounter * 10), Arithmetics.SUM);
            case '-':
                return new ArithmeticSign((1 + bracketCounter * 10), Arithmetics.SUBTRACT);
        }
        return null;
    }

    public List<Mark> stringDataToMarkList(String data) {
        marks = new ArrayList<>();
        signsCounter = 0;
        bracketCounter = 0;
        digitStartPoint = false;
        numberCollectBox = null;

        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);

            //digit check section
            digitCheck(c, i, data);
            //end digit section

            // bracket check
            bracketCheck(c, i);

            // arithmetic characters check
            arithmeticSignCheck(c);
        }
        return marks;
    }

    public void digitCheck(char c, int i, String data) {
        // digit check
        if (Character.isDigit(c) || c == '.') {
            if (!digitStartPoint) {
                numberCollectBox = new StringBuilder();
                digitStartPoint = true;
            }
            numberCollectBox.append(c);
            if (i == data.length() - 1) {
                digitStartPoint = false;
                Number onlyNums = new Number(Double.parseDouble(numberCollectBox.toString()));
//                onlyNums.setNumberAsAnswer();
                addMarkAccordingToBracketLevel(onlyNums);
            }
        } else {
            if (digitStartPoint) {
                digitStartPoint = false;
                Number onlyNums = new Number(Double.parseDouble(numberCollectBox.toString()));
                addMarkAccordingToBracketLevel(onlyNums);
            }
        }
    }

    public void bracketCheck(char c, int i) {
        switch (c) {
            case '(':
//                marks.addNode(new Bracket(c, Bracket.BracketState.OPEN));
                // adds arithmetic sign multiply before adding bracket, if only the last
                        bracketCounter++;
                        break;
            case ')':
//                bracketLevelKeeper.remove(bracketCounter);
                bracketCounter--;
                break;
//                marks.addNode(new Bracket(c, Bracket.BracketState.CLOSE));
                // when it is in the same location it has to remove the first one
        }
    }


    public void arithmeticSignCheck(char c) {
        ArithmeticSign arithmeticMark = prioritizeArithmetics(c, bracketCounter);
        if (arithmeticMark != null) {
            addMarkAccordingToBracketLevel(arithmeticMark);
            signsCounter++;
        }
    }

    public void addMarkAccordingToBracketLevel(Mark mark) {
//        if (bracketCounter == 0) {
//            marks.addNode(mark);
//        } else {
//            bracketCache.findTheRequiredBracketLevelId(bracketIdCounter).addChild(mark);
//        }
        mark.bracketLevel = bracketCounter;
        marks.add(mark);
    }

}