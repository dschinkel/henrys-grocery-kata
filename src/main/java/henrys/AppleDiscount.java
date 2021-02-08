package henrys;

import java.time.LocalDate;
import java.util.ArrayList;

import static henrys.Constants.ItemName.*;
import static henrys.Utilities.formatDoubleToPrecisionOfTwo;

public class AppleDiscount {
  private final ArrayList<StockItem> stockItemsDB;

  public AppleDiscount(StockItemRepository stockItemRepository) {
    this.stockItemsDB = stockItemRepository.findAll();
  }

  public double applyDiscounts(ArrayList<StockItem> purchasedItems, double baseTotal, LocalDate purchasedDate) {
    double totalWithDiscounts = applyAppleTenPercentDiscount(purchasedItems, baseTotal, purchasedDate);
    return totalWithDiscounts;
  }

  public double applyAppleTenPercentDiscount(ArrayList<StockItem> purchasedItems, double itemsTotalPrice, LocalDate purchasedDate) {
    StockItem apple = stockItemsDB.get(APPLE.getValue());
    if (noApplesPurchased(purchasedItems)) { return itemsTotalPrice; }
    if (notPurchasedBetweenThreeDaysFromTodayUntilEndOfFollowingMonth(purchasedDate)) return itemsTotalPrice;

    double discountedPrice = calculateDiscountedPriceForApples(itemsTotalPrice, purchasedItems, apple);

    return discountedPrice;
  }

  private double calculateDiscountedPriceForApples(double totalWithoutDiscounts, ArrayList<StockItem> purchasedItems, StockItem apple) {
    long purchasedAppleQty = countOfStockItemsByType(purchasedItems, APPLE);
    double applesTotalBasePrice = apple.getItemPricePerUnit() * purchasedAppleQty;
    double totalAppleDiscountAmount = (applesTotalBasePrice * .10);

    double discountedPrice = totalWithoutDiscounts - totalAppleDiscountAmount;

    return formatDoubleToPrecisionOfTwo(discountedPrice);
  }

  private boolean noApplesPurchased(ArrayList<StockItem> purchasedItems) {
    long purchasedAppleQty = countOfStockItemsByType(purchasedItems, APPLE);
    return purchasedAppleQty == 0;
  }

  private boolean notPurchasedBetweenThreeDaysFromTodayUntilEndOfFollowingMonth(LocalDate purchasedDate) {
    LocalDate threeDaysFromToday = LocalDate.now().plusDays(3);
    LocalDate endOfMonth = LocalDate.now().plusMonths(1);
    boolean notPurchasedWithinValidDateRange = purchasedDate.isBefore(threeDaysFromToday) || purchasedDate.isAfter(endOfMonth);
    return notPurchasedWithinValidDateRange;
  }

  private long countOfStockItemsByType(ArrayList<StockItem> purchasedItems, Constants.ItemName itemName) {
    return purchasedItems.stream().filter(item -> item.getItemId() == itemName.getValue()).count();
  }
}
