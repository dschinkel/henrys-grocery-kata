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
      if(stockItem.getItemId() == 0) {
        total += calculateTotalForSoup(stockItem);
      }
      if(stockItem.getItemId() == 1){
        total += calculateTotalForBread(stockItem);
      }
      if(stockItem.getItemId() == 2){
        total += calculateTotalForMilk(stockItem);
      }
      if(stockItem.getItemId() == 3){
        total += calculateTotalForApples(stockItem);
      }
    }
    return total;
  }

  private Double calculateTotalForApples(StockItem stockItem) {
    return stockItem.getQuantityPurchased() * .10;
  }

  private Double calculateTotalForMilk(StockItem stockItem) {
    return stockItem.getQuantityPurchased() * 1.30;
  }

  private Double calculateTotalForBread(StockItem stockItem) {
    return stockItem.getQuantityPurchased() * .80;
  }

  private double calculateTotalForSoup(StockItem stockItem) {
    return stockItem.getQuantityPurchased() * .65;
  }
}
