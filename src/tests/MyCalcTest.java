package tests;

import calculators.MyCalc;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyCalcTest {

    @Test
    void calculate() {
        String expression = "1 + 3 - 5 * 3/ 4 + 6 - 2 / 2 * (7 ^ 2) +  4* (3 ^ (32 - 1 * 31  - 1) * (32))";
        MyCalc calc = new MyCalc();
        Double res = calc.Calculate(expression);
        assertEquals(85.25, res);
    }

    @Test
    void dellSpaces() {
        MyCalc calc = new MyCalc();
        String a = "             1    2 3 4 _ =5 -9 909-238 4-]  0C972['  ;P8     '88834I T 3W48YTR 029Ijkhsdkfjh h945 83u84y 2j 8gplgld.f g.dfgKMNCP08IY  sdf sd  d f                                         ";
        a = calc.DellSpaces(a);
        assertEquals(a, "1234_=5-9909-2384-]0C972[';P8'88834IT3W48YTR029Ijkhsdkfjhh94583u84y2j8gplgld.fg.dfgKMNCP08IYsdfsddf");
    }
    @Test
    void getPriority() {
        MyCalc calc = new MyCalc();
        int a = calc.getPriority('+');
        assertEquals(a, 1);
        a = calc.getPriority('-');
        assertEquals(a, 1);
        a = calc.getPriority('*');
        assertEquals(a, 2);
        a = calc.getPriority('/');
        assertEquals(a, 2);
        a = calc.getPriority('^');
        assertEquals(a, 3);
        a = calc.getPriority(')');
        assertEquals(a, 0);
        a = calc.getPriority('(');
        assertEquals(a, -1);
        a = calc.getPriority('t');
        assertEquals(a, -1);
    }
}