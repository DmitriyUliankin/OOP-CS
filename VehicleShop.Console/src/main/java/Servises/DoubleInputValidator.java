package Servises;

import java.util.Scanner;

public class DoubleInputValidator implements IInputValidator {

    @Override
    public Double getInput() {
        try {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextDouble();
        } catch (Exception e) {
            System.out.println("Invalid input! Try again!");
            return getInput();
        }
    }
}
