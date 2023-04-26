package net.bekmod.spoof.service;


import net.bekmod.spoof.entity.solutions.ArithmeticSign;
import net.bekmod.spoof.entity.solutions.Solution;
import net.bekmod.spoof.entity.solutions.SolutionBetween;
import net.bekmod.spoof.entity.solutions.supertype.Mark;
import net.bekmod.spoof.entity.solutions.Number;

public class SolveSolution {
    private static SolveSolution instance = null;

    public static SolveSolution self(){
        if (instance == null) instance = new SolveSolution();
        return instance;
    }
    private SolveSolution(){}
    public Solution groupTreeingIntoSolution(Solution solution) {
        changeNumbersIntoSolutions(solution);

        if (solution.getSignSizes() < 1)
        {
            System.out.println("there is no valid answer!");
            return null;
        }
        for (int i = 0; i < solution.getSignSizes(); i++) {
            int k = 0;
            int rememberPos = -1;

            for (int j = 0; j < solution.getMarks().size(); j++) {
                Mark mark = solution.getMarks().get(j);
                if (mark instanceof ArithmeticSign) {
                    if (((ArithmeticSign) mark).getPriority() > k) {
                        k = ((ArithmeticSign) mark).getPriority();
                        rememberPos = j;
                    }
                }
            }
            Mark solutionB1 = solution.getMarks().get(rememberPos - 1);
            Mark solutionB2 = solution.getMarks().get(rememberPos + 1);

            if (solutionB1 instanceof SolutionBetween && solutionB2 instanceof SolutionBetween) {
                ArithmeticSign theSign = (ArithmeticSign) solution.getMarks().get(rememberPos);

                SolutionBetween solutionBetween = new SolutionBetween();
                solutionBetween.joinSolutionBetweens(theSign,(SolutionBetween) solutionB1,
                        (SolutionBetween) solutionB2);

                solution.getMarks().set(rememberPos, solutionBetween);
                solution.getMarks().remove(rememberPos + 1);
                solution.getMarks().remove(rememberPos - 1);
            }
        }
        return solution;
    }

    public void changeNumbersIntoSolutions(Solution solution){
        for (int i = 0; i < solution.getMarks().size(); i++) {
            Mark mark = solution.getMarks().get(i);
            if (mark instanceof Number){
                SolutionBetween solutionBetween = new SolutionBetween();
                solutionBetween.setNumberAsAnswer((Number) mark);
                solution.getMarks().set(i,solutionBetween);
            }
        }
    }

}
