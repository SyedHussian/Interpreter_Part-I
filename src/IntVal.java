class IntVal extends Val
{
    int val;

    IntVal(int i)
    {
        val = i;
    }

    public String toString()
    {
        return val+"";
    }

    Val cloneVal()
    {
        return new IntVal(val);
    }
}