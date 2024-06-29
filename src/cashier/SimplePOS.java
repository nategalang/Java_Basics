package cashier;

import java.util.Scanner;

class OutOfRange extends Exception {
}

class InsufficientAmount extends Exception {
}

public class SimplePOS {
    private static Scanner scanner = new Scanner(System.in);
    private int product_count = 0;
    private int algo_roll_count = 0;
    private int data_maki_count = 0;
    private int osnigiri_count = 0;
    private int systemaki_count = 0;
    private double price = 0.0;

    static {
        System.out.println("Welcome to the Byte-sized POS System!\nEnter the items you wish to purchase:");
    }

    public boolean order() {
        String statement = "======= :: MAIN MENU :: =======\n[0] Exit\n[1] Order\nSelect an option: ";

        int option = 0;
        boolean valid_option = false;

        while (!valid_option) {
            System.out.print(statement);

            try {
                option = scanner.nextInt();
                if (!(option == 0 || option == 1))
                    throw new OutOfRange();
                valid_option = true;
            }
    
            catch (OutOfRange e) {
                System.out.println("======= :: ERROR :: =======\nPlease choose between the options");
                scanner.nextLine();
            }
                
            catch (Exception e) {
                System.out.println("======= :: ERROR :: =======\nPlease enter a number");
                scanner.nextLine();
            }
        }

        if (option == 0)
            return false;
        return true;
    }

    public int select() {
        String statement = "======= :: OPTIONS :: =======\n[0] Exit\n[1] Add\n[2] Update\n[3] View\n[4] Checkout\nSelect an option: ";

        int option = 0;
        boolean valid_option = false;

        while (!valid_option) {
            System.out.print(statement);

            try {
                option = scanner.nextInt();
                if (!(option >= 0 && option <=4))
                    throw new OutOfRange();
                valid_option = true;
            }

            catch (OutOfRange e) {
                System.out.println("======= :: ERROR :: =======\nPlease choose between the options");
                scanner.nextLine();
            }
                
            catch (Exception e) {
                System.out.println("======= :: ERROR :: =======\nPlease enter a number");
                scanner.nextLine();
            }
        }

        return option;
    }

    public void add() {
        String statement = "======= :: FOODS :: =======\n[0] Go back\n[1] Algo Roll @ 180.00 PHP\n[2] Data Maki @ 100.00 PHP\n[3] OSnigiri @ 125.00 PHP\n[4] Systemaki @ 175.50 PHP\nSelect an option: ";
        
        while (true) {
            int option = 0;
            boolean valid_option = false;

            while(!valid_option) {
                System.out.print(statement);

                try {
                    option = scanner.nextInt();
                    if (!(option >= 0 && option <=4))
                        throw new OutOfRange();
                    valid_option = true;
                }
        
                catch (OutOfRange e) {
                    System.out.println("======= :: ERROR :: =======\nPlease choose between the options");
                    scanner.nextLine();
                }
                    
                catch (Exception e) {
                    System.out.println("======= :: ERROR :: =======\nPlease enter a number");
                    scanner.nextLine();
                }
            }

            if (option == 0)
                break;

            else {
                if (this.product_count == 100) {
                    System.out.println("Reached the maximum amount, redirecting...");
                    break;
                }

                else {
                    if (option == 1) {
                        int amount = inputCheck("Algo Roll");
                        System.out.println("You added " + amount + " Algo Roll.");
                        this.product_count += amount;
                        this.algo_roll_count += amount;
                    }
        
                    else if (option == 2) {
                        int amount = inputCheck("Data Maki");
                        System.out.println("You added " + amount + " Data Maki.");
                        this.product_count += amount;
                        this.data_maki_count += amount;
                    }
        
                    else if (option == 3) {
                        int amount = inputCheck("OSnigiri");
                        System.out.println("You added " + amount + " OSnigiri.");
                        this.product_count += amount;
                        this.osnigiri_count += amount;
                    }
        
                    else {
                        int amount = inputCheck("Systemaki");
                        System.out.println("You added " + amount + " Systemaki.");
                        this.product_count += amount;
                        this.systemaki_count += amount;
                    }
                }
            }
        }
    }

    public void view() {
        System.out.println("======= :: CART :: =======");
        int item_count = 1;

        if (this.product_count == 0) {
            System.out.println("Your cart is empty");
        }

        else {
            if (this.algo_roll_count != 0) {
                System.out.printf("[%d] %d pcs. Algo Roll @ %.2f PHP\n", item_count, this.algo_roll_count, this.algo_roll_count*180.00);
                item_count++;
            }

            if (this.data_maki_count != 0) {
                System.out.printf("[%d] %d pcs. Data Maki @ %.2f PHP\n", item_count, this.data_maki_count, this.data_maki_count*100.00);
                item_count++;
            }
            
            if (this.osnigiri_count != 0) {
                System.out.printf("[%d] %d pcs. OSnigiri @ %.2f PHP\n", item_count, this.osnigiri_count, this.osnigiri_count*125.00);
                item_count++;
            }
            
            if (this.systemaki_count != 0) {
                System.out.printf("[%d] %d pcs. Systemaki @ %.2f PHP\n", item_count, this.systemaki_count, this.systemaki_count*175.50);
                item_count++;
            }
        }
    }
    
    public void update() {
        String statement = "======= :: UPDATE :: =======\n[0] Go back\n[1] Algo Roll\n[2] Data Maki\n[3] OSnigiri\n[4] Systemaki\nSelect an option: ";

        while (true) {
            int option = 0;
            boolean valid_option = false;

            while(!valid_option) {
                view();
                System.out.print(statement);

                try {
                    option = scanner.nextInt();
                    if (!(option >= 0 && option <=4))
                        throw new OutOfRange();
                    valid_option = true;
                }
        
                catch (OutOfRange e) {
                    System.out.println("======= :: ERROR :: =======\nPlease choose between the options");
                    scanner.nextLine();
                }
                    
                catch (Exception e) {
                    System.out.println("======= :: ERROR :: =======\nPlease enter a number");
                    scanner.nextLine();
                }
            }

            if (option == 0)
                break;

            else {
                if (option == 1) {
                    this.product_count -= this.algo_roll_count;
                    int amount = inputCheck("Algo Roll");
                    System.out.println("Quantity of Algo Roll updated to " + amount + " pcs.");
                    this.product_count += amount;
                    this.algo_roll_count = amount;
                }
    
                else if (option == 2) {
                    this.product_count -= this.data_maki_count;
                    int amount = inputCheck("Data Maki");
                    System.out.println("Quantity of Data Maki updated to " + amount + " pcs.");
                    this.product_count += amount;
                    this.data_maki_count = amount;
                }
    
                else if (option == 3) {
                    this.product_count -= this.osnigiri_count;
                    int amount = inputCheck("OSnigiri");
                    System.out.println("Quantity of OSnigiri updated to " + amount + " pcs.");
                    this.product_count += amount;
                    this.osnigiri_count = amount;
                }
    
                else {
                    this.product_count -= this.systemaki_count;
                    int amount = inputCheck("Systemaki");
                    System.out.println("Quantity of Systemaki updated to " + amount + " pcs.");
                    this.product_count += amount;
                    this.systemaki_count = amount;
                }
            }
        }
    }

    public boolean checkout() {
        if (this.product_count == 0) {
            System.out.println("Your cart is empty");
            return false;
        }

        else {
            System.out.println("======CHECKOUT RECEIPT=======");

            view();

            this.price = this.algo_roll_count * 180.0 + this.data_maki_count * 100.0 + this.osnigiri_count * 125.0 + this.systemaki_count * 175.5;
            double cash = 0;
            boolean valid_cash = false;

            while (!valid_cash) {
                System.out.println("The total amount due is: " + this.price + " PHP");
                System.out.println("Vat (12%): " + this.price*0.12);
                System.out.print("Enter cash amount: ");

                try {
                    cash = scanner.nextDouble();
                    if (!(cash >= price)) 
                        throw new InsufficientAmount();
                    valid_cash = true;
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

            System.out.println("Change: " + (cash - this.price));
            System.out.println("Thank you for shopping with us!");
            System.out.println("======CHECKOUT RECEIPT=======\n");

            return true;
        }
    }

    public int inputCheck(String order) {
        int amount = 0;
        boolean valid_amount = false;

        while (!valid_amount) {
            System.out.print("Enter amount of " + order + ": ");

            try {
                amount = scanner.nextInt();
                if (!(amount >= 0 && amount <= (100 - this.product_count)))
                    throw new OutOfRange();
                valid_amount = true;
            }
    
            catch (OutOfRange e) {
                System.out.println("======= :: ERROR :: =======\nAmount exceeds limit");
                scanner.nextLine();
            }
                
            catch (Exception e) {
                System.out.println("======= :: ERROR :: =======\nPlease enter a number");
                scanner.nextLine();
            }
        }

        return amount;
    }

    public void reset() {
        this.product_count = 0;
        this.algo_roll_count = 0;
        this.data_maki_count = 0;
        this.osnigiri_count = 0;
        this.systemaki_count = 0;
        this.price = 0.0;
    }

    public void close() {
        System.out.println("Goodbye!");
        scanner.close();
    }
}