package Maths.algebra;

import java.util.ArrayList;

public class Parenthesis extends BasicToken {
    String vallue;
    BasicToken factor = new Variable("1");
    public Parenthesis(String value){
        super(value,types.PARENTHESIS);
        this.vallue = value;

    }


    @Override
    public String getValue(){
        return vallue;
    }

    @Override
    public String parse(){
        System.out.println("the type of factor is " + factor.getType());
        System.out.println("the factor is null " + (factor.getValue()  == null));

        String fac = (factor.getValue().equals("1") ? "" : factor.getValue());
        StringBuilder str = new StringBuilder(fac);
        str.append("(");
      for(BasicToken tok : Tokenizer.tokenize(vallue)){
          str.append(tok.parse());
      }
      str.append(")");
      return str.toString();
    }

    @Override
    public BasicToken add(BasicToken basicToken){
        BasicToken add = basicToken;
        for(BasicToken tok : Tokenizer.tokenize(vallue)){
            add = add.add(tok);
        }
        return add;
    }

    @Override
    public BasicToken subtract(BasicToken basicToken){
        BasicToken subtract = basicToken;
        for(BasicToken tok : Tokenizer.tokenize(vallue)){
            subtract = subtract.subtract(tok);
        }
        return subtract;
    }

    @Override
    public BasicToken multiply(BasicToken basicToken){
        BasicToken basic = null;
        ArrayList<BasicToken> list = Tokenizer.tokenize(vallue);
        for(BasicToken tok : list){
            System.out.println(tok.getValue());
            System.out.println(vallue);
            if(basic == null)
          basic = basicToken.multiply(tok);
            else{
                basic = basic.add(basicToken.multiply(tok));
            }
        }
        return new Parenthesis(basic.getValue());
    }
    @Override
    public BasicToken divide(BasicToken basicToken){

        for(BasicToken tok : Tokenizer.tokenize(vallue)){
            vallue = vallue.replaceFirst(tok.getValue(),(basicToken.divide(tok).getValue()));
        }
        return new Parenthesis(vallue);
    }

    public Parenthesis setFactor(BasicToken basicToken){
        this.factor = basicToken;
        return this;
    }

    public BasicToken openBrac(){
        BasicToken paren = factor;
        return multiply(factor);
    }

    public static Parenthesis createBrac(BasicToken basicA,BasicToken basicB){
        Parenthesis parent = new Parenthesis(basicA.getValue());
        parent.setFactor(basicB);
        return parent;
    }



}
