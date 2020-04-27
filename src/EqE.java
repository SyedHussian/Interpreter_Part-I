import java.util.HashMap;
import java.util.Stack;

class EqE extends FunExp
{
    EqE(ExpList e) {
        expList = e;
    }

    String getFunOp()
    {
        return "=";
    }

    Val Eval(HashMap<String, Val> state) {
        if(expList.getClass() != EmptyExpList.class) {

            NonEmptyExpList nonEmptyExpList = (NonEmptyExpList) expList;
            boolean expListClass = true;

            Stack<Val> stack = new Stack<>();

            while (expListClass) {
                stack.add(nonEmptyExpList.exp.Eval(state));
                if (nonEmptyExpList.expList.getClass() == NonEmptyExpList.class) {
                    nonEmptyExpList = (NonEmptyExpList) nonEmptyExpList.expList;
                }
                else {
                    expListClass = false;
                }
            }

            if (stack.size() > 1) {
                FloatVal floatVal = null;
                if (stack.get(0).getClass() == IntVal.class) {
                    floatVal = new FloatVal(((IntVal) stack.get(0)).val);
                }
                else if (stack.get(0).getClass() == FloatVal.class) {
                    floatVal = new FloatVal(((FloatVal) stack.get(0)).val);
                }
                else if (stack.get(0).getClass() == PairVal.class) {
                    for (int i = 1; i < stack.size(); i++) {
                        if (!( (((PairVal) stack.get(0)).first).toString().equals(((PairVal) stack.get(i)).first.toString())
                                && (((PairVal) stack.get(0)).second).toString().equals(((PairVal) stack.get(i)).second.toString())) ) {
                            return new BoolVal(false);
                        }
                    }
                    return new BoolVal(true);
                }

                boolean isEq = true;
                for (int i = 1; i < stack.size(); i++) {
                    if (stack.get(i).getClass() == IntVal.class || stack.get(i).getClass() == FloatVal.class) {
                        FloatVal floatVal1 = null;
                        if (stack.get(i).getClass() == FloatVal.class) {
                            floatVal1 = new FloatVal(((FloatVal) stack.get(i)).val);
                        }
                        else if (stack.get(i).getClass() == IntVal.class) {
                            floatVal1 = new FloatVal(((IntVal) stack.get(i)).val);
                        }
                        if (floatVal.val != floatVal1.val) {
                            floatVal = floatVal1;
                            isEq = false;
                        }
                        else {
                            continue;
                        }
                    }
                }
                if (isEq) {
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