package calculators;

import com.fathzer.soft.javaluator.DoubleEvaluator;

public class CalcWithLib extends Calculator {
    @Override
    public Double Calculate(String expression) {
        Expression = expression;
        Expression = DellSpaces(Expression);
        DoubleEvaluator eval = new DoubleEvaluator();
        Result = eval.evaluate(Expression);
        return Result;
    }
    @Override
    public String DellSpaces(String processed_string) {
        StringBuilder build = new StringBuilder(processed_string);
        for (int i = 0 ; i < build.length(); i++) {
            if (build.charAt(i) == ' ') {
                build.deleteCharAt(i);
                i--;
            }
        }
        return build.toString();
    }
}
