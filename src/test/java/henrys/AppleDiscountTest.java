package henrys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static henrys.Constants.ItemName.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppleDiscountTest {

  ArrayList<StockItem> stockItemsDB;
  RegisterCalculator registerCalculator;
  AppleDiscount appleDiscount;
  ArrayList<StockItem> purchasedItems;
  private SoupDiscount soupDiscount;

  @BeforeEach
  void setUp() {
    StockItemRepository stockItemRepository = new StockItemRepository();
    stockItemsDB = stockItemRepository.findAll();
    appleDiscount = new AppleDiscount(stockItemRepository);
    soupDiscount = new SoupDiscount(stockItemRepository);
    registerCalculator = new RegisterCalculator(appleDiscount, soupDiscount);
    purchasedItems = new ArrayList<>();
  }

  @Test
  void apples_noDiscount_purchasedToday() {
    addToPurchasedItems(APPLE, 3);
    LocalDate dateToday = LocalDate.now();
    double baseTotalPrice = .30;
    double expectedDiscountedPrice = .30;

    double discountedPrice = appleDiscount.applyAppleTenPercentDiscount(purchasedItems, baseTotalPrice, dateToday);

    assertEquals(expectedDiscountedPrice, discountedPrice);
  }

  @Test
  void threeApples_withDiscount_purchasedThreeDaysFromToday() {
    addToPurchasedItems(APPLE, 3);
    LocalDate dateThreeDaysFromToday = LocalDate.now().plusDays(3);
    double baseTotalPrice = .30;
    double expectedDiscountedPrice = .27;

    double discountedPrice = appleDiscount.applyAppleTenPercentDiscount(purchasedItems, baseTotalPrice, dateThreeDaysFromToday);

    assertEquals(expectedDiscountedPrice, discountedPrice);
  }

  @Test
  void sixApples_and_oneBottleOfMilk_noDiscount_purchasedToday() {
    addToPurchasedItems(APPLE, 6);
    addToPurchasedItems(MILK, 1);
    double baseTotalPriceWithoutDiscount = 1.90;
    LocalDate dateToday = LocalDate.now();

    double discountedPrice = appleDiscount.applyAppleTenPercentDiscount(purchasedItems, baseTotalPriceWithoutDiscount, dateToday);

    assertEquals(baseTotalPriceWithoutDiscount, discountedPrice);
  }

  @Test
  void sixApples_and_oneBottleOfMilk_withDiscount_purchasedWithinThreeDaysFromToday() {
    addToPurchasedItems(APPLE, 6);
    addToPurchasedItems(MILK, 1);
    double baseTotalPrice = 1.90;
    double expectedDiscountedPrice = 1.84;
    LocalDate dateToday = LocalDate.now().plusDays(3);

    double discountedPrice = appleDiscount.applyAppleTenPercentDiscount(purchasedItems, baseTotalPrice, dateToday);

    assertEquals(expectedDiscountedPrice, discountedPrice);
  }

  @Test
  void threeApples_noDiscount_purchasedAfterEndOfFollowingMonth() {
    addToPurchasedItems(APPLE, 3);
    double baseTotalPrice = .30;
    LocalDate afterEndOfNextMonth = LocalDate.now().plusMonths(1).plusDays(1);

    double nonDiscountedPrice = appleDiscount.applyAppleTenPercentDiscount(purchasedItems, baseTotalPrice, afterEndOfNextMonth);

    assertEquals(baseTotalPrice, nonDiscountedPrice);
  }

  private void addToPurchasedItems(Constants.ItemName itemName, Integer howManyToAdd) {
    StockItem item = stockItemsDB.get(itemName.getValue());
    for (int i = 0; i < howManyToAdd; i++) {
      purchasedItems.add(item);
    }
  }
}
