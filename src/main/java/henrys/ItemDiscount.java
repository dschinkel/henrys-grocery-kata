package henrys;

import java.time.LocalDate;
import java.util.ArrayList;

import static henrys.Utilities.formatDoubleToPrecisionOfTwo;
import static henrys.StockItem.ItemName.*;

public class ItemDiscount {
  ArrayList<StockItem> stockItemsDB = new StockItemRepository().findAll();

  public Double applyDiscounts(ArrayList<StockItem> purchasedItems, Double baseTotal, LocalDate purchasedDate) {
    Double totalWithDiscounts = twoSoupGetOneLoafBreadHalfOff(purchasedItems, baseTotal, purchasedDate);
    totalWithDiscounts = applyAppleTenPercentDiscount(purchasedItems, totalWithDiscounts, purchasedDate);
    return totalWithDiscounts;
  }

  public Double twoSoupGetOneLoafBreadHalfOff(ArrayList<StockItem> purchasedItems, Double itemsTotalPrice, LocalDate purchasedDate) {
    if (lessThanTwoSoupsPurchased(purchasedItems) ) { return itemsTotalPrice; }
    if (notPurchasedBetweenYesterdayForSevenDays(purchasedDate)) return itemsTotalPrice;

    StockItem bread = stockItemsDB.get(BREAD.getValue());
    double totalBreadDiscountAmt = bread.getItemPricePerUnit() / 2;
    Double discountedPrice = itemsTotalPrice + -(totalBreadDiscountAmt);

    return discountedPrice;
  }

  public Double applyAppleTenPercentDiscount(ArrayList<StockItem> purchasedItems, Double itemsTotalPrice, LocalDate purchasedDate) {
    StockItem apple = stockItemsDB.get(APPLE.getValue());
    if (noApplesPurchased(purchasedItems)) { return itemsTotalPrice; }
    if (notPurchasedBetweenThreeDaysFromTodayUntilEndOfFollowingMonth(purchasedDate)) return itemsTotalPrice;

    Double discountedPrice = calculateDiscountedPriceForApples(itemsTotalPrice, purchasedItems, apple);

    return discountedPrice;
  }

  private boolean noApplesPurchased(ArrayList<StockItem> purchasedItems) {
    long purchasedAppleQty = countOfStockItemsByType(purchasedItems, APPLE);
    return purchasedAppleQty == 0;
  }

  private boolean lessThanTwoSoupsPurchased(ArrayList<StockItem> purchasedItems) {
    long purchasedSoupQty = countOfStockItemsByType(purchasedItems, SOUP);
    return purchasedSoupQty < 2;
  }

  private boolean notPurchasedBetweenThreeDaysFromTodayUntilEndOfFollowingMonth(LocalDate purchasedDate) {
    LocalDate threeDaysFromToday = LocalDate.now().plusDays(3);
    return purchasedDate.isBefore(threeDaysFromToday);
  }

  private Double calculateDiscountedPriceForApples(Double totalWithoutDiscounts, ArrayList<StockItem> purchasedItems, StockItem apple) {
    long purchasedAppleQty = countOfStockItemsByType(purchasedItems, APPLE);
    double applesTotalBasePrice = apple.getItemPricePerUnit() * purchasedAppleQty;
    Double totalAppleDiscountAmount = (applesTotalBasePrice * .10);

    Double discountedPrice = totalWithoutDiscounts - totalAppleDiscountAmount;

    return formatDoubleToPrecisionOfTwo(discountedPrice);
  }

  private long countOfStockItemsByType(ArrayList<StockItem> purchasedItems, StockItem.ItemName itemName) {
    return purchasedItems.stream().filter(item -> item.getItemId() == itemName.getValue()).count();
  }

  private boolean notPurchasedBetweenYesterdayForSevenDays(LocalDate purchasedDate) {
    LocalDate yesterday = LocalDate.now().minusDays(1);
    return purchasedDate.isBefore(yesterday);
  }
}
