import java.util.HashMap;

class NotE extends FunExp
{
    NotE(ExpList e) {
        expList = e;
    }

    String getFunOp()
    {
        return "not";
    }

    Val Eval(HashMap<String, Val> state) {
        if(expList.getClass() != EmptyExpList.class) {
            NonEmptyExpList nonEmptyExpList = (NonEmptyExpList) expList;
            boolean expListClass = true;

            while (expListClass) {
                state.put(getFunOp(), nonEmptyExpList.exp.Eval(state));
                if (nonEmptyExpList.expList.getClass() == NonEmptyExpList.class) {
                    nonEmptyExpList = (NonEmptyExpList) nonEmptyExpList.expList;
                }
                else {
                    expListClass = false;
                }
            }
            state.replace(getFunOp(), new BoolVal(!Boolean.valueOf(state.get(getFunOp()).toString())));

            return state.get(getFunOp());
        }
        else {
            System.out.println("Error: not operator missing arguments");
            return null;
        }
    }
}