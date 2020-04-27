import java.util.HashMap;

abstract class ExpList
{
    abstract Val Eval(HashMap<String,Val> state);
}