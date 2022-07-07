import java.math.BigInteger;
//Doest not work with Test Cases 3 and 4 - will work with 1, 2, and 5
public class Solution {
    public static BigInteger multiplier(BigInteger a, BigInteger b) {
        BigInteger diff = a.subtract(b);
        return diff.divide(b).add(new BigInteger("1"));
    }

    public static String solution(String x, String y) {
        BigInteger step = new BigInteger("0"), m = new BigInteger(x), f = new BigInteger(y);
        while (true) {
            if (m.intValue() <= 0 || f.intValue() <= 0) break;
            if (m.compareTo(new BigInteger("100")) == 1 || f.compareTo(new BigInteger("100")) == 1 ) {
                if (m.compareTo(f) == 1) {
                    BigInteger mul = Solution.multiplier(m, f);
                    m = m.subtract(f.multiply(mul));
                    step = step.add(mul);
                } else if (f.compareTo(m) == 1) {
                    BigInteger mul = Solution.multiplier(f, m);
                    f = f.subtract(m.multiply(mul));
                    step = step.add(mul);
                } else {
                    break;
                }
            } else {
                if (m.compareTo(f) == 1 ) m = m.subtract(f);
                else if (f.compareTo(m) == 1) f = f.subtract(m);
                else break;
                step = step.add(new BigInteger("1"));
            }
        }

        if (m.intValue() == 1 && f.intValue() == 1 && step.compareTo(new BigInteger("-1")) == 1) {
            return step.toString();
        }
        return "impossible";
    }

}