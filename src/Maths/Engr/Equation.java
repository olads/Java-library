package Maths.Engr;

import Maths.Evaluation.ParameterException;

import java.util.ArrayList;
import java.util.Set;

public abstract class Equation {
        private String name;
        private String representation;
        protected ArrayList<Formula> formulas = new ArrayList<>();
        public Equation(String name,String represent){
            this.name = name;
            representation = represent;
        }

        public Formula getFomula(String ... knownVariablesNames){
            System.out.println(formulas.size());
            for(Formula form:formulas){
               int curr = form.getNoOfParams(knownVariablesNames);
               if(curr == 0)
                   return form;

            }
            throw new ParameterException("Formula is not defined yet \n try defining the formula first ");
        }
        public double solve(String ... a){
            Parser parser = new Parser(a);
            Formula formula = getFomula(parser.getKeys());
            return formula.solve(parser);
        }





}
