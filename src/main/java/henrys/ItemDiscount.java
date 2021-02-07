package henrys;

import java.time.LocalDate;
import java.util.ArrayList;

import static henrys.Constants.ItemName.*;
import static henrys.Utilities.formatDoubleToPrecisionOfTwo;

public class ItemDiscount {
  ArrayList<StockItem> stockItemsDB = new StockItemRepository().findAll();

  public double applyDiscounts(ArrayList<StockItem> purchasedItems, double baseTotal, LocalDate purchasedDate) {
    double totalWithDiscounts = twoSoupGetOneLoafBreadHalfOff(purchasedItems, baseTotal, purchasedDate);
    totalWithDiscounts = applyAppleTenPercentDiscount(purchasedItems, totalWithDiscounts, purchasedDate);
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

  public double applyAppleTenPercentDiscount(ArrayList<StockItem> purchasedItems, double itemsTotalPrice, LocalDate purchasedDate) {
    StockItem apple = stockItemsDB.get(APPLE.getValue());
    if (noApplesPurchased(purchasedItems)) { return itemsTotalPrice; }
    if (notPurchasedBetweenThreeDaysFromTodayUntilEndOfFollowingMonth(purchasedDate)) return itemsTotalPrice;

    double discountedPrice = calculateDiscountedPriceForApples(itemsTotalPrice, purchasedItems, apple);

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

  private double calculateDiscountedPriceForApples(double totalWithoutDiscounts, ArrayList<StockItem> purchasedItems, StockItem apple) {
    long purchasedAppleQty = countOfStockItemsByType(purchasedItems, APPLE);
    double applesTotalBasePrice = apple.getItemPricePerUnit() * purchasedAppleQty;
    double totalAppleDiscountAmount = (applesTotalBasePrice * .10);

    double discountedPrice = totalWithoutDiscounts - totalAppleDiscountAmount;

    return formatDoubleToPrecisionOfTwo(discountedPrice);
  }

  private long countOfStockItemsByType(ArrayList<StockItem> purchasedItems, Constants.ItemName itemName) {
    return purchasedItems.stream().filter(item -> item.getItemId() == itemName.getValue()).count();
  }

  private boolean notPurchasedBetweenYesterdayForSevenDays(LocalDate purchasedDate) {
    LocalDate yesterday = LocalDate.now().minusDays(1);
    return purchasedDate.isBefore(yesterday);
  }
}
