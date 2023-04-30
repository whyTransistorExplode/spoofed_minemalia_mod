package net.bekmod.spoof.entity.solutions;
public class Result {
   private Number number;
   private boolean success;
   private ResultArithmetic result;
    public enum ResultArithmetic { SUCCESS, ILLEGAL_ARITHMETIC_EXCEPTION, UNKOWN_NUMBER}

    public Result(){
    }

    public Result(Number number, boolean success, ResultArithmetic result) {
        this.number = number;
        this.success = success;
        this.result = result;
    }

    public Result(boolean success) {
        this.success = success;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ResultArithmetic getResult() {
        return result;
    }

    public void setResult(ResultArithmetic result) {
        this.result = result;
    }
}
