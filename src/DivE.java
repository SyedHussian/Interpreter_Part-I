import java.util.HashMap;
import java.util.Stack;

class DivE extends FunExp
{
    DivE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "/";
    }

    Val Eval(HashMap<String, Val> state) {
        if (expList.getClass() != EmptyExpList.class) {
            Stack<Float> stack = new Stack<>();
            NonEmptyExpList nonEmptyExpList = (NonEmptyExpList) expList;
            float num = 1;
            boolean expListClass = true;
            boolean isInt = true;
            while (expListClass) {
                if (nonEmptyExpList.exp.getClass() == Int.class) {
                    num = ((Int) nonEmptyExpList.exp).intElem;
                    stack.add(num);
                } else if (nonEmptyExpList.exp.getClass() == Floatp.class) {
                    isInt = false;
                    num = ((Floatp) nonEmptyExpList.exp).floatElem;
                    stack.add(num);
                } else {
                    if (nonEmptyExpList.exp.Eval(state).getClass() == IntVal.class) {
                        num = ((IntVal) nonEmptyExpList.exp.Eval(state)).val;
                        stack.add(num);
                    } else if (nonEmptyExpList.exp.Eval(state).getClass() == FloatVal.class) {
                        isInt = false;
                        num = ((FloatVal) nonEmptyExpList.exp.Eval(state)).val;
                        stack.add(num);
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
                if (isInt == false) {
                    finalVal = stack.pop() / finalVal;
                }
                else {
                    System.out.println("Error: integer division by 0");
                    return null;
                }
            }
            if (isInt) {
                return new IntVal((int) finalVal);
            } else {
                return new FloatVal(finalVal);
            }
        }
        else {
            return new IntVal(1);
        }
    }
}