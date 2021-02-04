package henrys;

public class Shop {
  public static void main(String[] args) {
    CommandLineUI ui = new CommandLineUI();
    Register register = new Register(ui);
    register.start();
  }
}
