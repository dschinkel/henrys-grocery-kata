package henrys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

public class Register {
  private final RegisterUI ui;
  private final StockItemRepository stockItemRepository;
  private final RegisterCalculator registerCalculator;
  ArrayList<StockItem> stockItemsDB;

  Register(RegisterUI ui, StockItemRepository stockItemRepository, RegisterCalculator registerCalculator) {
    this.ui = ui;
    this.stockItemRepository = stockItemRepository;
    this.registerCalculator = registerCalculator;
    stockItemsDB = stockItemRepository.findAll();
  }

  public void start() {
    ui.displayStartMessage();
    ui.displayItemsForSelection(this.stockItemRepository.findAll());
    Object[] inputsFromUI = ui.inputAllItems();
    displayTotalPrice(inputsFromUI);
  }

  private void displayTotalPrice(Object[] inputsFromUI) {
    if (inputsFromUI == null) return;
    Map<Integer, Integer> itemsWithQuantity = (Map<Integer, Integer>) inputsFromUI[0];
    LocalDate purchasedDate = convertPurchasedDateToLocalDate(inputsFromUI[1]);
    double totalPrice = calculateTotalPrice(itemsWithQuantity, purchasedDate);

    ui.displayTotalPrice(totalPrice);
  }

  public double calculateTotalPrice(Map<Integer, Integer> itemsWithQuantity, LocalDate purchasedDate) {
    Double totalPrice = 0.00;
    if (noItems(itemsWithQuantity)) return totalPrice;
    ArrayList<StockItem> purchasedItems = convertItemsToStockItems(itemsWithQuantity);

    totalPrice = getTotalPrice(purchasedItems, purchasedDate);
    return totalPrice;
  }

  private double getTotalPrice(ArrayList<StockItem> purchasedItems, LocalDate purchasedDate) {
    return registerCalculator.tallyTotalForPurchasedStockItems(purchasedItems, purchasedDate);
  }

  private boolean noItems(Map<Integer, Integer> itemsWithQuantity) {
    return itemsWithQuantity == null;
  }

  private ArrayList<StockItem> convertItemsToStockItems(Map<Integer, Integer> itemsWithQuantity) {
    ArrayList<StockItem> purchasedItems = new ArrayList<>();
    convertToStockItems(itemsWithQuantity, purchasedItems);
    return purchasedItems;
  }

  private void convertToStockItems(Map<Integer, Integer> itemsWithQuantity, ArrayList<StockItem> purchasedItems) {
    itemsWithQuantity.forEach((itemId, quantityPurchased) -> {
      createStockItemBasedOnItemQuantity(purchasedItems, itemId, quantityPurchased);
    });
  }

  private void createStockItemBasedOnItemQuantity(ArrayList<StockItem> purchasedItems, Integer itemId, Integer quantityPurchased) {
    for (int i = 0; i < quantityPurchased; i++) {
      StockItem stockItem = new StockItem();
      stockItem.setItemId(itemId);
      stockItem.setPricePerUnit(stockItemsDB.get(itemId).getItemPricePerUnit());

      purchasedItems.add(stockItem);
    }
  }

  private LocalDate convertPurchasedDateToLocalDate(Object o) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
    LocalDate date = LocalDate.parse((String) o, formatter);
    return date;
  }
};
