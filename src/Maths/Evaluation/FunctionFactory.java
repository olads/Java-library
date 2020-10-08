package Maths.Evaluation;

import java.util.HashMap;
import java.util.Map;

public class FunctionFactory {
    public static Map<String,Function> functions = new HashMap<>();
    public static Function get(String a){
        String caller = a.toLowerCase();
        initialize();
        return functions.get(a);
        }
        public static void initialize(){
        functions.put("sin",new Function(){
            @Override
            public double solve(String a){
                return Math.sin(parseToDouble(a));
            }
        });
        functions.put("cos",new Function(){
            @Override
            public double solve(String a){
                return Math.cos(parseToDouble(a));
            }
        });
        functions.put("tan",new Function(){
            @Override
            public double solve(String a){
                return Math.tan(parseToDouble(a));
            }
        });

        }

    public static double parseToDouble(String a){
        double ans = Double.parseDouble(a);
        if(Double.isNaN(ans)) return 0;
        else return ans;
    }
}
