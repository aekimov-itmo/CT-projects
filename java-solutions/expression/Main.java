package expression;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int res = new Add(new Subtract(new Multiply(new Variable("x"), new Variable("x")), new Multiply(new Variable("x"), new Const(2))), new Const(1)).evaluate(scanner.nextInt());
        scanner.close();
        System.out.println(res);
        System.out.println(new Subtract(new Add(new Variable(0), new Variable(1)), new Const(1))
                .equals(new Subtract(new Add(new Variable(0), new Variable(1)), new Const(1))));
        System.out.println(new Subtract(new Add(new Variable(0), new Variable(1)), new Const(1)).toString());
    }
}
