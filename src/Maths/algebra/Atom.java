package Maths.algebra;

public class Atom  {
        private String value;
        private Bindings bindings;
         private int index;
         public void setIndex(int val){
             index = val;
         }
        public int getIndex(){
             return index;
        }
    public Bindings getBindings() {
        return bindings;
    }

    public void setBindings(Bindings bindings) {
        this.bindings = bindings;
    }

    public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Atom(String value){
            this.value = value;

        }

}
