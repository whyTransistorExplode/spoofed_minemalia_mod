package net.bekmod.spoof.entity.solutions;


import net.bekmod.spoof.entity.solutions.supertype.Mark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SolutionBetween extends Mark {
    private int orderIndex;
    private SolutionBetween solution1;
    private SolutionBetween solution2;


//    private Number number1;
//    private Number number2;

    private Number answer;

    private boolean isAnswer;
    private ArithmeticSign sign;

    public Result commitSolution() {

//        if (!isTransferSolutionsToNums) {
//                Result result1 = solution1.commitSolution();
//                Result result2 = solution2.commitSolution();
//                number1 = new Number(result1.getNumber().getNumber());
//                number2 = new Number(result2.getNumber().getNumber());
//            isTransferSolutionsToNums = true;
//        }

        if (!this.isAnswer) {
            this.answer = doMath(solution1.commitSolution().getNumber(), solution2.commitSolution().getNumber(), sign);
            this.isAnswer = true;
        }

        return new Result(answer, true, Result.ResultArithmetic.SUCCESS);
    }

    public void joinSolutionBetweens(ArithmeticSign sign, SolutionBetween... solutions) {
        this.solution1 = solutions[0];
        this.solution2 = solutions[1];
        this.sign = sign;
    }

    private Number doMath(Number num1, Number num2, ArithmeticSign sign) {
        switch (sign.getMark()) {
            case SUM:
                return sum(num1, num2);
            case DIVIDE:
                return divide(num1, num2);
            case MULTIPLY:
                return multiply(num1, num2);
            case SUBTRACT:
                return subtract(num1, num2);
        }
        return new Number(-1);
    }

    public void setNumberAsAnswer(Number number) {
        this.answer = number;
        this.isAnswer = true;
    }

    public Number sum(Number num1, Number num2) {
        return new Number(num1.getNumber() + num2.getNumber());
    }

    public Number subtract(Number num1, Number num2) {
        return new Number(num1.getNumber() - num2.getNumber());
    }

    public Number multiply(Number num1, Number num2) {
        return new Number(num1.getNumber() * num2.getNumber());
    }

    public Number divide(Number num1, Number num2) {
        return new Number(num1.getNumber() / num2.getNumber());
    }

    @Override
    public void showYourself() {
        System.out.print("[" + answer + "]");
    }
}
