package Maths.Evaluation;

public class MyCharacter {
    public type typ;
    public String value;

    MyCharacter(String a,type b){
        value = a;
        typ = b;
    }
    public enum type{
         NUMBER,
         OPERATOR ,
        FUNCTION,
       PARENTHESIS,
        CONSTANTS
    }
    public type getType(){
        return this.typ;
    }
    public String getValue(){
        return this.value;
    }

}
