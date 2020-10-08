package Maths.algebra;

import java.util.ArrayList;

public class Tokenizer {

    public static ArrayList<BasicToken> tokenize(String token){
        ArrayList<BasicToken> tokens = new ArrayList<>();
        BasicToken tokk = null;
        StringBuilder tok = new StringBuilder();
        int pos =0;
        char[] arr = token.toCharArray();
        for(;pos<arr.length; pos++){
            char a = arr[pos];
            if(Character.isLetter(a) && a != 'x' ){
                System.out.println("token " + a);
                tok.append(a);
            }
            else if(a == '+' || a == '-' ){
                if(tokk == null){
                    tokk = new Variable(tok.toString());
                }
                tokk.addValues(tok.toString());
                    tokens.add(tokk);
                    tokk = null;
                    //System.out.println("the token is now " + tokens.get(tokens.size()-1).parse());
                    tok = new StringBuilder();
                    tok.append(a);

            }
            else if(a == '/' || a =='^' || a == 'x' ){
                switch (a) {
                    case 'x':
                        tokk = new Multiplication(tok.toString());
                        tok = new StringBuilder();
                        tokens.add(tokk);
                        tokk = null;

                        break;
                    case '/':
                        tokk = new Divide(tok.toString());
                        tok = new StringBuilder();
                        break;
                }
            }
            else if(a == '('){

                //tok.append(a);
                String ann = brac(token.substring(pos+1,token.length()));
                pos += ann.length();
                ann = ann.substring(0,ann.length()-1);
                tokk = new Parenthesis(ann);
                ((Parenthesis) tokk).setFactor(new Variable(tok.toString()));
                tokens.add(tokk);
                System.out.println(tokk.parse() + " the factor is " + ann);
                tokk = null;
                tok = new StringBuilder();

            }
            else{
                tok.append(a);
            }

        }
        if(tokk == null ) {
            tokk  = new Variable(tok.toString());
            tokens.add(tokk);
        }
        else{
            tokk.addValues(tok.toString());
            tokens.add(tokk);
        }

        return tokens;
    }
    public static String brac(String a){
        String ans = "";
        char[] jj = a.toCharArray();
        for(int i=0;i<a.length();i++){
            char s= jj[i];
            if(s == ')'){
                ans +=')';
                return ans;
            }
            else if( s == '('){
                String hh = brac(a.substring(i+1,a.length()));
                ans += hh;
                i += hh.length();
                continue;
            }
            else {
                ans += s;
            }

        }

        return ans;
    }
}
