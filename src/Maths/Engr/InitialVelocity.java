package Maths.Engr;

public class InitialVelocity extends Equation {
    public InitialVelocity(){
        super("InitialVelocity","u");
        formulas.add(new Formula("v-(axt)"));
        formulas.add(new Formula("2√(v^2-(2xaxs))"));
        formulas.add(new Formula("(d-(0.5xaxt^2))/t"));
    }
}
