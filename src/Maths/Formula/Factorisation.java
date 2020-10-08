package Maths.Formula;
import Maths.Data;

import java.util.ArrayList;

/**A class containing function for factorising expressions
**/
public class Factorisation {
    Data dat = new Data("");
    ArrayList<ArrayList<String>> variables;
    ArrayList<String> tok ;
    String stringToBeFactorise;
/**
 * initializes the variables in the class
 * it is called internally
 */

    protected void initVariables(String a) {
        stringToBeFactorise = a;
            dat.tokk(a);
            tok = dat.tokens;
        variables = new ArrayList<>();
        for (int i = 0; i < dat.tokens.size(); i++) {
            System.out.println(tok.size());

            variables.add(tokenize(tok.get(i)));
        }
    }
    public String factor(){

       int len = tok.size();
       for(int i = 0; i<len; i++){
           for(int j = 0; j<len; j++){

           }
       }
       return "";
   }
   public String compare(int a,int b){
       String ans = "";

    return "";
   }
   public ArrayList<String> tokenize(String a){
       String an = "";
       ArrayList<String> ans = new ArrayList<>();
       char[] str = a.toCharArray();

       if ( (str[0] != '+' || str[0] !='-' || str[0] != 'x' || str[0] != '/')){
           an +="+";
       }
       for(int i =0; i<a.length();i++){
           char k = str[i];

           if(Character.isLetter(k)){
               an+=k;
               ans.add(an);
               System.out.println(an);
               an ="";
           }
           else if(Character.isDigit(k)){
               an +=k;
               if(i+1<a.length() &&(Character.isDigit(str[i+1]) || str[i+1] =='.')){
                   continue;
               }
               else {
                   ans.add(an);
                   System.out.println(an);
                   an = "";
                   continue;
               }
           }
           else if(k == '('){
               an +="(";
              an += dat.brac(a.substring(i+1,a.length()));
               ans.add(an);
               System.out.println(an);
               i+=an.length()-1;
               an = "";
           }

       }
       return ans;
   }
}
