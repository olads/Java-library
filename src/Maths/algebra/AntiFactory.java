package Maths.algebra;

public class AntiFactory {

    public AntiFactory(){

    }

    public String  getAnti(String desc){
        switch(desc){
            case "sin":
                return "asin";
            case "cos" :
                return "acos";
            case "x" :
                return "/";
            case "/":
                return "x";
            case "+":
                return "-";
            default:
                    return "";

        }
    }
}
