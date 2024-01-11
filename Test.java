import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter function (x): ");
        String function = input.nextLine();
        input.close();
        System.out.println(FunctionParser.convertExponent(function));
    }
}
