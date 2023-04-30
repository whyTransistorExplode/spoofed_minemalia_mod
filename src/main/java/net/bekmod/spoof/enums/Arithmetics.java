package net.bekmod.spoof.enums;

public enum Arithmetics {
    MULTIPLY, DIVIDE, SUM, SUBTRACT, POWER, ROOT;
    private char c;

    public static char getItsChar(Arithmetics arithmetics){
        switch (arithmetics){
            case MULTIPLY: return '*';
            case DIVIDE:   return '/';
            case SUM:      return '+';
            case SUBTRACT: return '-';
            case POWER:    return '^';
            case ROOT:     return '~';
        }
        return 0;
    }
}
