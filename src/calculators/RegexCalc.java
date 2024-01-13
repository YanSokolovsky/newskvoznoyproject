package calculators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexCalc extends Calculator {
    @Override
    public String DellSpaces(String processed_string) {
        StringBuilder build = new StringBuilder(processed_string);
        for (int i = 0 ; i < build.length(); i++) {
            if (build.charAt(i) == ' ' || build.charAt(i) == '\r') {
                build.deleteCharAt(i);
                i--;
            }
        }
        return build.toString();
    }
    public boolean hasBrackets(String inputExpression) {
        boolean hasBrackets = false;
        for (int i = 0; i < inputExpression.length(); i++) {
            if (inputExpression.charAt(i) == ')' || inputExpression.charAt(i) == '(') {
                hasBrackets = true;
                break;
            }
        }
        return hasBrackets;
    }

    public String removeExponentiation(String inputString) {
        String regularExpressionForExponentiation = "\\d+\\.\\d+\\^\\d+\\.\\d+|\\d+\\.\\d+\\^\\d+|\\d+\\^\\d+\\.\\d+|\\d+\\^\\d+";
        return removeSign(inputString, regularExpressionForExponentiation);
    }

    public String removeSign(String inputString, String regularExpressionForExponentiation) {
        Pattern pattern = Pattern.compile(regularExpressionForExponentiation);
        StringBuilder inputStringBuilder = new StringBuilder(inputString);
        Matcher matcher = pattern.matcher(inputStringBuilder);
        String resultString = inputString;
        while(matcher.find()) {
            int startIndex = matcher.start();
            int endIndex = matcher.end();
            String extractedString = resultString.substring(startIndex, endIndex);
            String replacementString = calculateAtomicExpression(extractedString);
            resultString = matcher.replaceFirst(replacementString);
            matcher = pattern.matcher(resultString);
        }
        return resultString;
    }

    public String removeDivision(String inputString) {
        String regularExpressionForDivision = "\\d+\\.\\d+/\\d+\\.\\d+|\\d+\\.\\d+/\\d+|\\d+/\\d+\\.\\d+|\\d+/\\d+";
        return removeSign(inputString, regularExpressionForDivision);
    }

    public String removeMultiplication(String inputString) {
        String regularExpressionForMultiplication = "\\d+\\.\\d+\\*\\d+\\.\\d+|\\d+\\.\\d+\\*\\d+|\\d+\\*\\d+\\.\\d+|\\d+\\*\\d+";
        return removeSign(inputString, regularExpressionForMultiplication);
    }

    public String removeSummation(String inputString) {
        String regularExpressionForSummation = "\\d+\\.\\d+\\+\\d+\\.\\d+|\\d+\\.\\d+\\+\\d+|\\d+\\+\\d+\\.\\d+|\\d+\\+\\d+";
        return removeSign(inputString, regularExpressionForSummation);
    }
    public String removeSubtraction(String inputString) {
        String regularExpressionForSubtraction = "\\d+\\.\\d+-\\d+\\.\\d+|\\d+\\.\\d+-\\d+|\\d+-\\d+\\.\\d+|\\d+-\\d+";
        return removeSign(inputString, regularExpressionForSubtraction);
    }
    public String calcExWithoutBrace1(String inputString) {
        StringBuilder stringBuilder = new StringBuilder(inputString);
        stringBuilder.deleteCharAt(0);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String res = removeExponentiation(stringBuilder.toString());
        res = removeDivision(res);
        res = removeMultiplication(res);
        res = removeSubtraction(res);
        res = removeSummation(res);
        return res;
    }
    public String calcExWithoutBrace2(String inputString) {
        inputString = DellSpaces(inputString);
        String res = removeExponentiation(inputString);
        res = removeDivision(res);
        res = removeMultiplication(res);
        res = removeSubtraction(res);
        res = removeSummation(res);
        return res;
    }

    @Override
    public Double Calculate(String expression) {
        expression = DellSpaces(expression);
        String regForBraces = "\\([^()]*\\)";
        Pattern pattern = Pattern.compile(regForBraces);
        Matcher matcher = pattern.matcher(expression);
        String result = expression;
        if (hasBrackets(expression)) {
            while(matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                String extracted = result.substring(start, end);
                String res = calcExWithoutBrace1(extracted);
                result = matcher.replaceFirst(res);
                matcher = pattern.matcher(result);
            }
            result = calcExWithoutBrace2(result);
        }
        else {
            result = calcExWithoutBrace2(expression);
        }
        return Double.parseDouble(result);
    }
    public String calcForAtomicExpression(Double operand1, Double operand2, char operation) {
        double result = 0.0;
        switch (operation){
            case '-':
                result = operand1 - operand2;
                break;
            case '+':
                result = operand1 + operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                if (operand2 == 0.0) {
                    System.out.println("division by 0. Please, try to find mistake in expression.");
                    result = Double.MAX_VALUE;
                    return Double.toString(result);
                } else {
                    result = operand1 / operand2;
                }
                break;
            case '^':
                if (operand1 < 0) {
                    System.out.println("powering negative value. It is impossible for some cases.");
                    result = Double.MIN_VALUE;
                    return Double.toString(result);
                } else {
                    result = Math.pow(operand1, operand2);
                }
                break;
            default:
                System.out.println("Invalid operation. Result will be equal to 0. Please find mistake in expression.");
                return Double.toString(result);
        }
        return Double.toString(result);
    }
    public String calculateAtomicExpression(String input) {
        double firstOperand = 0.0;
        double secondOperand = 0.0;
        int flag = 0;
        int counter = 0;
        int i = 0;
        while (i < input.length() && (input.charAt(i) - '0' >= 0 && input.charAt(i) - '0' <= 9 || input.charAt(i) == '.')) {
            if (flag == 1) {
                counter++;
            }
            if (input.charAt(i) != '.') {
                firstOperand = firstOperand * 10.0 + (double)(input.charAt(i) - '0');
            } else {
                flag = 1;
            }
            i++;
        }
        for (int y = 0 ; y < counter; y++) {
            firstOperand = firstOperand / 10.0;
        }


        char operation = input.charAt(i);
        flag = 0;
        counter = 0;
        i++;
        while (i < input.length() && (input.charAt(i) - '0' >= 0 && input.charAt(i) - '0' <= 9 || input.charAt(i) == '.')) {
            if (flag == 1) {
                counter++;
            }
            if (input.charAt(i) != '.') {
                secondOperand = secondOperand * 10.0 + (double) (input.charAt(i) - '0');
            } else {
                flag = 1;
            }
            i++;
        }
        for (int y = 0 ; y < counter; y++) {
            secondOperand = secondOperand / 10.0;
        }
        return calcForAtomicExpression(firstOperand, secondOperand, operation);
    }

}
