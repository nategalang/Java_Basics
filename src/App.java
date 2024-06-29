import cashier.SimplePOS;

public class App {
    public static void main(String[] args) {
        SimplePOS customer = new SimplePOS();
        
        while(true) {
            boolean option = customer.order();

            if (option) {
                int task = customer.select();

                while (task != 0) {
                    if (task == 1) {
                        customer.add();
                        task = customer.select();
                    }

                    else if (task == 2) {
                        customer.update();
                        task = customer.select();
                    }

                    else if (task == 3) {
                        customer.view();
                        task = customer.select();
                    }

                    else {
                        boolean finish = customer.checkout();
                        if (finish) {
                            customer.reset();
                            task = 0;
                        }
                        else 
                            task = customer.select();
                    }
                }
            }

            else {
                customer.close();
                break;
            }
        }
    }
}
