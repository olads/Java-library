package Maths.Engr;

public class Mass extends Equation {
    public Mass(){
        super("Mass","m");
        formulas.add(new Formula("F/a"));
        formulas.add(new Formula("F/g"));
        formulas.add(new Formula("ρxv"));
    }
}
