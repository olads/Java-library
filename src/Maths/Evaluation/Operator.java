package Maths.Evaluation;

public abstract class Operator {
    public boolean isRTL = false;
    public boolean isUnary = false;
    public boolean isCoefOperator = false;
    Operate operate;
    public abstract double solve(String a);
    public abstract double solve(String a,String b);
    public void setOperator(Operate opera){
        Operator.this.operate = opera;
    }
    public void setIsCoefOperator(boolean a){
        this.isCoefOperator = a;
    }
}
