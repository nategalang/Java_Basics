package cashier;

import java.util.Scanner;

class OutOfRange extends Exception {
}

class InsufficientAmount extends Exception {
}

public class SimplePOS {
    private static Scanner scanner = new Scanner(System.in);
    private String[] products = new String[100];
    private int productCount = 0;

    static {
        System.out.println("Welcome to the Byte-sized POS System!");
        System.out.println("Enter the items you wish to purchase:");
    }

    public boolean order() {
        System.out.println("=======MAIN MENU=======");
        System.out.println("[0] Exit");
        System.out.println("[1] New Transaction");
        System.out.println("Select an option:");

        int option = 0;
        boolean valid_input = false;

        while (!valid_input) {
            try {
                option = scanner.nextInt();
                if (option != 0 && option != 1) {
                    throw new OutOfRange();
                }
                valid_input = true;
            }

            catch (OutOfRange e) {
                System.out.println("Please choose between the options provided");
                scanner.nextLine();
            }
            
            catch (Exception e) {
                System.out.println("Please enter a number");
                scanner.nextLine();
            }
        }

        if (option == 0)
            return false;
        return true;
    }

    public double purchase() {
        System.out.println("=======MAIN MENU=======");
        double price = 0;
        
        while(true) {
            System.out.println("[0] Checkout and Print Receipt");
            System.out.println("[1] Algo Roll @ 180.00 PHP");
            System.out.println("[2] Data Maki @ 100.00 PHP");
            System.out.println("[3] OSnigiri @ 125.00 PHP");
            System.out.println("[4] Systemaki @ 175.50 PHP");
            System.out.println("Select an option:");

            int option = 0;
            boolean valid_input = false;
    
            while (!valid_input) {
                try {
                    option = scanner.nextInt();
                    if (option >= 0 && option <= 4) {
                        valid_input = true;
                    }
                    else {
                        throw new OutOfRange();
                    }
                }
                
                catch (OutOfRange e) {
                    System.out.println("Please choose between the options provided");
                    scanner.nextLine();
                }
                
                catch (Exception e) {
                    System.out.println("Please enter a number");
                    scanner.nextLine();
                }
            }

            if (option == 1) {
                this.products[this.productCount] = String.format("%-16s" + "$180.0", "Algo Roll");
                this.productCount++;
                System.out.println("You bought 1 Algo Roll.");
                price += 180.00;
            }

            else if (option == 2) {
                this.products[this.productCount] = String.format("%-16s" + "$100.0", "Data Maki");
                this.productCount++;
                System.out.println("You bought 1 Data Maki.");
                price += 100.00;
            }

            else if (option == 3) {
                this.products[this.productCount] = String.format("%-16s" + "$125.0", "OSnigiri");
                this.productCount++;
                System.out.println("You bought 1 OSnigiri.");
                price += 125.00;
            }

            else if (option == 4) {
                this.products[this.productCount] = String.format("%-16s" + "$175.5", "Systemaki");
                this.productCount++;
                System.out.println("You bought 1 Systemaki.");
                price += 175.50;
            }

            else
                break;
        }

        return price;
    }

    public void checkout(double price) {
        System.out.println("======CHECKOUT RECEIPT=======");

        for(int i = 0; i < productCount; i++) {
            System.out.println(this.products[i]);
        }

        System.out.println("The total amount due is: " + price + " PHP");
        System.out.println("Vat (12%): " + price*0.12);

        System.out.println("Cash Amount:");

        double cash = 0;
        boolean valid_input = false;

        while (!valid_input) {
            try {
                cash = scanner.nextDouble();
                if (cash >= price) {
                    valid_input = true;
                }
                else {
                    throw new InsufficientAmount();
                }
            }

            catch (InsufficientAmount e) {
                System.out.println("The amount is insufficient");
                scanner.nextLine();
            }
            
            catch (Exception e) {
                System.out.println("Please enter a number");
                scanner.nextLine();
            }
        }

        System.out.println("Change: " + (cash - price));
        System.out.println("Thank you for shopping with us!");
        System.out.println("======CHECKOUT RECEIPT=======\n");
    }

    public void reset() {
        this.products = new String[100];
        this.productCount = 0;
    }

    public void close() {
        scanner.close();
    }
}