import java.util.HashMap;

class NonEmptyExpList extends ExpList
{
    Exp exp;
    ExpList expList;

    NonEmptyExpList(Exp e, ExpList el)
    {
        exp = e;
        expList = el;
    }

    Val Eval(HashMap<String, Val> state) {
        return expList.Eval(state);
    }
}