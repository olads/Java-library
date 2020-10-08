package Maths.EvaluateAlgebra;

import Maths.algebra.BasicToken;

public abstract class Operation {



    public Types type ;
    public String name;
    public BasicToken token ;

    public Operation(BasicToken tk , Types typ){
        token = tk;
        type = typ;
    }

    public String getName() {
        return name;
    }
    public Types getType(){
        return type;
    }
    public Operation setType(Types typ){
        type = typ;
        return this;
    }

    public abstract BasicToken solve(BasicToken... toks);

}
