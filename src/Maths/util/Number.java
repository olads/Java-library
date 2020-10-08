package Maths.util;

public class Number {

    public static int[] reduce(String a,String b){
        return reduce(Double.parseDouble(a),Double.parseDouble(b));
    }
    public static int[] reduce(double a,double b){
        int max = Math.max(Format.factorToInt(a),Format.factorToInt(b));
        int[] ans = {Format.conDou(a,max),Format.conDou(b,max),(int) Math.pow(10,max)};
        return ans;
    }
    public static int factor(double a, double b){
        int[] arr = reduce(a,b);
        return factor(arr[0],arr[1],arr[2]);
    }


    public static int factor(int a,int b,int mult){
        int fact = 1;
        int[] divisor = {2,3,5,7};
        for (int g = 0; g<divisor.length; g++ ){
            int gg = divisor[g];

            if(((a%gg) == 0) && ((b%gg) == 0)){

              a /= gg;
              b /= gg;
                fact *= gg;
                --g;
            }

        }
        return fact/mult;
    }




}
