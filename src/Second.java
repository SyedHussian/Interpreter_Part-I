import java.util.HashMap;

class Second extends FunExp
{
    Second(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "second";
    }

    Val Eval(HashMap<String, Val> state)
    {
        if(expList.getClass() == EmptyExpList.class)
        {
            System.out.println("Error: second operator missing arguments");
            return null;
        }
        NonEmptyExpList nonEmptyExpList = (NonEmptyExpList) expList;
        boolean expListClass = true;

        while (expListClass) {
            try {
                state.put(getFunOp(), ((PairVal)nonEmptyExpList.exp.Eval(state)).second);
            }
            catch (Exception e) {
                System.out.println("Error: second operator cannot be applied to "+nonEmptyExpList.exp.Eval(state));
                return null;
            }

            if(nonEmptyExpList.expList.getClass() == NonEmptyExpList.class) {
                nonEmptyExpList = (NonEmptyExpList)nonEmptyExpList.expList;
            }
            else {
                expListClass = false;
            }
        }
        return state.get(getFunOp());
    }
}