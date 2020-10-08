package Maths.algebra;

import java.util.ArrayList;

/**
 *
 *A class for simplifying expressions in the equations
 */
public class Simplify {

    public static ArrayList<BasicToken> simplifyAdd(ArrayList<BasicToken> tokens){
        ArrayList<BasicToken> result = new ArrayList<>();
        int size = tokens.size();
        for(int i = 0 ; i < (size = tokens.size()); i++){
            for (int j = 0 ; j < (size = tokens.size()); j++){
                System.out.println(i + ">>i " + j + ">>j");
                if(tokens.size() <=1){
                    return tokens;
                }
                if(j == i) continue;
                BasicToken tok = tokens.get(i);
                BasicToken tk = tokens.get(j);
                System.out.println(i + " The first value is " + tok.getValue());
                System.out.println(j + " The Second value is " + tk.getValue());

                if(tok.checkAddSub(tk)){
                    BasicToken token = tok.add(tk);
                    System.out.println("result of addition is "+ token.getValue());
                    tokens.remove(tok);
                    tokens.remove(tk);
                    tokens.add(Math.min(i,j),token);
                }
            }
        }
        return tokens;
    }

    public static ArrayList<BasicToken> simplifyFactor(ArrayList<BasicToken> tokens){
        int size = tokens.size();
        System.out.println("The size of the tokens is " + size);
        for(int i = 0 ; i < (size = tokens.size()); i++){
            for (int j = 0 ; j < (size = tokens.size()); j++){
                System.out.println(i + ">>i " + j + ">>j");
                if(tokens.size() <=1){
                    return tokens;
                }
                if(j == i) continue;
                BasicToken tok = tokens.get(i);
                BasicToken tk = tokens.get(j);
                System.out.println(i + " The first value is " + tok.getValue());
                System.out.println(j + " The Second value is " + tk.getValue());

                if(tok.checkFactor(tk.getValue())){
                    BasicToken token = tok.factorOut(tk);
                    System.out.println("result of factorization is "+ token.getValue());
                    tokens.remove(tok);
                    tokens.remove(tk);
                    tokens.add(Math.min(i,j),token);
                }
            }
        }
        return tokens;
    }

    public static ArrayList<BasicToken> simplify(ArrayList<BasicToken> tokens){
        return simplifyFactor(simplifyAdd(tokens));
    }
}
