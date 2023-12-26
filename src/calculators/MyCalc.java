package calculators;

import java.util.ArrayList;
import java.util.Stack;

public class MyCalc extends Calculator {
    ArrayList<Data> simpleNotationOrder;
    ArrayList<Data> polishNotationOrder;
    public int getPriority(char data) {
        return switch (data) {
            case '-', '+' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            case ')' -> 0;
            default -> -1;
        };
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
        processed_string = build.toString();
        return processed_string;
    }
    public void createSimpleNotationOrder() {
        Expression = DellSpaces(Expression);
        simpleNotationOrder = new ArrayList<>();
        double number = 0.0;
        for (int i = 0; i < Expression.length(); i++) {
            if (Expression.charAt(i) >= '0' && Expression.charAt(i) <= '9') {
                int decimalFlag = 0;
                int decimalCounter = 0;
                while (i < Expression.length() && (Expression.charAt(i) - '0' >= 0 && Expression.charAt(i) - '0' <= 9 || Expression.charAt(i) == '.')) {
                    if (decimalFlag == 1) {
                        decimalCounter++;
                    }
                    if (Expression.charAt(i) != '.') {
                        number = number * 10.0 + (double) (Expression.charAt(i) - '0');
                    } else {
                        decimalFlag = 1;
                    }
                    i++;
                }
                for (int j = 0 ; j < decimalCounter; j++) {
                    number = number / 10.0;
                }
                i--;
                Data dataNode = new Data(number);
                number = 0.0;
                simpleNotationOrder.add(dataNode);
            } else {
                Data operatorData = new Data(Expression.charAt(i));
                simpleNotationOrder.add(operatorData);
            }
        }
    }

    public void createPolishNotationOrder() {
        polishNotationOrder = new ArrayList<>();
        Stack<Data> operatorStack  = new Stack<>();
        for (Data dataItem : simpleNotationOrder) {
            if (dataItem.isSign()) {
                if (dataItem.arithmeticOperator == '(') {
                    Data tempData = new Data('(');
                    operatorStack.push(tempData);
                } else {
                    if (dataItem.arithmeticOperator == ')') {
                        while (operatorStack.peek().arithmeticOperator != '(') {
                            Data tempData = operatorStack.pop();
                            polishNotationOrder.add(tempData);
                        }
                        operatorStack.pop();
                    } else {
                        while (!operatorStack.empty() && getPriority(operatorStack.peek().arithmeticOperator) >= getPriority(dataItem.arithmeticOperator)) {
                            Data tempData = operatorStack.pop();
                            polishNotationOrder.add(tempData);
                        }
                        operatorStack.push(new Data(dataItem.arithmeticOperator));
                    }
                }
            } else {
                polishNotationOrder.add(new Data(dataItem.Operand));
            }
        }
        while(!operatorStack.empty()) {
            Data tempData = operatorStack.pop();
            polishNotationOrder.add(tempData);
        }
    }
    public void calculateResult() {
        Stack<Data> dataStack = new Stack<>();
        for (Data dataItem : polishNotationOrder) {
            if (dataItem.isSign()) {
                double operand1 = dataStack.pop().Operand;
                double operand2 = dataStack.pop().Operand;
                switch (dataItem.arithmeticOperator) {
                    case '-':
                        dataStack.push(new Data(operand2 - operand1));
                        break;
                    case '+':
                        dataStack.push(new Data(operand2 + operand1));
                        break;
                    case '*':
                        dataStack.push(new Data(operand2 * operand1));
                        break;
                    case '/':
                        if (operand1 == 0) {
                            System.out.println("division by 0");
                            Result = Double.MIN_VALUE;
                            return;
                        } else {
                            dataStack.push(new Data(operand2 / operand1));
                        }
                        break;
                    case '^':
                        if (operand2 < 0) {
                            System.out.println("powering negative value");
                            //TODO implement exceptions for div on 0
                            Result = Double.MIN_VALUE;
                            return;
                        } else {
                            dataStack.push(new Data(Math.pow(operand2, operand1)));
                        }
                        break;
                    default:
                        break;
                }
            } else {
                dataStack.push(dataItem);
            }
        }
        Result = dataStack.pop().Operand;
    }

    @Override
    public Double Calculate (String expression) {
        Expression = expression;
        createSimpleNotationOrder();
        createPolishNotationOrder();
        calculateResult();
        return Result;
    }
}
