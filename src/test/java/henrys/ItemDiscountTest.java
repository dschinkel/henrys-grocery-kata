package henrys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static henrys.Constants.ItemName.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemDiscountTest {

  ArrayList<StockItem> stockItemsDB;
  RegisterCalculator registerCalculator;
  ItemDiscount itemDiscount;
  ArrayList<StockItem> purchasedItems;

  @BeforeEach
  void setUp() {
    StockItemRepository stockItemRepository = new StockItemRepository();
    stockItemsDB = stockItemRepository.findAll();
    itemDiscount = new ItemDiscount(stockItemRepository);
    registerCalculator = new RegisterCalculator(itemDiscount);
    purchasedItems = new ArrayList<>();
  }

  @Test
  void threeSoup_twoLoavesBread_getOneloafOfBread_halfOff_noDiscount_whenPurchasedPriorToYesterday() {
    addToPurchasedItems(SOUP, 3);
    addToPurchasedItems(BREAD, 2);
    double baseTotalPrice = 3.55;
    LocalDate dateTwoDaysAgo = LocalDate.now().minusDays(2);

    double nonDiscountedPrice = itemDiscount.twoSoupGetOneLoafBreadHalfOff(purchasedItems, baseTotalPrice, dateTwoDaysAgo);

    assertEquals(baseTotalPrice, nonDiscountedPrice);
  }

  @Test
  void threeSoup_twoLoavesBread_withDiscount_purchasedDateToday() {
    addToPurchasedItems(SOUP, 3);
    addToPurchasedItems(BREAD, 2);
    double baseTotalPrice = 3.55;
    double expectdDiscountedPrice = 3.15;
    LocalDate dateTwoDaysAgo = LocalDate.now();

    double discountedPrice = itemDiscount.twoSoupGetOneLoafBreadHalfOff(purchasedItems, baseTotalPrice, dateTwoDaysAgo);

    assertEquals(expectdDiscountedPrice, discountedPrice);
  }

  @Test
  void threeSoup_twoLoavesBread_withDiscount_purchasedYesterday() {
    addToPurchasedItems(SOUP, 3);
    addToPurchasedItems(BREAD, 2);
    double baseTotalPrice = 3.55;
    double expectdDiscountedPrice = 3.15;
    LocalDate dateTwoDaysAgo = LocalDate.now().minusDays(1);

    double discountedPrice = itemDiscount.twoSoupGetOneLoafBreadHalfOff(purchasedItems, baseTotalPrice, dateTwoDaysAgo);

    assertEquals(expectdDiscountedPrice, discountedPrice);
  }

  @Test
  void apples_noDiscount_purchasedToday() {
    addToPurchasedItems(APPLE, 3);
    LocalDate dateToday = LocalDate.now();
    double baseTotalPrice = .30;
    double expectedDiscountedPrice = .30;

    double discountedPrice = itemDiscount.applyAppleTenPercentDiscount(purchasedItems, baseTotalPrice, dateToday);

    assertEquals(expectedDiscountedPrice, discountedPrice);
  }

  @Test
  void threeApples_withDiscount_purchasedThreeDaysFromToday() {
    addToPurchasedItems(APPLE, 3);
    LocalDate dateThreeDaysFromToday = LocalDate.now().plusDays(3);
    double baseTotalPrice = .30;
    double expectedDiscountedPrice = .27;

    double discountedPrice = itemDiscount.applyAppleTenPercentDiscount(purchasedItems, baseTotalPrice, dateThreeDaysFromToday);

    assertEquals(expectedDiscountedPrice, discountedPrice);
  }

  @Test
  void sixApples_and_oneBottleOfMilk_noDiscount_purchasedToday() {
    addToPurchasedItems(APPLE, 6);
    addToPurchasedItems(MILK, 1);
    double baseTotalPriceWithoutDiscount = 1.90;
    LocalDate dateToday = LocalDate.now();

    double discountedPrice = itemDiscount.applyAppleTenPercentDiscount(purchasedItems, baseTotalPriceWithoutDiscount, dateToday);

    assertEquals(baseTotalPriceWithoutDiscount, discountedPrice);
  }

  @Test
  void sixApples_and_oneBottleOfMilk_withDiscount_purchasedWithinThreeDaysFromToday() {
    addToPurchasedItems(APPLE, 6);
    addToPurchasedItems(MILK, 1);
    double baseTotalPrice = 1.90;
    double expectedDiscountedPrice = 1.84;
    LocalDate dateToday = LocalDate.now().plusDays(3);

    double discountedPrice = itemDiscount.applyAppleTenPercentDiscount(purchasedItems, baseTotalPrice, dateToday);

    assertEquals(expectedDiscountedPrice, discountedPrice);
  }

  @Test
  void threeApples_noDiscount_purchasedAfterEndOfFollowingMonth() {
    addToPurchasedItems(APPLE, 3);
    double baseTotalPrice = .30;
    LocalDate afterEndOfNextMonth = LocalDate.now().plusMonths(1).plusDays(1);

    double nonDiscountedPrice = itemDiscount.applyAppleTenPercentDiscount(purchasedItems, baseTotalPrice, afterEndOfNextMonth);

    assertEquals(baseTotalPrice, nonDiscountedPrice);
  }

  private void addToPurchasedItems(Constants.ItemName itemName, Integer howManyToAdd) {
    StockItem item = stockItemsDB.get(itemName.getValue());
    for (int i = 0; i < howManyToAdd; i++) {
      purchasedItems.add(item);
    }
  }
}
