package Maths.Formula;

public abstract class Token {

    public type type ;
    private String value;

    public Token(String value,type typ){
        this.value = value;
        type = typ;
    }

    public type getType(){
        return this.type;
    }

    public String getValue(){
        return this.value;
    }

    public abstract Token differentiate();


    enum type{
        TRIG,
        LOG,
        POLYNOMIAL,
        CONSTANTS
    }
}
