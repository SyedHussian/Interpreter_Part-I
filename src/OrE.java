import java.util.HashMap;

class OrE extends FunExp
{
    OrE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "or";
    }

    Val Eval(HashMap<String, Val> state) {
        if (expList.getClass() != EmptyExpList.class) {
            NonEmptyExpList nonEmptyExpList = (NonEmptyExpList) expList;
            boolean isFalse = false;
            boolean expListClass = true;

            while (expListClass) {
                if (nonEmptyExpList.exp.Eval(state) != null) {
                    if (nonEmptyExpList.exp.Eval(state).getClass() == BoolVal.class) {
                        if (nonEmptyExpList.exp.Eval(state).getClass() == BoolVal.class) {
                            if (((BoolVal) nonEmptyExpList.exp.Eval(state)).val) isFalse = true;
                        }
                        if (nonEmptyExpList.expList.getClass() == NonEmptyExpList.class) {
                            nonEmptyExpList = (NonEmptyExpList) nonEmptyExpList.expList;
                        } else {
                            expListClass = false;
                        }
                    }
                    else {
                        return null;
                    }
                }
                else {
                    return null;
                }
            }
            state.put(getFunOp(), new BoolVal(isFalse));
            return state.get(getFunOp());
        }
        else {
            return new BoolVal(false);
        }
    }
}