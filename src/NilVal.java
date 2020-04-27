class NilVal extends Val
{
    public Val cloneVal() {
        return null;
    }
    public String toString() {
        return "nil";
    }
}