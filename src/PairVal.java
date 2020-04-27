import java.util.Objects;

class PairVal extends Val
{
    Val first;
    Val second;

    public PairVal(Val first, Val second) {
        this.first = first;
        this.second = second;
    }

    public String toString()
    {
        return "pair("+first+", "+second+")";
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof PairVal) {
                PairVal pairVal = (PairVal) obj;
                return Objects.equals(first, pairVal.first) &&
                        Objects.equals(second, pairVal.second);
            }
            else {
                return false;
            }
        }
        else {
            return true;
        }
    }

    public int hashCode() {
        return Objects.hash(first, second);
    }

    Val cloneVal() {
        return null;
    }

    // You add suitable constructors/functions.


}