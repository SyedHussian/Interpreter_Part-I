import java.util.HashMap;

class If extends Exp
{
    Exp exp1;
    Exp exp2;
    Exp exp3;

    If(Exp e1, Exp e2, Exp e3)
    {
        exp1 = e1;
        exp2 = e2;
        exp3 = e3;
    }

    void printParseTree(String indent)
    {
        super.printParseTree(indent);

        String indent1 = indent+" ";
        String indent2 = indent1+" ";

        IO.displayln(indent1 + indent1.length() + " if");
        exp1.printParseTree(indent2);
        IO.displayln(indent1 + indent1.length() + " then");
        exp2.printParseTree(indent2);
        IO.displayln(indent1 + indent1.length() + " else");
        exp3.printParseTree(indent2);
    }

    Val Eval(HashMap<String, Val> state)
    {
        if(exp1.Eval(state).getClass() == BoolVal.class) {
            if(((BoolVal)exp1.Eval(state)).val) {
                return exp2.Eval(state);
            }
            else {
                return exp3.Eval(state);
            }
        }
        else {
            System.out.println("Error: boolean condition of if-then-else evaluated to non-boolean value: " + exp1.Eval(state));
            return null;
        }
    }
}