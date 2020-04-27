import java.util.HashMap;
import java.util.Stack;

class LtE extends FunExp
{
    LtE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "<";
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

                boolean isLess = true;
                for (int i = 1; i < stack.size(); i++) {
                    if (stack.get(i).getClass() == IntVal.class || stack.get(i).getClass() == FloatVal.class) {
                        FloatVal floatVal1;
                        if (stack.get(i).getClass() == FloatVal.class) {
                            floatVal1 = new FloatVal(((FloatVal) stack.get(i)).val);
                        }
                        else {
                            floatVal1 = new FloatVal(((IntVal) stack.get(i)).val);
                        }
                        if (floatVal.val >= floatVal1.val) {
                            floatVal = floatVal1;
                            isLess = false;
                        }
                        else {
                            floatVal = floatVal1;
                            isLess = true;
                        }
                    } else {
                        System.out.println("Error: < operator cannot be applied to " + stack.get(i));
                        return null;
                    }
                }
                if (isLess) {
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