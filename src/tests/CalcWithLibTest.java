package tests;

import calculators.CalcWithLib;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcWithLibTest {

    @Test
    void calculate() {
        String expression = "1 + 3 - 5 * 3/ 4 + 6 -     2 / 2 * (7 ^ 2) +     4* (3 ^ (32 - 1 * 31  - 1) * (32))";
        CalcWithLib calc = new CalcWithLib();
        Double res = calc.Calculate(expression);
        assertEquals(85.25, res);
    }

    @Test
    void dellSpaces() {
        CalcWithLib calc = new CalcWithLib();
        String a = "             1    2 3 4 _ =5 -9 909-238 4-]  0C972['  ;P8     '88834I T 3W48YTR 029Ijkhsdkfjh h945 83u84y 2j 8gplgld.f g.dfgKMNCP08IY  sdf sd  d f                                         ";
        a = calc.DellSpaces(a);
        assertEquals(a, "1234_=5-9909-2384-]0C972[';P8'88834IT3W48YTR029Ijkhsdkfjhh94583u84y2j8gplgld.fg.dfgKMNCP08IYsdfsddf");
    }
}