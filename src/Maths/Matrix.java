package Maths;
import Maths.Eval.*;

import java.util.ArrayList;
import java.util.Scanner;


class data  {
    public String[][] dat;
    public double[][] read;
    public int g,y;
    Scanner inp=new Scanner(System.in);
    data(int row,int col){
        dat=new String[row][col];
        read=new double[row][col];
        g=row;y=col;
    }

   public void getData(){
      Eval bn=new Eval();
        for(int q=0;q<dat.length;q++){
            dat[q]=inp.next().split(",");
            for(int w=0;w<dat[q].length;w++) {
                read[q][w]=bn.simpleEval(dat[q][w]);
           /*     System.out.println(read[q][w]);*/
                 }
            }
   }
   public void printArr(){
       for(int q=0;q<dat.length;q++){

           for(int w=0;w<dat[q].length;w++) {

               System.out.print(read[q][w]+" ");
           }
           System.out.println();
       }
   }

}
public class Matrix {
   // public String b;
    public data ans;
   /* Matrix(String a){
        b=a;
    }*/
    public void parseQ(String que){

    }
    public data addMat(data a,data j){
        ans=new data(a.g,a.y);

        if((a.g==j.g) && (a.y==j.y)){
        for(int z=0;z<a.g;z++){
            for(int h=0;h<j.y;h++){
                ans.read[z][h]=(a.read[z][h]+j.read[z][h]);
            }
        }
        }
        return ans;
    }    public data subMat(data a,data j){
        ans=new data(a.g,a.y);

        if((a.g==j.g) && (a.y==j.y)){
        for(int z=0;z<a.g;z++){
            for(int h=0;h<j.y;h++){
                ans.read[z][h]=(a.read[z][h]-j.read[z][h]);
            }
        }
        }
        return ans;
    }
    public data multiply(data a,data b){
        ans=new data(a.g,b.y);
        if(a.y==b.g){
            for(int w=0;w<a.g;w++){
                for(int i=0;i<b.y;i++){
                    double[] temp=new double[b.g];

                    for(int j=0;j<b.g;j++){
                        temp[j]=b.read[j][i];
                    }
                    ans.read[w][i]=mult(a.read[w],temp);

                }
            }

        }



        return ans;
    }

    public data multiply(data a,double b){
        data ans=new data(a.g,a.y);
        for (int i=0;i<a.g;i++){
            for (int j=0;j<a.y;j++){
                ans.read[i][j] = a.read[i][j] * b;
            }
        }

        return ans;
    }

    public double mult(double[] a,double[] b) {

        double ann = 0;
        for (int i = 0; i < a.length; i++) {
            ann += a[i] * b[i];
        }
        return ann;
    }

    public data divide(data a,double b){
            data ans=new data(a.g,a.y);
        for(int i=0;i<a.g;i++) {
            for (int j = 0; j < a.y; j++) {
                ans.read[i][j]=a.read[i][j] /b;
            }
        }

            return ans;
    }

    public data getco(data a,int k,int hh){
        data ans;
        ArrayList<Double> cofac=new ArrayList<>();
        if(a.g == a.y && a.g!=1){

            for(int i =0;i<a.g;i++){
                if(i!=hh) {
                    for (int j = 0; j < a.y; j++) {
                        if (j != k) {
                            cofac.add(a.read[i][j]);
                        } else {
                            continue;
                        }


                    }
                }
            }
        }
        else{
            cofac.add(a.read[0][0]);
        }
        ans=convert(cofac);

        return  ans;
    }


    public data convert(ArrayList<Double> a){
        int g=a.size();
        int gh=(int)Math.sqrt(g);
        data ans=new data(gh,gh);
        int y=0;
        for(int i=0;i<gh;i++){
            for(int j=0;j<gh;j++){
                ans.read[i][j]=a.get(y++);
            }
        }
        return ans;
    }

    public double det(data a){
        double ans=0;
        if(a.g==a.y){
            if(a.g==1){
                ans=a.read[0][0];
            }
            else{
                for(int g=0;g<a.y;g++){
                    int rr=1;
                    if(g%2==1){
                       rr  *=-1;
                    }
                    ans += rr * a.read[0][g] * det(getco(a,g,0));
                }
            }
        }
        return ans;
    }

    public data transpose(data a){
        data ans=new data(a.g,a.y);
        for(int i=0;i<a.g;i++){
            for(int j=0;j<a.y;j++){
                ans.read[i][j]=a.read[j][i];
            }

        }
        return ans;
    }

    public data adjoint(data a){
        data ans=new data(a.g,a.y);
        int rr=1;
        for(int i=0;i<a.g;i++) {
            for (int j = 0; j < a.y; j++) {
                rr=1;
                if((i+j)%2==1){
                    rr*=-1;
                }
                ans.read[i][j]=rr*det(getco(a,j,i));

            }
        }
        //ans=transpose(ans);
        return transpose(ans);
    }
    public data inverse(data a){
        data ans=adjoint(a);
        ans=divide(ans,det(a));

        return ans;
    }

}
