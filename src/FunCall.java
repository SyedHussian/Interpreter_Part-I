import java.util.HashMap;

class FunCall extends FunExp
{
    Id func;  // identifier "func" may be a defined function name or a variable

    FunCall(Id i, ExpList e)
    {
        func = i;
        expList = e;
    }

    String getFunOp()
    {
        return func.id;
    }

    Val Eval(HashMap<String, Val> state) {
        System.out.println("sth");
        return null;
    }
}