import java.util.Stack;

public class FunctionParser {
    public static float evaluateFunction(String function, String xValue) {
        Stack<Character> operations = new Stack<>();
        Stack<String> values = new Stack<>();
        StringBuilder valueBuilder = new StringBuilder();
        char current;
        float answer = 0;

        function = function.replaceAll("x", xValue);

        int i = 0;
        while (i < function.length()) {
            current = function.charAt(i);
            // If operator is encountered
            if (!Character.isLetterOrDigit(current)) {
                // Add the number to the values stack
                values.push(valueBuilder.toString());
                valueBuilder.setLength(0);

                // Add the operator
                // Check first if exponent
                if (current == '^') {
                    operations.push(current);
                    int exponentEndIndex = findExponentEndIndex(i, function);
                    values.push(function.substring(i+1, exponentEndIndex));
                } else {
                    
                }

            } else {
                valueBuilder.append(current);
            }
            i++;
        }
        System.out.println(operations);
        System.out.println(values);
        return answer;
    }

    public static String plugX(String s, String xValue) {
        s.replace("s", xValue);
        return s;
    }

    public static String convertExponent(String s) {
        // Example input = 5 x ^ 2
        // Supposed output = 5 * Math.pow(x, 2)
        
        StringBuilder stb = new StringBuilder();

        // remove all whitespace
        s = s.replaceAll("\\s", "");
        
        // iterate through string
        int i = 0;
        char currChar;
        while (i < s.length()) {
            currChar = s.charAt(i);
            if (currChar == '^') {
                // Find exponent value
                int endIndex = findExponentEndIndex(i, s);
                String exponent = s.substring(i+1, endIndex);
                stb.append("*Math.pow(x, " + exponent + ")");

                i = endIndex-1;
                // Check if last
                if (i == s.length()) {
                    break;
                }
            } else if (currChar == 'x') {
                int next = i + 1;
                if (i == s.length()-1) {
                    stb.append("x");
                } else if (s.charAt(next) != '^') {
                    stb.append('x');
                }
            } else {
                stb.append(currChar);
            }
            i++;
        }
        String output = stb.toString();
        return(output);
    }

    public static int findExponentEndIndex(int currentIndex, String s) {
        int i = currentIndex;
        char curr;

        while (i < s.length()) {
            curr = s.charAt(i);
            switch (curr) {
                // return if an operator is encountered
                case '+':
                case '-':
                case '*':
                case '/':
                    return i;
                default:
                    i++;
            }
        }
        return i;
    }    
}
