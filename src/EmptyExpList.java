import java.util.HashMap;

class EmptyExpList extends ExpList
{
    Val Eval(HashMap<String, Val> state) {
        return null;
    }
}