import cashier.SimplePOS;

public class App {
    public static void main(String[] args) {
        SimplePOS customer = new SimplePOS();
        
        while(true) {
            boolean option = customer.order();
            if(option) {
                double price = customer.purchase();
                customer.checkout(price);
                customer.reset();
            }
            else{
                customer.close();
                break;
            }
        }
    }
}
