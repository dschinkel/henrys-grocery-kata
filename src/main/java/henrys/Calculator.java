package henrys;

import java.util.ArrayList;

public class Calculator {
  public Double calculateTotalPriceForItems(ArrayList<StockItem> purchasedItems) {
    Double total = tallyTotal(purchasedItems);
    return total;
  }

  private Double tallyTotal(ArrayList<StockItem> purchasedItems) {
    Double total = 0.00;
    for (StockItem stockItem : purchasedItems) {
      total += calculateTotalForSoup(stockItem);
    }
    return total;
  }

  private double calculateTotalForSoup(StockItem stockItem) {
    return stockItem.getQuantityPurchased() * .65;
  }
}
