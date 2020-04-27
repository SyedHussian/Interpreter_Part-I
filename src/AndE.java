import java.util.HashMap;

class AndE extends FunExp
{
    AndE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "and";
    }

    Val Eval(HashMap<String, Val> state) {
        if (expList.getClass() != EmptyExpList.class) {
            NonEmptyExpList nonEmptyExpList = (NonEmptyExpList) expList;
            boolean isTrue = true;
            boolean expListClass = true;

            while (expListClass) {
                if (nonEmptyExpList.exp.Eval(state) != null) {
                    if (nonEmptyExpList.exp.Eval(state).getClass() == BoolVal.class) {
                        if (nonEmptyExpList.exp.Eval(state).getClass() == BoolVal.class) {
                            if (!((BoolVal) nonEmptyExpList.exp.Eval(state)).val) {
                                isTrue = false;
                            }
                        }
                        if (nonEmptyExpList.expList.getClass() == NonEmptyExpList.class) {
                            nonEmptyExpList = (NonEmptyExpList) nonEmptyExpList.expList;
                        } else {
                            expListClass = false;
                        }
                    }
                    else {
                        System.out.println("Error: and operator cannot be applied to " + nonEmptyExpList.exp.Eval(state));
                        return null;
                    }
                }
                else {
                    return null;
                }
            }
            return new BoolVal(isTrue);
        }
        else {
            return new BoolVal(true);
        }
    }
}