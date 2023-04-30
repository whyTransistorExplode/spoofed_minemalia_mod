package net.bekmod.spoof.service;

import net.bekmod.spoof.entity.solutions.Result;
import net.bekmod.spoof.entity.solutions.Solution;
import net.bekmod.spoof.entity.solutions.SolutionBetween;
import net.bekmod.spoof.entity.solutions.supertype.Mark;
import net.bekmod.spoof.entity.solutions.supertype.Maths;

public class MathHelper {

    public static Result calculateText(String text){
        Maths math = TextToSolution.self().proceed(text);

        Solution solution = SolveSolution.self().groupTreeingIntoSolution((Solution) math);

        for (Mark mark : solution.getMarks()) {
            Result result = ((SolutionBetween) mark).commitSolution();
            if (result.isSuccess())
                return  result;
        }
        return new Result(false);
    }


}
