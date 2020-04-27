import java.util.HashMap;

abstract class Exp
{
    void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <exp>");
    }
    abstract Val Eval(HashMap<String,Val> state); // function to interpret this assignment list
}