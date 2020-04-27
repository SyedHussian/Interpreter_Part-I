import java.util.HashMap;
import java.util.Stack;

class AddE extends FunExp
{
    ExpList e;

    AddE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "+";
    }

    Val Eval(HashMap<String, Val> state) {
        if ( expList.getClass() != EmptyExpList.class ) {

            NonEmptyExpList nonEmptyExpList = (NonEmptyExpList) expList;
            Stack<Float> stack = new Stack<>();

            float num = 0;
            boolean isInt = true;
            boolean expListClass = true;

            // The result will be float if one or both of the arguments are float.

            while (expListClass) {

                if (nonEmptyExpList.exp.getClass() == Int.class) {
                    num = ((Int) nonEmptyExpList.exp).intElem;
                    stack.add(num);
                } else if (nonEmptyExpList.exp.getClass() == Floatp.class) {
                    isInt = false;
                    num = ((Floatp) nonEmptyExpList.exp).floatElem;
                    stack.add(num);
                } else {
                    if (nonEmptyExpList.exp.Eval(state) != null) {
                        if (nonEmptyExpList.exp.Eval(state).getClass() == IntVal.class) {
                            num = ((IntVal)nonEmptyExpList.exp.Eval(state)).val;
                            stack.add(num);
                        } else if (nonEmptyExpList.exp.Eval(state).getClass() == FloatVal.class) {
                            isInt = false;
                            num = ((FloatVal)nonEmptyExpList.exp.Eval(state)).val;
                            stack.add(num);
                        }
                    }
                    else {
                        return null;
                    }
                }
                if (nonEmptyExpList.expList.getClass() == NonEmptyExpList.class) {
                    nonEmptyExpList = (NonEmptyExpList) nonEmptyExpList.expList;
                } else {
                    expListClass = false;
                }
            }

            float finalVal = stack.pop();
            while (!stack.isEmpty()) {
                finalVal = stack.pop() + finalVal;
            }

            if (isInt) {
                return new IntVal((int) finalVal);
            } else {
                return new FloatVal(finalVal);
            }
        }
        else {
            return new IntVal(0);
        }
    }
}