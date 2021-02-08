package henrys;

import java.time.LocalDate;
import java.util.ArrayList;

import static henrys.Constants.ItemName.BREAD;
import static henrys.Constants.ItemName.SOUP;

public class SoupDiscount {
  private final ArrayList<StockItem> stockItemsDB;

  public SoupDiscount(StockItemRepository stockItemRepository) {
    this.stockItemsDB = stockItemRepository.findAll();
  }

  public double applyDiscounts(ArrayList<StockItem> purchasedItems, double baseTotal, LocalDate purchasedDate) {
    double totalWithDiscounts = twoSoupGetOneLoafBreadHalfOff(purchasedItems, baseTotal, purchasedDate);
    return totalWithDiscounts;
  }

  public double twoSoupGetOneLoafBreadHalfOff(ArrayList<StockItem> purchasedItems, double itemsTotalPrice, LocalDate purchasedDate) {
    if (lessThanTwoSoupsPurchased(purchasedItems) ) { return itemsTotalPrice; }
    if (notPurchasedBetweenYesterdayForSevenDays(purchasedDate)) return itemsTotalPrice;

    StockItem bread = stockItemsDB.get(BREAD.getValue());
    double totalBreadDiscountAmt = bread.getItemPricePerUnit() / 2;
    double discountedPrice = itemsTotalPrice + -(totalBreadDiscountAmt);

    return discountedPrice;
  }

  private boolean lessThanTwoSoupsPurchased(ArrayList<StockItem> purchasedItems) {
    long purchasedSoupQty = countOfStockItemsByType(purchasedItems, SOUP);
    return purchasedSoupQty < 2;
  }

  private long countOfStockItemsByType(ArrayList<StockItem> purchasedItems, Constants.ItemName itemName) {
    return purchasedItems.stream().filter(item -> item.getItemId() == itemName.getValue()).count();
  }

  private boolean notPurchasedBetweenYesterdayForSevenDays(LocalDate purchasedDate) {
    LocalDate yesterday = LocalDate.now().minusDays(1);
    return purchasedDate.isBefore(yesterday);
  }
}
