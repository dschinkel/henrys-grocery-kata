package henrys;

public class Shop {
  public static void main(String[] args) {
    CommandLineUI ui = new CommandLineUI();
    StockItemRepository repository = new StockItemRepository();
    ItemDiscount itemDiscount = new ItemDiscount(repository);
    RegisterCalculator registerCalculator = new RegisterCalculator(itemDiscount);
    Register register = new Register(ui, repository, registerCalculator);
    register.start();
  }
}
