package henrys;

public class Shop {
  public static void main(String[] args) {
    CommandLineUI ui = new CommandLineUI();
    StockItemRepository repository = new StockItemRepository();
    Register register = new Register(ui, repository);
    register.start();
  }
}
