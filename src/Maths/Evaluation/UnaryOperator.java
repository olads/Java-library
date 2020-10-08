package Maths.Evaluation;

public class UnaryOperator extends Operator {
   public UnaryOperator(boolean isrtl,Operate operat){
        operate = operat;
        isRTL = isrtl;
        isUnary = true;
    }
    public double solve(String a){
       return UnaryOperator.this.operate.operate(a);
    }
    public double solve(String a, String b){
       throw new ParameterException("Excessive number of parameter");
    }
}
