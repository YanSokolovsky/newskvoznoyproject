package tests;

import calculators.MyCalc;
import calculators.RegexCalc;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegexCalcTest {

    @Test
    void dellSpaces() {
        RegexCalc calc = new RegexCalc();
        String a = "             1    2 3 4 _ =5 -9 909-238 4-]  0C972['  ;P8     '88834I T 3W48YTR 029Ijkhsdkfjh h945 83u84y 2j 8gplgld.f g.dfgKMNCP08IY  sdf sd  d f                                         ";
        a = calc.DellSpaces(a);
        assertEquals(a, "1234_=5-9909-2384-]0C972[';P8'88834IT3W48YTR029Ijkhsdkfjhh94583u84y2j8gplgld.fg.dfgKMNCP08IYsdfsddf");
    }

    @Test
    void hasBrackets() {
        RegexCalc calc = new RegexCalc();
        String a = "dfl kjldkj fs94e9i ldjflk";
        String b = "() )()()( kdjslkfj lsd";
        String c = "jlkdfsj lkjsd kjsl   klmqw3n ,(";
        assertTrue(calc.hasBrackets(b));
        assertTrue(calc.hasBrackets(c));
        assertFalse(calc.hasBrackets(a));
    }

    @Test
    void removeSign() {

    }

    @Test
    void calculate() {
        String expression = "123 + 123";
        RegexCalc calc = new RegexCalc();
        Double res = calc.Calculate(expression);
        assertEquals(246, res);
    }

    @Test
    void calculateAtomicExpression() {
        RegexCalc calc = new RegexCalc();
        String expression1 = "2.3*12";
        String expression2 = "2.4/12";
        String expression3 = "2.3+12";
        String expression4 = "2.3-12";
        String expression5 = "2.3^12";
        double res1 = 27.6;
        double res2 = 0.2;
        double res3 = 14.3;
        double res4 = 9.7;
        double res5 = 21914.6244;
        assertTrue(res1 - Double.parseDouble(calc.calculateAtomicExpression(expression1)) < 0.0000000001);
        assertTrue((res2 - Double.parseDouble(calc.calculateAtomicExpression(expression1)) < 0.0000000001));
        assertTrue((res3 - Double.parseDouble(calc.calculateAtomicExpression(expression1)) < 0.0000000001));
        assertTrue((res4 - Double.parseDouble(calc.calculateAtomicExpression(expression1)) < 0.0000000001));
        assertTrue((Double.parseDouble(calc.calculateAtomicExpression(expression1)) - res5 < 0.0000000001));
    }
}