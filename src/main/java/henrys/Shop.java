package henrys;

public class Shop {
  public static void main(String[] args) {
    CommandLineUI ui = new CommandLineUI();
    StockItemRepository repository = new StockItemRepository();
    AppleDiscount appleDiscount = new AppleDiscount(repository);
    SoupDiscount soupDiscount = new SoupDiscount(repository);
    RegisterCalculator registerCalculator = new RegisterCalculator(appleDiscount, soupDiscount);
    Register register = new Register(ui, repository, registerCalculator);
    register.start();
  }
}
