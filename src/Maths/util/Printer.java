package Maths.util;

import java.util.ArrayList;
import java.util.Collection;

public class Printer {

    public static void print(ArrayList array , boolean newLine){
        for(Object objj : array){
            System.out.print(objj.toString() + " -> a token");
            if(newLine) System.out.println();
        }
    }
}
