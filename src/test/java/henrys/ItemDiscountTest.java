package henrys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static henrys.StockItem.ItemName.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemDiscountTest {

  ArrayList<StockItem> stockItemsDB;
  RegisterCalculator registerCalculator;
  ItemDiscount itemDiscount;
  ArrayList<StockItem> purchasedItems;

  @BeforeEach
  void setUp() {
    stockItemsDB = new StockItemRepository().findAll();
    registerCalculator = new RegisterCalculator();
    itemDiscount = new ItemDiscount();
    purchasedItems = new ArrayList<>();
  }

  @Test
  void threeSoup_twoLoavesBread_getOneloafOfBread_halfOff_purchasedDateOutOfRangeOfDiscount() {
    addItemsToPurchasedItems(SOUP, 3);
    addItemsToPurchasedItems(BREAD, 2);
    Double baseTotalPrice = 3.55;
    LocalDate dateTwoDaysAgo = LocalDate.now().minusDays(2);

    Double discountedPrice = itemDiscount.twoSoupGetOneLoafBreadHalfOff(purchasedItems, baseTotalPrice, dateTwoDaysAgo);

    assertEquals(baseTotalPrice, discountedPrice);
  }

  @Test
  void threeSoup_twoLoavesBread_purchasedDateToday() {
    addItemsToPurchasedItems(SOUP, 3);
    addItemsToPurchasedItems(BREAD, 2);
    Double baseTotalPrice = 3.55;
    LocalDate dateTwoDaysAgo = LocalDate.now();
    Double expectdDiscountedPrice = 3.15;

    Double discountedPrice = itemDiscount.twoSoupGetOneLoafBreadHalfOff(purchasedItems, baseTotalPrice, dateTwoDaysAgo);

    assertEquals(expectdDiscountedPrice, discountedPrice);
  }

  @Test
  void sixApples_and_oneBottleOfMilk_purchasedThreeDaysFromToday() {
    addItemsToPurchasedItems(APPLE, 6);
    StockItem milk = stockItemsDB.get(MILK.getValue());
    purchasedItems.add(milk);
    Double baseTotalPrice = 1.84;
    LocalDate dateToday = LocalDate.now().plusDays(5);

    Double discountedPrice = itemDiscount.twoSoupGetOneLoafBreadHalfOff(purchasedItems, baseTotalPrice, dateToday);

    assertEquals(baseTotalPrice, discountedPrice);
  }

  @Test
  void apples_purchased_today() {
    addItemsToPurchasedItems(APPLE, 3);
    LocalDate dateToday = LocalDate.now();

    Double baseTotalPrice = .30;

    Double discountedPrice = itemDiscount.applyAppleTenPercentDiscount(purchasedItems, baseTotalPrice, dateToday);
    Double expectedDiscountedPrice = .30;

    assertEquals(expectedDiscountedPrice, discountedPrice);
  }

  @Test
  void apples_purchased_threeDaysFromToday() {
    addItemsToPurchasedItems(APPLE, 3);
    LocalDate dateThreeDaysFromToday = LocalDate.now().plusDays(3);

    Double baseTotalPrice = .30;

    Double discountedPrice = itemDiscount.applyAppleTenPercentDiscount(purchasedItems, baseTotalPrice, dateThreeDaysFromToday);
    Double expectedDiscountedPrice = .27;

    assertEquals(expectedDiscountedPrice, discountedPrice);
  }

  private void addItemsToPurchasedItems(StockItem.ItemName itemName, Integer howManyToAdd) {
    StockItem item = stockItemsDB.get(itemName.getValue());
    item.setQuantityPurchased(1);
    for (int i = 0; i < howManyToAdd; i++) {
      purchasedItems.add(item);
    }
  }

}
