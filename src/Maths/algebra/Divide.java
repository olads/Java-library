package Maths.algebra;

import java.util.ArrayList;

public class Divide extends BasicToken{

    BasicToken numerator;
    BasicToken denominator;

    public Divide(String numerator,String denominator){
      super(new Variable(numerator),new Variable(denominator),types.FRACTION);
      setValue(numerator,denominator);
    }

    public Divide(BasicToken a,BasicToken b){
        super(a,b,types.FRACTION);
        setValue(a.getValue(),b.getValue());
    }

    public Divide(String a){
        super(a,types.FRACTION);
       values.add(new Variable(a));
    }

    public void setValue(String num,String den){
        System.out.println("Setting the value now ...");
        if(den.equals("1")){
            super.setValue(num);
        }
        else super.setValue(numerator + " / " + denominator);
        System.out.println("the value after been set is " + getValue());
    }

    @Override
    public BasicToken multiply(BasicToken basicToken) {
        if(basicToken instanceof Divide ){
           BasicToken a =  ((Divide) basicToken).getValues().get(0);
           BasicToken b = ((Divide) basicToken).getValues().get(1);
           return new Divide(this.getValues().get(0).multiply(a),this.getValues().get(1).multiply(b));
        }
        else return new Divide(getValues().get(0).multiply(basicToken),getValues().get(1));
    }

    @Override
    public BasicToken add(BasicToken basicToken) {
        BasicToken b = getDenominator().multiply(basicToken.getNumerator());
        BasicToken a = this.getNumerator().multiply(basicToken.getDenominator());
        BasicToken lcm = getDenominator().multiply(basicToken.getDenominator());
        return new Divide(a.add(b),lcm);
    }

    @Override
    public BasicToken subtract(BasicToken basicToken) {
        BasicToken b = getDenominator().multiply(basicToken.getNumerator());
        BasicToken a = this.getNumerator().multiply(basicToken.getDenominator());
        BasicToken lcm = getDenominator().multiply(basicToken.getDenominator());
        return new Divide(a.subtract(b),lcm);
    }

    @Override
    public BasicToken divide(BasicToken basicToken) {

        if(basicToken instanceof Divide ){
            BasicToken a =  ((Divide) basicToken.getValues().get(0));
            BasicToken b = ((Divide) basicToken.getValues().get(1));
            return new Divide(this.getValues().get(0).multiply(b),this.getValues().get(0).multiply(a));
        }
        else return new Divide(getValues().get(0),getValues().get(1).multiply(basicToken));
    }

    @Override
    public String getValue(){
        System.out.println("Get value function is called here ");
        if(getNo(getDenominator().getValue()) == "1"){
            System.out.println((getNumerator() == null )+ " is it that the get Numerator fucntion returns fnull");
            return (getNumerator().getValue() );
        }
        else if(getNo(getDenominator().getValue()) == "-1"){
            return (getNumerator().multiply(new Variable("-1")).value);
        }
        else{
            return (getNumerator().getValue() + " / "  + getDenominator().getValue());
        }

    }

    @Override
    public BasicToken getFactor(BasicToken basicToken){
        String a = getNumerator().getValue();
        String b = getDenominator().getValue();
        String c = basicToken.getNumerator().getValue();
        String d = basicToken.getDenominator().getValue();
        BasicToken var = super.getFactor(a,c);
        BasicToken varr = super.getFactor(b,d);
        return new Divide(var,varr);

    }

    public BasicToken reduce(){
        return ((Divide) getNumerator().divide(getDenominator()));
    }

}
