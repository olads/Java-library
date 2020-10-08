package Maths.algebra;

import java.util.ArrayList;

public class Multiplication extends BasicToken {

    public ArrayList<BasicToken> values ;

    public Multiplication(String a,String b){
        super(new Variable(a),new Variable(b),types.MULTIPLICATION);

    }
    public Multiplication(String a){
        super(a,types.MULTIPLICATION);
       Multiplication.this.getValues().add(new Variable(a));

    }


    @Override
    public String getValue(){
        ArrayList<BasicToken> val = getValues();
        try {
            return (val.get(0).getValue() + val.get(1).getValue());
        }
        catch (IndexOutOfBoundsException e){
            return val.get(0).getValue();
        }
    }
}
