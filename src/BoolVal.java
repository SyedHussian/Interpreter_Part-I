class BoolVal extends Val
{
    boolean val;

    BoolVal(boolean i)
    {
        val = i;
    }

    public String toString()
    {
        return val+"";
    }

    Val cloneVal()
    {
        return new BoolVal(val);
    }

    float floatVal()
    {
        return 0;
    }

    boolean isZero()
    {
        return val;
    }
}