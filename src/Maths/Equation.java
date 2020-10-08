package Maths;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Maths.Eval;

public class Equation extends Eval {

    public String getcoe(String a){
        String ans="";
        Pattern pat = Pattern.compile("[-]?\\d+[\\.]?[\\d]*");
        Pattern bn=Pattern.compile("[A-Za-z]");
        Matcher bb=bn.matcher(a);
        Matcher match=pat.matcher(a);
        //ans=match.group();
            if(match.find()) {
                //System.out.println(match.find());
                ans = match.group();
            }
            else{
                if(a.contains("-")){
                    ans="-1";
                }
                else {
                    ans = "1";
                }
            }

        return  ans;
    }
    public String linear(String a){
        String[] hh=a.split("=");
        Pattern pat=Pattern.compile("[-]?[\\+]?\\d+[.]?[\\d]*");
        Pattern patt=Pattern.compile("[-]?[\\+]?\\d+[.]?[\\d]*");

       String[] fir=sumvar(hh[0],pat);
       String[] sfir=sumv(fir[1],patt);
       String[] sec=sumvar(hh[1],pat);
       String[] ssec=sumv(sec[1],patt);
       double i=simpleEval(fir[0]),j=simpleEval(sec[0]),k=simpleEval(sfir[0]),l=simpleEval(ssec[0]);
       //System.out.println("i= "+i+"j= "+j+"k= "+k+"l= "+l +"fir1= "+fir[1]+"sec2= "+sec[1]);

        //System.out.println((i-j)+"x = " + (l-k));
        System.out.println("The answer is : x = " + (l-k)/(i-j));
        return "";

    }
    public String[] sumvar(String a,Pattern pat){
        String hh="";
       double sumco1 =0;
        String[] ans=new String[2];

        Matcher mat=pat.matcher(a);
        hh=a;
        while(mat.find()){
            a=hh;
            String h=mat.group();
            System.out.println("h= "+h);
            sumco1 += simpleEval(getcoe(h));
            hh=a.replace(h,"");

        }
        System.out.println(sumco1);
        ans[0] =String.valueOf(sumco1); ans[1]= hh;


        return ans;
    }
    public String[] sumv(String a,Pattern pat){
        String hh="";
       double sumco1 =0;
        String[] ans=new String[2];
        Matcher mat=pat.matcher(a);
        hh=a;
        while(mat.find()){
            String h=mat.group();
            sumco1 += simpleEval(h);
            hh=hh.replace(h,"");

        }
        System.out.println(sumco1);
        ans[0] =String.valueOf(sumco1); ans[1]= hh;


        return ans;
    }

    public double[] quad(String a){
        double[] ans=new double[3];
        String[] hh=a.split("=");
        Pattern xx=Pattern.compile("[-]?[\\+]?[\\d]*[\\.]?[\\d]*x²");
         Pattern x=Pattern.compile("[-]?[\\+]?[\\d]*[\\.]?[\\d]*x");
         Pattern cont=Pattern.compile("[-]?[\\+]?[\\d]*[\\.]?[\\d]*");
         double i,j,k,l,m,n,o;
         String[] xx1=sumvar(hh[0],xx);
         String[] xx2=sumvar(hh[1],xx);
         String[] x1=sumvar(xx1[1],x);
         String[] x2=sumvar(xx2[1],x);
         String[] con1=sumv(x1[1],cont);
         String[] con2=sumv(x2[1],cont);
         System.out.println(xx1[1]+" " +xx2[1]+" "+x1[1]+" "+x2[1]);
         i=simpleEval(xx1[0]);
         j=simpleEval(xx2[0]);
         k=simpleEval(x1[0]);
         l=simpleEval(x2[0]);
         m=simpleEval(con1[0]);
         o=simpleEval(con2[0]);
         System.out.println((i-j)+"x²"+ (k-l)+"x"+(m-o));
         ans[0] =(i-j);
         ans[1] = k-l;
         ans[2] = m-o;



    return ans;
    }
    public double[] evalquad(String q){
        double[] val=quad(q);

        double a=val[0],b=val[1],c=val[2];
        double df = Math.pow(b, 2) - (4 * a * c);
        double x1= (-b +Math.sqrt(df))/(2*a);
        double x2= (-b -Math.sqrt(df))/(2*a);
        System.out.println("answers are :"+x1+" and "+x2);

        return val;
    }

    public double simul(String a,String b){
        Pattern p1=Pattern.compile("[-]?[\\+]?[\\d]*[\\.]?[\\d]*x");
         Pattern p2=Pattern.compile("[-]?[\\+]?[\\d]*[\\.]?[\\d]*y");
         Pattern p3=Pattern.compile("[-]?[\\+]?[\\d]*[\\.]?[\\d]*");
         String[] aa=a.split("=");
         String[] bb=b.split("=");
         //Eqn 1
         //look for x in both sides in eqn 1
         String[] f11=sumvar(aa[0],p1);
         String[] f12=sumvar(aa[1],p1);
         // look for y i both sides in eqn 1
         String[] f21=sumvar(f11[1],p2);
         String[] f22=sumvar(f12[1],p2);
         //look for const in both sides in eqn 1
         String[] f31=sumv(f21[1],p3);
         String[] f32=sumv(f22[1],p3);
         //Enq 2
         //look for x in booth sides in eqn 2
          String[] g11=sumvar(bb[0],p1);
          String[] g12=sumv(bb[1],p1);
          //look for y in both sides in eqn2
         String[] g21=sumvar(g11[1],p2);
         String[] g22=sumvar(g12[1],p2);
         //look for const in eqn 2
         String[] g31=sumv(g21[1],p3);
         String[] g32=sumv(g22[1],p3);
         //sum of variable constants to have the equation in the form x+y+2=0
         double i=(simpleEval(f11[0]))-(simpleEval(f12[0])),j=(simpleEval(f21[0]))-(simpleEval(f22[0])),k=simpleEval(f32[0]) - simpleEval(f31[0]);
         double l=simpleEval(g11[0])-simpleEval(g12[0]),m=simpleEval(g21[0])-simpleEval(g22[0]),n=simpleEval(g32[0])-simpleEval(g31[0]);

         data an=new data(2,2);
         data ann=new data(2,1);
         an.read[0][0]=i;
         an.read[0][1]=j;
         an.read[1][0]=l;
         an.read[1][1]=m;
         ann.read[0][0]=k;
         ann.read[1][0]=n;
         Matrix sol=new Matrix();
         data ans=sol.multiply(sol.inverse(an),ann);

         System.out.println("The simplified eqn 1= " + ans.read[0][0]);
         System.out.println("The simplified eqn 2= " +ans.read[1][0]);


        return 0;
    }

    public double simul3(String a,String b,String c){
        Pattern p1=Pattern.compile("[-]?[\\+]?[\\d]*[\\.]?[\\d]*x");
        Pattern p2=Pattern.compile("[-]?[\\+]?[\\d]*[\\.]?[\\d]*y");
        Pattern p4=Pattern.compile("[-]?[\\+]?[\\d]*[\\.]?[\\d]*z");
        Pattern p3=Pattern.compile("[-]?[\\+]?[\\d]*[\\.]?[\\d]*");

        String[] aa=a.split("=");
        String[] bb=b.split("=");
        String[] cc=c.split("=");

        //Eqn 1
        //look for x in both sides in eqn 1
        String[] f11=sumvar(aa[0],p1);
        String[] f12=sumvar(aa[1],p1);
        // look for y i both sides in eqn 1
        String[] f21=sumvar(f11[1],p2);
        String[] f22=sumvar(f12[1],p2);
        //look for z in both sides
        String[] f31=sumvar(f21[1],p4);
        String[] f32=sumvar(f22[1],p4);

        //look for const in both sides in eqn 1
        String[] f41=sumv(f31[1],p3);
        String[] f42=sumv(f32[1],p3);
        //Enq 2
        //look for x in booth sides in eqn 2
        String[] g11=sumvar(bb[0],p1);
        String[] g12=sumv(bb[1],p1);
        //look for y in both sides in eqn2
        String[] g21=sumvar(g11[1],p2);
        String[] g22=sumvar(g12[1],p2);
        // look for z in both sides
        String[] g31 = sumvar(g21[1],p4);
        String[] g32=sumvar(g22[1],p4);
        //look for const in eqn 2
        String[] g41=sumv(g31[1],p3);
        String[] g42=sumv(g32[1],p3);
        //Enq 3
        //look for x in booth sides in eqn 2
        String[] h11=sumvar(cc[0],p1);
        String[] h12=sumv(cc[1],p1);
        //look for y in both sides in eqn2
        String[] h21=sumvar(h11[1],p2);
        String[] h22=sumvar(h12[1],p2);
        // look for z in both sides
        String[] h31 = sumvar(h21[1],p4);
        String[] h32=sumvar(h22[1],p4);
        //look for const in eqn 2
        String[] h41=sumv(h31[1],p3);
        String[] h42=sumv(h32[1],p3);
        //sum of variable constants to have the equation in the form x+y+2=0
        double i=(simpleEval(f11[0]))-(simpleEval(f12[0])),j=(simpleEval(f21[0]))-(simpleEval(f22[0])),k=simpleEval(f31[0]) - simpleEval(f32[0]);
        double l=simpleEval(g11[0])-simpleEval(g12[0]),m=simpleEval(g21[0])-simpleEval(g22[0]),n=simpleEval(g31[0])-simpleEval(g32[0]);
        double o=simpleEval(h11[0])-simpleEval(h12[0]),p=simpleEval(h21[0])-simpleEval(h22[0]),q=simpleEval(h31[0])-simpleEval(h32[0]);
        double const1=simpleEval(f42[0])-simpleEval(f41[0]),const2=simpleEval(g42[0])-simpleEval(g41[0]),const3=simpleEval(h42[0])-simpleEval(h41[0]);
        data an=new data(3,3);
        data ann=new data(3,1);
        Matrix sol=new Matrix();
        an.read[0][0]=i;an.read[0][1]=j;an.read[0][2]=k;an.read[1][0]=l;an.read[1][1]=m;an.read[1][2]=n;
        an.read[2][0]=o;an.read[2][1]=p;an.read[2][2]=q;
        ann.read[0][0]=const1;ann.read[1][0]=const2;ann.read[2][0]=const3;
        data ans=sol.multiply(sol.inverse(an),ann);

        System.out.println(" X = " +ans.read[0][0]);
        System.out.println(" Y = " +ans.read[1][0]);
        System.out.println(" Z = " +ans.read[2][0]);




        return 0;
    }

    public double simul4(String a,String b,String c,String d){

        Pattern p1=Pattern.compile("[-]?[\\+]?[\\d]*[\\.]?[\\d]*a");
        Pattern p2=Pattern.compile("[-]?[\\+]?[\\d]*[\\.]?[\\d]*b");
        Pattern p4=Pattern.compile("[-]?[\\+]?[\\d]*[\\.]?[\\d]*c");
        Pattern p5=Pattern.compile("[-]?[\\+]?[\\d]*[\\.]?[\\d]*d");
        Pattern p3=Pattern.compile("[-]?[\\+]?[\\d]*[\\.]?[\\d]*");

        String[] aa=a.split("=");
        String[] bb=b.split("=");
        String[] cc=c.split("=");
        String[] dd=d.split("=");
        //Eqn 1
        //look for x in both sides in eqn 1
        String[] f11=sumvar(aa[0],p1);
        String[] f12=sumvar(aa[1],p1);
        // look for y i both sides in eqn 1
        String[] f21=sumvar(f11[1],p2);
        String[] f22=sumvar(f12[1],p2);
        //look for z in both sides
        String[] f31=sumvar(f21[1],p4);
        String[] f32=sumvar(f22[1],p4);

        //look for 4th in both sides in eqn 1
        String[] f41=sumvar(f31[1],p5);
        String[] f42=sumvar(f32[1],p5);
        // look for const in both eqns
        String[] f51=sumv(f41[1],p3);
        String[] f52=sumv(f42[1],p3);
        //Enq 2
        //look for x in booth sides in eqn 2
        String[] g11=sumvar(bb[0],p1);
        String[] g12=sumv(bb[1],p1);
        //look for y in both sides in eqn2
        String[] g21=sumvar(g11[1],p2);
        String[] g22=sumvar(g12[1],p2);
        // look for z in both sides
        String[] g31 = sumvar(g21[1],p4);
        String[] g32=sumvar(g22[1],p4);
        //look for const in eqn 2
        String[] g41=sumvar(g31[1],p5);
        String[] g42=sumvar(g32[1],p5);

        String[] g51=sumv(g41[1],p3);
        String[] g52=sumv(g42[1],p3);
        //Enq 3
        //look for x in booth sides in eqn 2
        String[] h11=sumvar(cc[0],p1);
        String[] h12=sumv(cc[1],p1);
        //look for y in both sides in eqn2
        String[] h21=sumvar(h11[1],p2);
        String[] h22=sumvar(h12[1],p2);
        // look for z in both sides
        String[] h31 = sumvar(h21[1],p4);
        String[] h32=sumvar(h22[1],p4);
        //look for const in eqn 2
        String[] h41=sumvar(h31[1],p5);
        String[] h42=sumvar(h32[1],p5);

        String[] h51=sumv(h41[1],p3);
        String[] h52=sumv(h42[1],p3);
        // eqn 4
        //look for x in booth sides in eqn 2
        String[] i11=sumvar(dd[0],p1);
        String[] i12=sumv(dd[1],p1);
        //look for y in both sides in eqn2
        String[] i21=sumvar(i11[1],p2);
        String[] i22=sumvar(i12[1],p2);
        // look for z in both sides
        String[] i31 = sumvar(i21[1],p4);
        String[] i32=sumvar(i22[1],p4);
        //look for const in eqn 2
        String[] i41=sumvar(i31[1],p5);
        String[] i42=sumvar(i32[1],p5);

        String[] i51=sumv(i41[1],p3);
        String[] i52=sumv(i42[1],p3);
        //sum of variable constants to have the equation in the form x+y+2=0
        double i=(simpleEval(f11[0]))-(simpleEval(f12[0])),j=(simpleEval(f21[0]))-(simpleEval(f22[0])),k=simpleEval(f31[0]) - simpleEval(f32[0]),kl=simpleEval(f41[0])-simpleEval(f42[0]);
        double l=simpleEval(g11[0])-simpleEval(g12[0]),m=simpleEval(g21[0])-simpleEval(g22[0]),n=simpleEval(g31[0])-simpleEval(g32[0]),no=simpleEval(g41[0])-simpleEval(g42[0]);
        double o=simpleEval(h11[0])-simpleEval(h12[0]),p=simpleEval(h21[0])-simpleEval(h22[0]),q=simpleEval(h31[0])-simpleEval(h32[0]),qr=simpleEval(h41[0])-simpleEval(h42[0]);
        double r=simpleEval(i11[0])-simpleEval(i12[0]),s=simpleEval(i21[0])-simpleEval(i22[0]),t=simpleEval(i31[0])-simpleEval(i32[0]),tu=simpleEval(i41[0])-simpleEval(i42[0]);

        double const1=simpleEval(f52[0])-simpleEval(f51[0]),const2=simpleEval(g52[0])-simpleEval(g51[0]),const3=simpleEval(h52[0])-simpleEval(h51[0]),const4=simpleEval(i52[0])-simpleEval(i51[0]);
        data an=new data(4,4);
        data ann=new data(4,1);
        Matrix sol=new Matrix();
        an.read[0][0]=i;an.read[0][1]=j;an.read[0][2]=k;an.read[0][3]=kl;an.read[1][0]=l;an.read[1][1]=m;an.read[1][2]=n;an.read[1][3]=no;
        an.read[2][0]=o;an.read[2][1]=p;an.read[2][2]=q;an.read[2][3]=qr; an.read[3][0]=r;an.read[3][1]=s;an.read[3][2]=t;an.read[3][3]=tu;
        ann.read[0][0]=const1;ann.read[1][0]=const2;ann.read[2][0]=const3;ann.read[3][0]=const4;
        data ans=sol.multiply(sol.inverse(an),ann);
       /* sol.inverse(an).printArr();
        an.printArr();
        ann.printArr();*/
        System.out.println("answer are: "+ans.read[0][0]+" and "+ans.read[1][0]+" ans "+ans.read[2][0]+" ans "+ans.read[3][0]);



        return 0;
    }


}
