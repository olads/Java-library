package Maths.EvaluateAlgebra;

import Maths.algebra.BasicToken;

public class Token {

    public Types type ;
    public String name;
    public BasicToken token ;
    public Token(BasicToken tok, Types tp){
        this.token = tok;
        type = tp;
    }
    public String getName() {
        return name;
    }
    public Types getType(){
        return type;
    }

}
