package henrys;

import java.time.LocalDate;
import java.util.ArrayList;

import static henrys.Utilities.formatDoubleToPrecisionOfTwo;

public class RegisterCalculator {
  public double tallyTotalForPurchasedStockItems(ArrayList<StockItem> purchasedItems, LocalDate purchasedDate) {
    double total;
    ItemDiscount itemDiscount = new ItemDiscount();
    total = tallyTotalWithoutDiscounts(purchasedItems);
    total = itemDiscount.applyDiscounts(purchasedItems, total, purchasedDate);

    return formatDoubleToPrecisionOfTwo(total);
  }

  private double tallyTotalWithoutDiscounts(ArrayList<StockItem> purchasedItems) {
    double total = 0.00;
    for (StockItem stockItem : purchasedItems) {
      total += stockItem.calculateStockItemTotalCost();
    }
    return formatDoubleToPrecisionOfTwo(total);
  }

}
