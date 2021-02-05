package henrys;

public class Shop {
  public static void main(String[] args) {
    CommandLineUI ui = new CommandLineUI();
    StockItemRepository repository = new StockItemRepository();
    RegisterCalculator registerCalculator = new RegisterCalculator();
    Register register = new Register(ui, repository, registerCalculator);
    register.start();
  }
}
