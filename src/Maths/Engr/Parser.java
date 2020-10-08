package Maths.Engr;

import java.util.HashMap;

public class Parser {

    String[] data;
    private HashMap<String,String> parsed;
    public Parser(String ... a){
        data = a;
        parsed = new HashMap<>();
    }

    public String[] getKeys(){
        String[] ans = new String[data.length];
        int pos =0;
        for(String a : data){
            String[] res = separate(a);
            ans[pos++] = res[0];
        }
        return ans;
    }

    public HashMap<String,String> getParsed(){
        for(String a:data){
            String[] res = separate(a);
            parsed.put(res[0],res[1]);
        }
        return parsed;
    }
    public String[] separate(String a){
        return a.split("=");
    }
}
