package Maths.Evaluation;

public interface Operate{
    default double operate(String a,String c){
        return 0;
    }
    default double operate(String a){
        return 0;
    }
    default void print(){
        System.out.println("Hey there !");
    }

}
