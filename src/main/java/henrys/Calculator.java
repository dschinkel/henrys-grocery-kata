package henrys;

import java.util.ArrayList;

public class Calculator {
  public Double calculateTotalPriceForItems(ArrayList<StockItem> purchasedItems) {
    Double total = 0.00;
    total = tallyTotal(purchasedItems, total);
    return total;
  }

  private Double tallyTotal(ArrayList<StockItem> purchasedItems, Double total) {
    for (StockItem stockItem : purchasedItems) {
      total += stockItem.getQuantityPurchased() * .65;
    }
    return total;
  }
}
