package Maths;

import java.util.ArrayList;

/**
 * class for tokenizing string mostly based on operators
 */

public class Data {
    public String myString;
    //the string data for plus
    public String PLUS="+";
   /*
   //the string data for minus*/
    public String MINUS = "-";
    //the string data for multiplication
    public String MULT = "x";
    //the string data for division
    public String DIV = "/";
    //the string data for power
    public String POW = "^";
    public ArrayList<String> tokens = new ArrayList<>();

   public Data(String a){
        myString = a;

    }
/**
 * Function to separate the parenthesis and the  expression in it
 * @param  a cannot be a nullable String  */
    public String  brac(String a){
        String ans = "";
        char[] jj = a.toCharArray();
        for(int i=0;i<a.length();i++){
            char s= jj[i];
            if(s == ')'){
                ans +=')';
                return ans;
            }
            else if( s == '('){
                ans +=brac(a.substring(i+1,a.length()));
                continue;
            }
            else {
                ans += s;
            }

        }

        return ans;
    }
/**
 * function to parse strings and return the arraylist
 * of the tokenized String
 * @param b must not be a null String */
    public String tokk(String b){
        String hh = "";
        char[] txt = b.toCharArray();
        /**
         * loop through the String to get the separated String base on
         *  the +,-,x,/*/
        for (int i = 0 ;i <b.length();i++){
            char a = txt[i];
            if(Character.isDigit(a) || a == '.'){
                hh+=a;
                continue;
            }
            else if (a =='+' || a == '-' || a == 'x' || a == '/'){
               tokens.add(hh);
                hh="" +a;
                continue;
            }
            else if(a == '('){
                hh +="(";
                hh += brac(b.substring(i+1,b.length()));
               tokens.add(hh);
               i+=hh.length()-1;
               hh = "";
                }
            else if(Character.isLetter(a)){
                hh += a;
                int c = i+1;
                if(c<b.length() && txt[c]=='('){
                    hh +='(';
                   hh += brac(b.substring(c+1,b.length()));
                    i+=hh.length()-1;
                    tokens.add(hh);
                    hh ="";
                    continue;

                }
                else {
                    continue;
                }

            }

            }

        if(!hh.equals(""))
        tokens.add(hh);
        return "";
        }
    }

