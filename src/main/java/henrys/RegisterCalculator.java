package henrys;

import java.time.LocalDate;
import java.util.ArrayList;

import static henrys.Utilities.formatDoubleToPrecisionOfTwo;

public class RegisterCalculator {

  private final AppleDiscount appleDiscount;
  private SoupDiscount soupDiscount;

  public RegisterCalculator(AppleDiscount appleDiscount, SoupDiscount soupDiscount) {
    this.soupDiscount = soupDiscount;
    this.appleDiscount = appleDiscount;
  }

  public double tallyTotalForPurchasedStockItems(ArrayList<StockItem> purchasedItems, LocalDate purchasedDate) {
    double total;
    total = tallyTotalWithoutDiscounts(purchasedItems);
    total = this.appleDiscount.applyDiscounts(purchasedItems, total, purchasedDate);
    total = this.soupDiscount.applyDiscounts(purchasedItems, total, purchasedDate);

    return formatDoubleToPrecisionOfTwo(total);
  }

  private double tallyTotalWithoutDiscounts(ArrayList<StockItem> purchasedItems) {
    double total = 0.00;
    for (StockItem stockItem : purchasedItems) {
      total += stockItem.formatStockItemTotalCost();
    }
    return formatDoubleToPrecisionOfTwo(total);
  }

}
