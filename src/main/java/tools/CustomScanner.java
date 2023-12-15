package tools;

import java.util.Scanner;

public class CustomScanner {
    private Scanner scanner;

    public CustomScanner() {
        this.scanner = new Scanner(System.in);
    }

    public Object scanType(ScanType t) {
        switch (t) {
            case STRING:
                return scanner.next();
            case INT:
                return scanner.nextInt();
            case DOUBLE:
                return scanner.nextDouble();
            case BOOLEAN:
                return scanner.nextBoolean();
            // Add more cases for additional types as needed
            default:
                throw new IllegalArgumentException("Unsupported ScanType: " + t);
        }
    }
}