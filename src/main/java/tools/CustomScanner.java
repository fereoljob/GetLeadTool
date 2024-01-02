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
                return scanner.nextLine();
            case INT:
                return Integer.parseInt(scanner.nextLine());
            case DOUBLE:
                return Double.parseDouble(scanner.nextLine());
            case BOOLEAN:
                return Boolean.parseBoolean(scanner.nextLine());
            // Add more cases for additional types as needed
            default:
                throw new IllegalArgumentException("Unsupported ScanType: " + t);
        }
    }
}