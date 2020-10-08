package Maths.Engr;

public class Pressure extends Equation {
    public Pressure(){
        super("pressure","P");
        formulas.add(new Formula("F/A"));
        formulas.add(new Formula("ρxgxh"));
    }
}

