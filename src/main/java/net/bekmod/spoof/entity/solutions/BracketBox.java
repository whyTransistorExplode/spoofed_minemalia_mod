package net.bekmod.spoof.entity.solutions;

import net.bekmod.spoof.entity.solutions.supertype.Mark;

import java.util.ArrayList;
import java.util.List;

public class BracketBox extends Mark {
    private int id;
    private final List<Mark> children;

    public BracketBox(int bracketLevel, int levelId) {
        children = new ArrayList<>();
        this.bracketLevel = bracketLevel;
        this.id = levelId;
    }

    public void addChild(Mark child) {
        children.add(child);
    }

    public List<Mark> getChildren() {
        return children;
    }

    public Mark getChild(int index) {
        return children.get(index);
    }


    public BracketBox findTheRequiredBracketLevelId(int bracketId) {
        if (bracketId == this.id) {
            return this;
        } else {
            BracketBox lookingBracket;

            for (Mark child : this.children) {
                if (child instanceof BracketBox) {
                    lookingBracket = ((BracketBox) child).findTheRequiredBracketLevelId(bracketId);
                    if (lookingBracket != null)
                    {
                        return lookingBracket;
                    }
                }
            }
            return null;
        }
    }

    public void multiplyNumberIntoInnerSelf(Number number){
        if (number.getState() == Number.NumberState.UNKNOWN){

        }
    }

    @Override
    public void showYourself() {
        System.out.print("{");
        for (Mark child : children) {
            child.showYourself();
        }
        System.out.print("}");
    }
}
