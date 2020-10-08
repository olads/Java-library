package Maths.algebra;


import Maths.Evaluation.Evaluate;

public class Constants extends BasicToken {

    private Evaluate eval = new Evaluate();

    public Constants(String val,types typ){
        super(val,typ);
    }

    @Override
    public BasicToken multiply(BasicToken basicToken){
        String str = getValue() + "x" + basicToken.getValue();
        String ans = eval.eval(str) + "";
      return  new Constants(ans, types.CONSTANTS);

    }

    @Override
    public BasicToken add(BasicToken basicToken) {
        String str = getValue() + "+" + basicToken.getValue();
        String ans = eval.eval(str) + "";
        return  new Constants(ans, types.CONSTANTS);

    }

    @Override
    public BasicToken subtract(BasicToken basicToken) {
        String str = getValue() + "-" + basicToken.getValue();
        String ans = eval.eval(str) + "";
        return  new Constants(ans, types.CONSTANTS);

    }

    @Override
    public BasicToken divide(BasicToken basicToken) {
        String str = getValue() + "/" + basicToken.getValue();
        String ans = eval.eval(str) + "";
        return  new Constants(ans, types.CONSTANTS);

    }

    @Override
    public String getValue() {
        return  value;
    }

}

