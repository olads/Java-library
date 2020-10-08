package Maths.util;

public class StringUtil {


    /**
     * The function is for checking for equity among math tokens
     * @param a
     * @param b
     * @return
     */
    public static boolean equalsMath(String a,String b){
        for(char c: a.toCharArray()){
            if(b.contains(a)){
               return true;
            }
        }
        if(b.equals("")) return true;
        return false;
    }

    public static String remove(char a,String from){
        boolean foundBefore = false;
        StringBuilder builder = new StringBuilder();
        for(char b : from.toCharArray()){
            if( (a == b) && !foundBefore) {
                foundBefore = true;
                continue;
            }
            builder.append(b);
        }
        return builder.toString();
    }

    public static String remove(String a, String from){
        boolean foundBefore = false;
        int pos = 0;
        char[] chars = a.toCharArray();
        int len = chars.length;
        StringBuilder builder = new StringBuilder("");
        for(char charr : from.toCharArray()){
            if(pos!= len  && charr == chars[pos]){
                pos ++;
                continue;
            }

            pos = (pos == len) ? pos: 0 ;
            builder.append(charr);

        }
        return builder.toString();
    }
}
