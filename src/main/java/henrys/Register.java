package henrys;

import java.util.ArrayList;
import java.util.Map;

public class Register {
  private final RegisterUI ui;
  private final StockItemRepository stockItemRepository;
  private Calculator calculator;

  Register(RegisterUI ui, StockItemRepository stockItemRepository, Calculator calculator){
    this.ui = ui;
    this.stockItemRepository = stockItemRepository;
    this.calculator = calculator;
  }

  public void start() {
    ui.displayStartMessage();
    ui.displayItemsForSelection(this.stockItemRepository.findAll());
    Map<Integer, Integer> itemsWithQuantity = ui.inputAllItems();
    String totalPrice = calculateTotalPrice(itemsWithQuantity);
    ui.displayTotalPrice(totalPrice);
  }

  public String calculateTotalPrice(Map<Integer, Integer> itemsWithQuantity) {
    Double totalPrice = 0.00;
    ArrayList<StockItem> purchasedItems = new ArrayList<>();

    if(itemsWithQuantity == null) return totalPrice.toString();

    for (Map.Entry<Integer,Integer> item : itemsWithQuantity.entrySet()) {
      StockItem stockItem = new StockItem();
      stockItem.setQuantityPurchased(item.getValue());
      purchasedItems.add(stockItem);
    }

     totalPrice = calculator.calculateTotalPriceForItems(purchasedItems);

    return totalPrice.toString();
  }

}
