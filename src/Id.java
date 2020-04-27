import java.util.HashMap;

class Id extends Exp
{
    String id;

    Id(String s)
    {
        id = s;
    }

    void printParseTree(String indent)
    {
        super.printParseTree(indent);
        String indent1 = indent+" ";
        IO.displayln(indent1 + indent1.length() + " " + id);
    }

    Val Eval(HashMap<String,Val> state)
    {
        Val idVal = state.get(id);
        if ( idVal != null )
            return idVal.cloneVal();
        else
        {
            System.out.println( "variable "+id+" does not have a value" );
            return null;
        }
    }
}