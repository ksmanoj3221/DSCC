package practical7;

import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

public class ElectricBillClient {
    public static void main(String[] args) {
        try {
            // Lookup the remote object
            ElectricBillService electricBillService = (ElectricBillService) Naming.lookup("rmi://localhost/ElectricBillService");
            Scanner scanner = new Scanner(System.in);
            String expression;

            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1: Get all bills");
                System.out.println("2: Get bill by Consumer ID");
                System.out.println("exit: Quit");

                expression = scanner.nextLine();
                if (expression.equalsIgnoreCase("exit")) {
                    break;
                }

                switch (expression) {
                    case "1":
                        List<Bill> bills = electricBillService.getAllBills();
                        System.out.println("Bills in the Electric Bill Database:");
                        for (Bill bill : bills) {
                            System.out.println(bill);
                        }
                        break;

                    case "2":
                        System.out.println("Enter the Consumer ID:");
                        int consumerId = Integer.parseInt(scanner.nextLine());
                        Bill bill = electricBillService.getBill(consumerId);
                        if (bill != null) {
                            System.out.println("Retrieved Bill: " + bill);
                        } else {
                            System.out.println("No bill found with Consumer ID: " + consumerId);
                        }
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
