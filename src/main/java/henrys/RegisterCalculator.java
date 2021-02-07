package henrys;

import java.time.LocalDate;
import java.util.ArrayList;

import static henrys.Formatter.formatDoubleToPrecisionOfTwo;

public class RegisterCalculator {
  public Double tallyTotalForPurchasedStockItems(ArrayList<StockItem> purchasedItems, LocalDate purchasedDate) {
    Double total = 0.00;
    ItemDiscount itemDiscount = new ItemDiscount();
    total = tallyTotalWithoutDiscounts(purchasedItems);
    total = itemDiscount.applyDiscounts(purchasedItems, total, purchasedDate);

    return formatDoubleToPrecisionOfTwo(total);
  }

  private Double tallyTotalWithoutDiscounts(ArrayList<StockItem> purchasedItems) {
    Double total = 0.00;
    for (StockItem stockItem : purchasedItems) {
      total += stockItem.calculateStockItemTotalCost();
    }
    return formatDoubleToPrecisionOfTwo(total);
  }

}
