package Maths.Evaluation;

import java.util.HashMap;
import java.util.Map;

public class OperatorFactory {

    public Map<String,Operator> myOperators;
    public OperatorFactory(){
        myOperators = new HashMap<>();
        myOperators.put("+",new BinaryOperator(true,true, new Operate() {
            @Override
            public double operate(String a, String c) {
                return (parseToDouble(c) + parseToDouble(a));
            }
        }));
        myOperators.put("-",new BinaryOperator(true,true, new Operate() {
            @Override
            public double operate(String a, String c) {
                return (parseToDouble(a) - parseToDouble(c));
            }
        }));
        myOperators.put("x",new BinaryOperator(true, new Operate() {
            @Override
            public double operate(String a, String c) {
                return (parseToDouble(c) * parseToDouble(a));
            }
        }));
        myOperators.put("/",new BinaryOperator(true, new Operate() {
            @Override
            public double operate(String a, String c) {
                return (parseToDouble(a) / parseToDouble(c));
            }
        }));
        myOperators.put("^",new BinaryOperator(true, new Operate() {
            @Override
            public double operate(String a, String c) {
                return (Math.pow(parseToDouble(a),parseToDouble(c)));
            }
        }));
        myOperators.put("!",new UnaryOperator(true, new Operate() {
            @Override
            public double operate(String a) {
                return fact(parseToDouble(a));
            }
        }));

        myOperators.put("√",new BinaryOperator(true,new Operate(){
            @Override
            public double operate(String a,String b){
                double aa = parseToDouble(a);
                double bb = parseToDouble(b);
                //System.out.println("from root operator " + (Math.log10(bb)/aa) +" the pow is " + Math.pow(10,(Math.log10(bb)/aa)));
                return Math.pow(10,(Math.log10(bb)/aa));
            }
        }));
    }
    public double parseToDouble(String a){
        double ans = Double.parseDouble(a);
        if(Double.isNaN(ans)) return 0;
        else return ans;
    }

    public double fact(double a){
        if(a==1){
            return a;
        }
        else{
            return(a*fact(a-1));
        }
    }
    public Map<String,Operator> getMyOperators(){
        return this.myOperators;
    }
}
