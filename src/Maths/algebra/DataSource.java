package Maths.algebra;


import java.util.ArrayList;

public class DataSource {
    public static ArrayList<String > definitions;
    static{
       definitions = new ArrayList<>();
       functions = new ArrayList<>();
        addFunction("cos");
        String[] a = new String[]{"sin","tan","atan","acos","asin"};
        for(String b : a) addFunction(b);
    }

    public static ArrayList<String > getAtoms(){
        return (definitions.addAll(functions) ? definitions: definitions);
    }



    public static ArrayList<String> functions;

    public static void addDefinition(String a){
        definitions.add(a);
    }
    public static void addFunction(String b){

        functions.add(b);
    }

    public static boolean contains(String a){
        return (definitions.contains(a) || functions.contains(a));
    }

    public static boolean defContains(String a){
        return definitions.contains(a);
    }
    public static boolean funcContains(String a){
        return functions.contains(a);
    }
    public static String getContain(String a){
        String result = "";
        for(String b : getAtoms()){
            if(a.contains(b)) result = (result.length() < b.length()) ? b: result;

        }
        if(result.equalsIgnoreCase("")) return null;
        return result;
    }

}
