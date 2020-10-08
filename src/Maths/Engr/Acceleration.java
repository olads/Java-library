package Maths.Engr;

public class Acceleration extends Equation {
    public Acceleration(){
        super("Acceleration","a");
        formulas.add(new Formula("(v-u)/t"));
        formulas.add(new Formula("((v^2)-(u^2))/2xs"));
        formulas.add(new Formula("(2(s-ut))/t^2"));
    }
}

