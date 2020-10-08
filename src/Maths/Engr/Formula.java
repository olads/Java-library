package Maths.Engr;

import Maths.Eval;
import Maths.Evaluation.Evaluate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formula {
    public String representation;

    private ArrayList<String> variables;

    public Formula(String represent){
        this.representation = represent;
        variables = new ArrayList<>();
        getVariables();
    }
    public void addConstant(String ... a){

    }

    public void getVariables(){
       char[] chars= representation.toCharArray();
       for(int j = 0;j<representation.length();j++){
           char cur = chars[j];
           if(Character.isLetter(cur) && cur != 'x'){
               variables.add(cur +"");
               //System.out.println(cur + " the character found in the formula");
           }
       }
    }

    public ArrayList<String> variablesNeededToSolve(String ... a){
        ArrayList<String> var = variables;
            for(String str : a){
                if(var.contains(str)){
                    var.remove(str);
                }
            }

            return var;
    }

    public int getNoOfParams(String ... a){
        return (variablesNeededToSolve(a)).size();
    }

    public String replace(Parser parse){
        getVariables();
        String ans = representation;
       HashMap<String,String> data = parse.getParsed();
        for (String a:variables){
            ans = ans.replace(a,data.get(a));
        }
        //System.out.println(representation + "  the value of the formula : " + ans);
        return ans;
    }

    public double solve(Parser parse){
      Evaluate eval = new Evaluate();
    //  Parser parse = new Parser(a);
       return eval.eval(replace(parse));
    }

}
