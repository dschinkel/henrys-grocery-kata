package henrys;

import java.util.ArrayList;

import static henrys.Formatter.formatDoubleToPrecisionOfTwo;
import static henrys.Formatter.subtractDoubles;
import static henrys.StockItem.ItemName.APPLE;
import static henrys.StockItem.ItemName.BREAD;

public class ItemDiscount {
  ArrayList<StockItem> stockItemsDB = new StockItemRepository().findAll();

  public Double applyDiscounts(ArrayList<StockItem> purchasedItems, Double baseTotal) {
    Double totalWithDiscounts = twoSoupGetOneLoafBreadHalfOff(purchasedItems, baseTotal);
    totalWithDiscounts = applyAppleTenPercentDiscount(purchasedItems, totalWithDiscounts);
    return formatDoubleToPrecisionOfTwo(totalWithDiscounts);
  }

  public Double twoSoupGetOneLoafBreadHalfOff(ArrayList<StockItem> purchasedItems, Double itemsTotalPrice) {
    long purchasedSoupQty = countOfStockItemsByType(purchasedItems, BREAD);

    if (purchasedSoupQty == 0) { return itemsTotalPrice; }

    StockItem bread = stockItemsDB.get(BREAD.getValue());
    Double totalBreadDiscountAmt = bread.getItemPricePerUnit() / 2;

    if (purchasedSoupQty < 2) { return itemsTotalPrice; }

    Double discountedPrice = itemsTotalPrice + -(totalBreadDiscountAmt);
    return formatDoubleToPrecisionOfTwo(discountedPrice);
  }

  public Double applyAppleTenPercentDiscount(ArrayList<StockItem> purchasedItems, Double itemsTotalPrice) {
    long purchasedAppleQty = countOfStockItemsByType(purchasedItems, APPLE);
    StockItem apple = stockItemsDB.get(APPLE.getValue());

    if (purchasedAppleQty == 0) { return itemsTotalPrice; }

    Double discountedPrice = calculateDiscountedPriceForApples(itemsTotalPrice, purchasedAppleQty, apple);

    return formatDoubleToPrecisionOfTwo(discountedPrice);
  }

  private Double calculateDiscountedPriceForApples(Double totalWithoutDiscounts, long appleQuantity, StockItem apple) {
    Double applesTotalBasePrice = apple.getItemPricePerUnit() * appleQuantity;
    Double totalAppleDiscountAmount = (applesTotalBasePrice * .10);
    Double discountedPrice = subtractDoubles(totalWithoutDiscounts, totalAppleDiscountAmount);
    return formatDoubleToPrecisionOfTwo(discountedPrice);
  }

  private long countOfStockItemsByType(ArrayList<StockItem> purchasedItems, StockItem.ItemName itemName) {
    return purchasedItems.stream().filter(item -> item.getItemId() == itemName.getValue()).count();
  }
}
