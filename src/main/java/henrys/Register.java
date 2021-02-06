package henrys;

import java.util.ArrayList;
import java.util.Map;

public class Register {
  private final RegisterUI ui;
  private final StockItemRepository stockItemRepository;
  private RegisterCalculator registerCalculator;
  ArrayList<StockItem> stockItemsDB;

  Register(RegisterUI ui, StockItemRepository stockItemRepository, RegisterCalculator registerCalculator){
    this.ui = ui;
    this.stockItemRepository = stockItemRepository;
    this.registerCalculator = registerCalculator;
    stockItemsDB = new StockItemRepository().findAll();
  }

  public void start() {
    ui.displayStartMessage();
    ui.displayItemsForSelection(this.stockItemRepository.findAll());
    Map<Integer, Integer> itemsWithQuantity = ui.inputAllItems();
    displayTotalPrice(itemsWithQuantity);
  }

  private void displayTotalPrice(Map<Integer, Integer> itemsWithQuantity) {
    String totalPrice = calculateTotalPrice(itemsWithQuantity);
    ui.displayTotalPrice(totalPrice);
  }

  public String calculateTotalPrice(Map<Integer, Integer> itemsWithQuantity) {
    Double totalPrice = 0.00;
    if(noItems(itemsWithQuantity)) return totalPrice.toString();
    ArrayList<StockItem> purchasedItems = convertItemsToStockItems(itemsWithQuantity);

    totalPrice = getTotalPrice(purchasedItems);

    return totalPrice.toString();
  }

  private Double getTotalPrice(ArrayList<StockItem> purchasedItems) {
    return registerCalculator.tallyTotalForPurchasedStockItems(purchasedItems);
  }

  private boolean noItems(Map<Integer, Integer> itemsWithQuantity) {
    return itemsWithQuantity == null;
  }

  private ArrayList<StockItem> convertItemsToStockItems(Map<Integer, Integer> itemsWithQuantity) {
    ArrayList<StockItem> purchasedItems = new ArrayList<>();

    for (Map.Entry<Integer, Integer> item : itemsWithQuantity.entrySet()) {
      StockItem stockItem = new StockItem();
      stockItem.setQuantityPurchased(item.getValue());
      stockItem.setPricePerUnit(stockItemsDB.get(item.getKey()).getItemPricePerUnit());
      purchasedItems.add(stockItem);
    }

    return purchasedItems;
  }
};
