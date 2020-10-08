package Maths;
import Maths.EvaluateAlgebra.Token;
import Maths.EvaluateAlgebra.Tokenizer;
import Maths.Evaluation.Evaluate;
import Maths.algebra.Atom;
import Maths.algebra.Simplify;
import Maths.algebra.Variable;
import Maths.util.Printer;


import java.util.Scanner;

import java.lang.*;
/*
* Class to provide entry to the calculator from terminal
*
* */
public class Ent {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Evaluate eval = new Evaluate();
		//Divide div = new Divide(input.next(),input.next());
		/*Variable mult = new Variable(input.next());
		Variable a = new Variable(input.next());
		System.out.println("The result is " + a.multiply(mult).parse());
		Parenthesis b = Parenthesis.createBrac(new Variable(input.next()),new Parenthesis(input.next()));
		System.out.println(b.openBrac().parse());
		for(BasicToken basic : Tokenizer.tokenize(input.next())){
			System.out.println(basic.parse());
		}*/
		//Printer.print(Simplify.simplify(Tokenizer.tokenize(input.next())),false);
		//Maths.algebra.Formula formula = new Maths.algebra.Formula(input.next(),input.next());
		//System.out.println(eval.eval(input.next()));




		/*Variable a = new Variable(input.next());
		Variable b = new Variable( input.next());

		//System.out.println(a.add(b).getValue());
		//System.out.println(a.subtract(b).getValue());
		System.out.println(a.divide(b).parse());
		System.out.println(a.multiply(b).parse());*/

		//Printer.print(Maths.algebra.Tokenizer.tokenize("s(ra)"),true);
		for(Atom atm: (new Variable("avcos(ar")).getAtoms()){
			System.out.println(atm.getValue());
		}
	/*	while(true) {
            Formatter format = new Formatter();
            //format.format("The answer is %.10f",bb.evall(input.next();
            System.out.println(new DecimalFormat("The answer = #.##########").format((bb.evall(input.next()))));
        }
*/
	}

}
