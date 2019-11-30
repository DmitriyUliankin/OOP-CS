package Servises;

import java.util.Scanner;

public class IntInputValidator implements IInputValidator {

    @Override
    public Integer getInput() {
        try {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input! Try again!");
            return getInput();
        }
    }
}
