package Maths.algebra;

import Maths.Evaluation.Evaluate;


public class Variable extends BasicToken {

    private String[] valuesForAdd =  new String[2];
    String coefficient = "";
    public Variable(String val){
        super(val,types.VARIABLES);
    }

    @Override
    public String getValue() {
        return value;
    }
}








