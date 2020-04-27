import java.util.HashMap;
import java.util.Stack;

class Pair extends FunExp
{
    Pair(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "pair";
    }

    Val Eval(HashMap<String, Val> state)
    {
        Stack<Val> stack = new Stack<>();

        if(expList.getClass() == EmptyExpList.class) {
            System.out.println("Error: pair operator missing arguments");
            return null;
        }

        NonEmptyExpList nonEmptyExpList = (NonEmptyExpList)expList;
        boolean expListClass = true;

        while (expListClass) {
            if(nonEmptyExpList.exp.Eval(state) != null) {
                stack.add(nonEmptyExpList.exp.Eval(state));
            }
            if(nonEmptyExpList.expList.getClass() == NonEmptyExpList.class) {
                nonEmptyExpList = (NonEmptyExpList)nonEmptyExpList.expList;
            }
            else {
                expListClass = false;
            }
        }
        for(int i = 0; i < stack.size(); i+=2) {
            try {
                state.put(getFunOp(), new PairVal(stack.get(i), stack.get(i+1)));
            }
            catch (Exception e) {
                System.out.println("Error: pair operator missing 2nd argument");
                return null;
            }
        }
        return state.get(getFunOp());
    }
}