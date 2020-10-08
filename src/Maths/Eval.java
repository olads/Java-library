package Maths;

import java.util.ArrayList;


/*
⁻¹  ²  ³  ⁴  ⁵  ⁶  ⁷  ⁸  ⁹  ⁰  ⁻   ⃗     ̂     ̅   ̇   ⁺  ˣ    ₁ ₂ ₃ ₄ ₅ ₆ ₇ ₈ ₉ ₀

        ʷ   ᵉ   ʳ  ᵗ   ʸ   ᵘ   ⁱ   ᵒ   ᵖ   ᵃ   ˢ    ᵈ    ᶠ    ᵍ   ʰ   ʲ   ᵏ  ˡ   ᶻ   ˣ  ᶜ  ᵛ  ᵇ  ⁿ   ᵐ   î  ĵ   k̂  k̂

        ς   ε  ρ   τ   υ   θ   ι   ο   π   α   σ    δ   φ  ;γ   η   ξ  κ  λ  ζ  χ  ψ  ω   β  ν  μ

        Σ  ∩  ∇  ⍉   ∠  ∡  ∢    ⏊    ⍊   ⏚    ∞    ≡   ≠    ∝   ≈    ∴     ∵   ≤   ≥   ±     ∫   ∮   ∯   ∰   ∲   ∳  Z̅Y̅X̅

*/
public class Eval {
   public boolean rad=false;
   public boolean deg=true;
	public double evall(String a) {
		double ans = 0;
		System.out.println(a);
		if (a.contains("("))  {
			try {
				ans=simpleEval(a);
			}
			catch(Exception e) {
				//String asd=parseBrac(a);
				//System.out.println("String return in eval "+asd);
				ans = simpleEval(parseBrac(a));
			}
//			}
//			
//		
//		} else if (a.equals("")) {
//			System.out.print("The ans is : ");
//			ans = Double.parseDouble(a);
		}
		
		
		else {
			
			ans = simpleEval(a);
		}

		return ans;
	}

	public String parseBrac(String a) {
		String ans = "";
		ab="";
      /*  System.out.println("parse a=="+a);*/
		if(!a.contains("(")){
		    return a;
        }
         else{
            String[] ann=count(a, '(');
            a=ann[2];
            int i=Integer.valueOf(ann[0]),j=Integer.valueOf(ann[1]);
            a=checkString(a,i,j);
			//System.out.println(" a is  "+a);
            ans=parseBrac(a);

        }

		return ans;
	}
	 String ab;
	public String checkString(String a,int b,int c) {
        String ans = a;
        int i, j = 0, k = 0, m = 0;
        double y = 1;


        if (b < c && a.length() >= c && a.contains("(") &&(a.charAt(b) =='(' && a.charAt(c)==')')) {
            char[] and = a.toCharArray(), ann = new char[b], an = new char[a.length() - c];
            String d = "";

            d = reverse(new String(ann));
            String temp = a.substring(b + 1, c);

            if (!temp.contains("(") && !temp.contains(")")) {
                if (b != 0) {
                    for (i = b - 1; i >= 0; i--) {

                        if (and[i] == '+' || and[i] == '-' || and[i] == 'x' || and[i] == '/' || and[i] == '(') {
                            break;
                        }

                        ann[j++] = and[i];//preOperator

                    }
                    for (k = c + 1; k < a.length(); k++) {

                        if (and[k] == '+' || and[k] == '-' || and[k] == 'x' || and[k] == '/' || (and[k] == ')' && and[k - 1] == ')')) {
                            break;
                        }
                        an[m++] = and[k];
                    }
                    String hhh = (new String(an)).trim();
                    int yy = (hhh.length() == 0) ? 0 : hhh.length();
                    y = (simpleEval(hhh) == 0) ? 1 : simpleEval(hhh);

                    String tts = reverse(new String(ann)).trim();
                    //System.out.println("they:" + reverse(new String(ann)).trim() + "===" + hhh + "==");

                    if (tts.contains("sin") || tts.contains("cos") || tts.contains("tan") || tts.contains("log") || tts.contains("sin⁻¹") || tts.contains("cos⁻¹") || tts.contains("tan⁻¹")) {

                        String init = a.substring(a.indexOf(tts), c + yy+1);
                       // System.out.println(a+":the length of an:"+y);
                        ans = a.replace(init, String.valueOf(simpleEval(a.substring(a.indexOf(tts), c + 1))*y));
                        //System.out.println("ggg/" + ans + "/");

                    } else {


                        //System.out.println("gross/" + reverse(new String(ann)) + "===" + new String(an) + "===" + String.valueOf((simpleEval(reverse(new String(ann)))) * simpleEval(temp) * y) + "/");

                        ans = String.valueOf(simpleEval((c + 1 <= a.length() - 1) ? a.replace(a.substring(a.indexOf(tts), c + yy), String.valueOf((simpleEval(tts)) * evall(temp) * y)) : a.replace(a.substring(a.indexOf(tts)), String.valueOf((simpleEval(tts)) * simpleEval(temp) * y))));
                        //System.out.println("check ans === " + ans);

                    }
                } else {
                    for (k = c + 1; k < a.length(); k++) {

                        if (and[k] == '+' || and[k] == '-' || and[k] == 'x' || and[k] == '/' || (and[k]==')' && and[k-1]==')')) {
                            break;
                        } else {
                            an[m++] = and[k];
                        }
                    }
                    y = (simpleEval(new String(an)) == 0) ? 1 : simpleEval(new String(an));
                    String hhh = (new String(an)).trim();
                    int yy = (hhh.length() == 0) ? 0 : hhh.length();
                    y = (simpleEval(hhh) == 0) ? 1 : simpleEval(hhh);
                    String hh = new String(an);
                    //System.out.println("values:" + y+"the length"+yy);
                    ans = a.replace(a.substring(b, c + yy+1), String.valueOf(simpleEval(temp) * y));
                    //System.out.println("the exp =="+ans);
                }
            } else {
                ans = a;
            }
        } else {
            ans = a;
        }

		return ans;
	}
	public String reverse(String a) {
		String ans="";
		StringBuilder gh=new StringBuilder(a);
		ans=gh.reverse().toString();
		
		return ans;
	}

	public String[] count(String a, char b) {
		ArrayList<Integer> fir = new ArrayList<Integer>();
		ArrayList<Integer> ans = new ArrayList<Integer>();
		String[] asn=new String[3];
		//System.out.println("called");
		for (int y = 0; y < a.length(); y++) {
			if (a.charAt(y) == '(') {
				ans.add(y);
			}
			else if(a.charAt(y)==')'){
			    fir.add(y);
            }
		}
		if(fir.size() != ans.size()){
			int hh=Math.abs(fir.size() - ans.size());
			for(int i= 0;i<hh ; i++){
				a = a + ")";
				fir.add(a.length()-1);
			}

			//System.out.println(a +" fir" + fir.get(0) + "ans" + ans.get(0));
		}
		if(fir.size() == ans.size()){
		    for(int z:ans){
		        for(int x:fir){
		            String temp=a.substring(z+1,x);
		            if(!temp.contains("(") && !temp.contains(")")){
		                asn[0]=String.valueOf(z);
		                asn[1]=String.valueOf(x);
		                asn[2] = a;
					return asn;
                    }
                }

            }
        }

		return asn;
	}

	public double simpleEval(String a) {

		double ans = 0;
		String[] arr;
		double[] cont;

		if (a.contains("+")) {
			if (a.matches("\\+\\d+")) {
				ans = Double.parseDouble(a);

			} else {
				if (a.contains(")+")) {
					arr = a.split("\\)\\+");
					for (int i = 0; i < arr.length; i++) {

						if ((a.indexOf(arr[i]) + arr[i].length()) < a.length()
								&& a.charAt(a.indexOf(arr[i]) + arr[i].length()) == ')') {
							arr[i] += ")";
							//System.out.println(arr[i]);
						}
					}
				} else {
					arr = a.split("\\+");
				}
				cont = convertToDou(arr);
				for (double r : cont) {
					ans += r;
				}

			}
		}

		else if (a.contains("-")) {

			if (a.matches("-\\d+")) {

				ans = Double.parseDouble(a);
				return ans;
			} else {

				if (a.contains(")-")) {
					arr = a.split("\\)-");
					for (int i = 0; i < arr.length; i++) {

						if ((a.indexOf(arr[i]) + arr[i].length()) < a.length()
								&& a.charAt(a.indexOf(arr[i]) + arr[i].length()) == ')') {
							arr[i] += ")";
							// System.out.println(arr[i]);
						}
					}
				} else {
					arr = a.split("-");
				}
				double[] bb = convertToDou(arr);
				ans = bb[0];

				bb[0] = 0;
				for (double s : bb) {
					ans -= s;
				}
			}
		} else if (a.contains("/")) {

			if (a.contains(")/")) {
				arr = a.split("\\)/");
				for (int i = 0; i < arr.length; i++) {

					if ((a.indexOf(arr[i]) + arr[i].length()) < a.length()
							&& a.charAt(a.indexOf(arr[i]) + arr[i].length()) == ')') {
						arr[i] += ")";
						// System.out.println(arr[i]);
					}
				}
			} else {
				arr = a.split("/");
			}
			double[] bb = convertToDou(arr);
			ans = bb[0];
			bb[0] = 1;
			for (double s : bb) {
				ans /= s;
			}

		} else if (a.contains("x")) {

			if (a.contains(")x")) {
				arr = a.split("\\)x");
				for (int i = 0; i < arr.length; i++) {

					if ((a.indexOf(arr[i]) + arr[i].length()) < a.length()
							&& a.charAt(a.indexOf(arr[i]) + arr[i].length()) == ')') {
						arr[i] += ")";
						// System.out.println(arr[i]);
					}
				}
			} else {
				arr = a.split("x");
			}
			double[] bb = convertToDou(arr);
			ans = 1;
			for (double s : bb) {

				ans *= s;
			}

		} else if (a.contains("%")) {

			if (a.contains(")+")) {
				arr = a.split("\\)\\+");
				for (int i = 0; i < arr.length; i++) {

					if ((a.indexOf(arr[i]) + arr[i].length()) < a.length()
							&& a.charAt(a.indexOf(arr[i]) + arr[i].length()) == ')') {
						arr[i] += ")";
						// System.out.println(arr[i]);
					}
				}
			} else {
				arr = a.split("%");
			}
			double[] bb = convertToDou(arr);
			ans = bb[0];
			bb[0] = ans + 1;
			for (double s : bb) {
				ans %= s;
			}
		} else if (a.contains("^")) {

			if (a.contains(")+")) {
				arr = a.split("\\)\\+");
				for (int i = 0; i < arr.length; i++) {

					if ((a.indexOf(arr[i]) + arr[i].length()) < a.length()
							&& a.charAt(a.indexOf(arr[i]) + arr[i].length()) == ')') {
						arr[i] += ")";
						// System.out.println(arr[i]);
					}
				}
			} else {
				arr = a.split("\\^");
			}
			double[] bb = convertToDou(arr);

			try {
				ans = Math.pow(bb[0], bb[1]);
				if (bb.length > 2) {
					bb[0] = bb[1] = 1;
					for (double h : bb) {
						ans = Math.pow(ans, h);
					}
				}
			} catch (Exception e) {
				simpleEval(arr[1]);
			}
		}
		// trig part

		else if (a.contains("sin(")) {
			int y = a.indexOf("sin(");
			int z = a.lastIndexOf(")");
			String sin = a.substring(y + 4, z);
			double i = 1;

			i=(simpleEval(a.substring(0, y)) ==0) ? 1:simpleEval(a.substring(0, y));
			//System.out.println(sin);
			double r = simpleEval(sin);
			if(deg) {
				rad=false;
				ans = Math.sin(Math.toRadians(r)) * i;
			}
			else{
				ans=Math.sin(r) * i;
			}

		}	else if (a.contains("sin⁻¹(")) {
			int y = a.indexOf("sin⁻¹(");
			int z = a.lastIndexOf(")");
			String sin = a.substring(y + 6, z);
			double i = 1;

			i=(simpleEval(a.substring(0, y)) ==0) ? 1:simpleEval(a.substring(0, y));
			//System.out.println(sin);
			double r = simpleEval(sin);
			if(deg){
				ans = Math.toDegrees(Math.asin(r)) * i;
				rad=false;
			}
			else{
				ans=Math.asin(r) * i;
			}


		}

		else if (a.contains("cos(")) {
			int y = a.indexOf("cos(");
			int z = a.lastIndexOf(")");
			String sin = a.substring(y + 4, z);
			double i = 0;
			i=(simpleEval(a.substring(0, y)) ==0) ? 1:simpleEval(a.substring(0, y));
			double r = simpleEval(sin);
			if(deg) {
				ans = Math.cos(Math.toRadians(r)) * i;
			}
			else{
				ans=Math.cos(r) * i;
			}

		}		else if (a.contains("cos⁻¹(")) {
			int y = a.indexOf("cos⁻¹(");
			int z = a.lastIndexOf(")");
			String sin = a.substring(y + 6, z);
			double i = 0;
			i=(simpleEval(a.substring(0, y)) ==0) ? 1:simpleEval(a.substring(0, y));
			double r = simpleEval(sin);
			if(deg) {
				ans = Math.toDegrees(Math.acos(r)) * i;
			}
			else{
				ans=Math.acos(r) * i;
			}

		}

		else if (a.contains("tan(")) {
			int y = a.indexOf("tan(");
			int z = a.lastIndexOf(")");
			String sin = a.substring(y + 4, z);
			double i = 0;
			i=(simpleEval(a.substring(0, y)) ==0) ? 1:simpleEval(a.substring(0, y));
			double r = simpleEval(sin);
			if(deg) {
				ans = Math.tan(Math.toRadians(r)) * i;
			}
			else{
				ans=Math.tan(r)* i;
			}

		}else if (a.contains("tan⁻¹(")) {
			int y = a.indexOf("tan⁻¹(");
			int z = a.lastIndexOf(")");
			String sin = a.substring(y + 4, z);
			double i = 0;
			i=(simpleEval(a.substring(0, y)) ==0) ? 1:simpleEval(a.substring(0, y));
			double r = simpleEval(sin);
			if(deg) {
				ans =Math.toDegrees(Math.atan(r)) * i;
			}
			else{
				ans=Math.atan(r) * i;
			}

		}

		else if (a.contains("log(")) {
			int y = a.indexOf("log(");
			int z = a.lastIndexOf(")");
			String sin = a.substring(y + 4, z);
			double i = 0;
			i=(simpleEval(a.substring(0, y)) ==0) ? 1:simpleEval(a.substring(0, y));
			double r = simpleEval(sin);
			ans = Math.log(Math.toRadians(r)) * i;

		}

//			else if(a.matches( "[\\x00-\\x20]*"+ "[+-]?(" + "NaN|" + "((("+"(\\.)?("+"?)("+")?)|"+ "(\\.("+")("+")?)|"+ "((" + "(0[xX]" + "(\\.)?)|" + "(0[xX]" + "?(\\.)" + ")" + ")[pP][+-]?"+ "))" + "[fFdD]?))" + "[\\x00-\\x20")) {
//				ans=Double.parseDouble(a);
//				
//			}

		else if (isDouble(a)) {
			ans = Double.parseDouble(a);
		}
		else if(a.contains("!")){
			String[] mn=a.split("!");
			ans=fact(simpleEval(mn[0]));
		}
		else if(a.contains("π")){
			ans=evall(a.replace("π","("+String.valueOf(Math.PI)+")"));
		}
	/*else  {
			ans=evall(a);
		}*/
/*
	 if((a.contains("(") && a.contains(")"))&& (!a.contains("sin") || !a.contains("cos") ) ){

			  int i=a.indexOf("("),j=a.lastIndexOf(")");
			  String temp=a.substring(i+1,j);
			  System.out.println("data "+ temp);
		  double jk=1;
		 ans=simpleEval(temp);

		 }

*/
		  
		  
		  
		 
		return ans;
	}

    public double fact(double a){
		if(a==1){
			return a;
		}
		else{
			return(a*fact(a-1));
		}
	}



	public boolean isDouble(String a) {
		boolean ans = true;
		try {
			Double.parseDouble(a);

		} catch (Exception e) {
			ans = false;
		}
		return ans;
	}

	public double[] convertToDou(String[] a) {
		double[] ans = new double[a.length];
		for (int i = 0; i < a.length; i++) {
			ans[i] = evall(a[i]);

		}

		return ans;

	}

}
