package Servises;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DateInputValidator implements IInputValidator<LocalDate> {
    @Override
    public LocalDate getInput() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter date:");
            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return LocalDate.parse(scanner.nextLine(), formatter);
        } catch (Exception e) {
            System.out.println("Invalid input! Format should be \"dd.MM.yyyy\"");
            return getInput();
        }
    }
}
