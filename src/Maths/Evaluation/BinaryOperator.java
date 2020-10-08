package Maths.Evaluation;

public class BinaryOperator extends Operator {
   public BinaryOperator(boolean isrtl,Operate opera) {
        isRTL = isrtl;
        operate = opera;
    }
    public BinaryOperator(boolean isrtl, boolean isCoef, Operate opera){
       isRTL = isrtl;
       operate = opera;
       isCoefOperator = isCoef;
    }
    public double solve(String a){
       throw new ParameterException("Incomplete number of parameter");
    }
    public double solve(String a,String b){
       return operate.operate(a,b);
    }
}
