package henrys;

public class Shop {
  public static void main(String[] args) {
    CommandLineUI ui = new CommandLineUI();
    StockItemRepository repository = new StockItemRepository();
    Calculator calculator = new Calculator();
    Register register = new Register(ui, repository, calculator);
    register.start();
  }
}
