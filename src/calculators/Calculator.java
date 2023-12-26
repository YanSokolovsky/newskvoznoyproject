package calculators;

public abstract class Calculator {
    Double Result;
    String Expression;
    static class Data {
        public Double Operand;
        public char arithmeticOperator;
        public boolean Type_Of_Data;
        public Data(double number) {
            Operand = number;
            arithmeticOperator = ' ';
            Type_Of_Data = true;
        }
        public Data(char arithmetic_sign) {
            Operand = Double.MIN_VALUE;
            arithmeticOperator = arithmetic_sign;
            Type_Of_Data = false;
        }
        public boolean isSign() {
            return !Type_Of_Data;
        }
    }
    abstract Double Calculate(String expression);

    String DellSpaces(String processed_string) {
        return null;
    }
}
