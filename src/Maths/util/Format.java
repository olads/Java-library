package Maths.util;

import Maths.algebra.InvalidOperationException;

public class Format {
    static boolean isInt = true;
    public static int  formatInt(double a) throws InvalidOperationException{
       // System.out.println("format int method ");
        if(checkString(a+"")){
            //System.out.println("from the formattter a is " + (int) a);
            return ((int) a);
        }
        //System.out.println("throwing an exception ");
        throw new InvalidOperationException("Not suitable for integer ");
    }


    public static boolean checkString(String a){
        //System.out.println( "the string to be checked " + a);
        char[] chars = a.toCharArray();
        boolean afterDecimal = false;
        boolean ans = true;
        for( int i = 0; i<a.length(); i++){
            char cur = chars[i];
            if(cur == '.'){
                afterDecimal = true;
            }
            else if(Character.isDigit(cur)){
                if(afterDecimal && ((int) cur) > '0'){
                    isInt = false;
                    return false;
                }
            }

        }
        return ans;
    }

    public static String parseDouble(double a){
        StringBuilder ans = new StringBuilder();
        StringBuilder dou = new StringBuilder();

        for(char ch : Double.toString(a).toCharArray()){
            if(ch == 'E'){
                ans.append(dou);
                dou = new StringBuilder();
            }
            dou.append(ch);
        }
        if(!dou.toString().equals("")){
            ans.append("x").append(dou);
        }

        return ans.toString();
    }
    public static int factorToInt(double a){
        String dou = parseDouble(a);
        boolean afterDecimal = false;
        StringBuilder builder = new StringBuilder();
        int factor = 0;

        for(char ch : dou.toCharArray()){
            if(ch == '.') {
                afterDecimal = true;
                builder = new StringBuilder();
                continue;
            }
            builder.append(ch);
        }
        if(afterDecimal){
            String aa = stripZerob(builder.toString());
            return aa.length();
        }
        return 0;
    }

    public static String stripZeros(String a){
        for(char ch : a.toCharArray()){
            if((int) ch > '0'){
                return a;
            }
           a = a.replaceFirst("0","");
        }
        return a;
    }

    public static String stripZerob(String a){
        if(a.length() == 1){
            if(((int) a.charAt(0)) > '0'){
                return a;
            }
            return "";
        }
        for(int i =  a.length() -1; i>0; i --){
            char ch = a.charAt(i);
            if(((int) ch ) > '0'){
                return a;
            }
            //a = a.substring(0,i);

        }
        return a;
        }

        public static int conDou(double a,int b){
        double pow = Math.pow(10,b);
        a *= pow;
        return (int) a;

        }

    }

