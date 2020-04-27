import java.util.HashMap;

class First extends FunExp
{
    First(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "first";
    }

    Val Eval(HashMap<String, Val> state)
    {
        if(expList.getClass() == EmptyExpList.class)
        {
            System.out.println("Error: first operator missing arguments");
            return null;
        }
        NonEmptyExpList nonEmptyExpList = (NonEmptyExpList) expList;
        boolean expListClass = true;

        while (expListClass)
        {
            try
            {
                state.put(getFunOp(), ((PairVal)nonEmptyExpList.exp.Eval(state)).first);
            }
            catch (Exception e) {
                System.out.println("Error: first operator cannot be applied to " + nonEmptyExpList.exp.Eval(state));
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