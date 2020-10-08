package Maths.Engr;

import Maths.Eval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * A class representing the framework of solving physics
 * problems
 * it contains functions for solving for a particular variable
 * and getting the list of variables remaining to perform
 * the solving of the formula
 *
 */
public class Variables {
  // private ArrayList<String> var =new ArrayList<>();
    /**
     * List of different forms of the base
     * formula
     */
   private ArrayList<String> definitions = new ArrayList<>();
    /**
     * List of variables in the base formula
     */
   ArrayList<String> varcom = new ArrayList<>();
    /**
     * A string of variables in the formula merged together
     */
   public String current;
   public String cur;

    /**
     * Constructor for the class
     * it is important to call this method in the subclass to
     * store the variables in the formula
     * @param a an indefinite no. of string representing formula
     *          of each variable
     */
   public Variables(String ... a){
   varcom.addAll(Arrays.asList(a));
   }

    /**
     * This method has to be called to store the
     * different definitions of base formula
     * @param definition is an undefined number of string
     *           representing the formula for each variable
     */
   protected void addDefinitions(String ... definition){
       definitions.addAll(Arrays.asList(definition));
   }


    /**
     * checks if the strings passed as the parameter
     * is sufficient to perfom the solving operation
     * @param a a no. of strings
     * @return returns true if the strings passed is exactly the requirement for
     * the solving
     */
   public boolean containsAll(String ... a){
       String curt = current.replace(cur,"");
       if(curt.length() == a.length){
           for(String i:a){
               if(curt.contains(i)){
                   curt.replace(i,"");
                   continue;
               }
               else
                   return false;
           }
           return true;
       }
       else return false;
   }

    /**
     * it checks the inputs an returns the list of variables remaining
     * to complete the solution
     *
     * @param a a no. of strings
     * @return a list of variables remaining to perform the solving operation
     */
    public ArrayList<String> getVariableToComplete(String ... a){
       ArrayList<String> vars = new ArrayList<>();
       String curt = current.replace(cur,"");
       for(String i : a){
           if(curt.contains(i)){
               curt = curt.replace(i,"");
           }

       }

           System.out.println(curt);
           for (char i:curt.toCharArray()) {
               vars.add(i+"");
           }
       return vars;
   }

    /**
     * the function for solving for a value of a variable
     * @setCurrent method has to be called first to set the value
     * as the subject of the base formula
     * @param a a no. of strings
     * @return the evaluated value of the formula
     */
   public double getSolution(String ... a){
       int i = varcom.indexOf(cur);
       System.out.println("i is "+i);
       String exp = definitions.get(i);
       String temp = current;
       current = current.replace(cur,"");
       char[] varr = current.toCharArray();

           int y = 0;
           Eval eval = new Eval();
           for (char j : varr) {
               System.out.println("j is " + j);
               exp = exp.replace(j+"", a[y++] );
           }
          current = temp;
           return eval.evall(exp);

   }

    /**
     * sets the current variable to be made the subject of formula
     * @param curr the variable to be the subject of formula
     */
    public void setCur(String curr){
       this.cur = curr;
    }
}
