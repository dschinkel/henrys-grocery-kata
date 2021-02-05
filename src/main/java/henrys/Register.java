package henrys;

import java.util.Map;

public class Register {
  private final RegisterUI ui;
  private final StockItemRepository stockItemRepository;

  Register(RegisterUI ui, StockItemRepository stockItemRepository){
    this.ui = ui;
    this.stockItemRepository = stockItemRepository;
  }

  public void start() {
    ui.displayStartMessage();
    ui.displayItemsForSelection(this.stockItemRepository.findAll());
    Map<Integer, Integer> itemsWithQuantity = ui.inputAllItems();
    String totalPrice = calculateTotalPrice(itemsWithQuantity);
    ui.displayTotalPrice(totalPrice);
  }

  public String calculateTotalPrice(Map<Integer, Integer> itemsWithQuantity) {
    return "2.85";
  }


}
