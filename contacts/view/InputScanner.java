package contacts.view;

import java.util.Scanner;

public class InputScanner {

    private Scanner scanner = new Scanner(System.in);

    public String readLine() {
        return scanner.nextLine();
    }

    // number validation in future!
    public int readInt() {
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    public void closeScanner() {
        scanner.close();
    }
}
