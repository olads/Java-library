package Maths.algebra;

import Maths.util.Printer;

import java.util.ArrayList;

public class Formula {
   Parenthesis leftSide ;
   Parenthesis rightSide;
   String left = "";
   String right = "";

   String subject;

   public Formula(String equation,String sub){
       System.out.println(equation);
       String[] aa = equation.split("=");
       left = aa[0];
       right = aa[1];
       this.subject = sub;
       init();
    }

    public void setSubject(String subject){
       this.subject = subject;
    }

    public boolean init(){
       System.out.println(left + " = " + right);
       leftSide = new Parenthesis(left);
       rightSide = new Parenthesis(right);
        print(extractSubject(left));
        System.out.println("Second");
        print(extractSubject(right));
       return true;
    }

    public ArrayList<String> extractSubject(String str){

       ArrayList<String> tokens = new ArrayList<>();
       Variable var = new Variable(subject);
       for(BasicToken basic : Tokenizer.tokenize(str)) {
           if (basic != null) {
               if (basic.checkFactor(subject)) {
                   tokens.add(Parenthesis.createBrac(var, basic.factorOut(var)).parse());
               } else tokens.add(basic.parse());
           }
       }
       return tokens;
    }

    public void print(ArrayList<String> str){
       System.out.println("Starting now...");
       for(String y : str ){
           System.out.println(y);
       }
    }

}
