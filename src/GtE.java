import java.util.HashMap;
import java.util.Stack;

class GtE extends FunExp
{
    GtE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return ">";
    }

    Val Eval(HashMap<String, Val> state) {
        if (expList.getClass() != EmptyExpList.class) {

            NonEmptyExpList nonEmptyExpList = (NonEmptyExpList) expList;
            Stack<Val> stack = new Stack<>();
            boolean expListClass = true;

            while (expListClass) {
                stack.add(nonEmptyExpList.exp.Eval(state));
                if (nonEmptyExpList.expList.getClass() == NonEmptyExpList.class) {
                    nonEmptyExpList = (NonEmptyExpList) nonEmptyExpList.expList;
                } else {
                    expListClass = false;
                }
            }

            if (stack.size() > 1) {
                FloatVal floatVal;
                if (stack.get(0).getClass() == IntVal.class) {
                    floatVal = new FloatVal(((IntVal) stack.get(0)).val);
                }
                else {
                    floatVal = (FloatVal) stack.get(0);
                }

                boolean isGreater = true;
                for (int i = 1; i < stack.size(); i++) {
                    if (stack.get(i).getClass() == IntVal.class || stack.get(i).getClass() == FloatVal.class) {
                        FloatVal floatVal1;
                        if (stack.get(i).getClass() == FloatVal.class) {
                            floatVal1 = new FloatVal(((FloatVal) stack.get(i)).val);
                        }
                        else {
                            floatVal1 = new FloatVal(((IntVal) stack.get(i)).val);
                        }
                        if (floatVal.val > floatVal1.val) {
                            isGreater = true;
                        }
                        else {
                            isGreater = false;
                        }
                        floatVal = floatVal1;
                    } else {
                        System.out.println("Error: < operator cannot be applied to " + stack.get(i));
                        return null;
                    }
                }
                if (isGreater) {
                    return new BoolVal(true);
                }
                return new BoolVal(false);
            }
            else {
                return new BoolVal(true);
            }
        }
        else {
            return new BoolVal(true);
        }
    }
}