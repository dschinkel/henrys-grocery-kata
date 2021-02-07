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
    StockItem soup = stockItemsDB.get(SOUP.getValue());
    soup.setQuantityPurchased(1);
    purchasedItems.add(soup);
    purchasedItems.add(soup);
    purchasedItems.add(soup);
    StockItem bread = stockItemsDB.get(BREAD.getValue());
    bread.setQuantityPurchased(1);
    purchasedItems.add(bread);
    purchasedItems.add(bread);
    Double baseTotalPrice = 3.55;
    LocalDate dateTwoDaysAgo = LocalDate.now().minusDays(2);

    Double discountedPrice = itemDiscount.twoSoupGetOneLoafBreadHalfOff(purchasedItems, baseTotalPrice, dateTwoDaysAgo);

    assertEquals(baseTotalPrice, discountedPrice);
  }

  @Test
  void threeSoup_twoLoavesBread_purchasedDateToday() {
    StockItem soup = stockItemsDB.get(SOUP.getValue());
    soup.setQuantityPurchased(1);
    purchasedItems.add(soup);
    purchasedItems.add(soup);
    purchasedItems.add(soup);
    StockItem bread = stockItemsDB.get(BREAD.getValue());
    bread.setQuantityPurchased(1);
    purchasedItems.add(bread);
    purchasedItems.add(bread);
    Double baseTotalPrice = 3.55;
    LocalDate dateTwoDaysAgo = LocalDate.now();
    Double expectdDiscountedPrice = 3.15;

    Double discountedPrice = itemDiscount.twoSoupGetOneLoafBreadHalfOff(purchasedItems, baseTotalPrice, dateTwoDaysAgo);

    assertEquals(expectdDiscountedPrice, discountedPrice);
  }

  @Test
  void sixApples_and_oneBottleOfMilk_purchasedThreeDaysFromToday() {
    StockItem apple = stockItemsDB.get(APPLE.getValue());
    purchasedItems.add(apple);
    purchasedItems.add(apple);
    purchasedItems.add(apple);
    purchasedItems.add(apple);
    purchasedItems.add(apple);
    purchasedItems.add(apple);
    StockItem milk = stockItemsDB.get(MILK.getValue());
    purchasedItems.add(milk);
    Double baseTotalPrice = 1.84;
    LocalDate dateToday = LocalDate.now().plusDays(5);

    Double discountedPrice = itemDiscount.twoSoupGetOneLoafBreadHalfOff(purchasedItems, baseTotalPrice, dateToday);

    assertEquals(baseTotalPrice, discountedPrice);
  }

  @Test
  void apples_purchased_today() {
    StockItem apple = stockItemsDB.get(StockItem.ItemName.APPLE.getValue());
    apple.setQuantityPurchased(1);
    purchasedItems.add(apple);
    purchasedItems.add(apple);
    purchasedItems.add(apple);
    LocalDate dateToday = LocalDate.now();

    Double baseTotalPrice = .30;

    Double discountedPrice = itemDiscount.applyAppleTenPercentDiscount(purchasedItems, baseTotalPrice, dateToday);
    Double expectedDiscountedPrice = .30;

    assertEquals(expectedDiscountedPrice, discountedPrice);
  }

  @Test
  void apples_purchased_threeDaysFromToday() {
    StockItem apple = stockItemsDB.get(StockItem.ItemName.APPLE.getValue());
    apple.setQuantityPurchased(1);
    purchasedItems.add(apple);
    purchasedItems.add(apple);
    purchasedItems.add(apple);
    LocalDate dateThreeDaysFromToday = LocalDate.now().plusDays(3);

    Double baseTotalPrice = .30;

    Double discountedPrice = itemDiscount.applyAppleTenPercentDiscount(purchasedItems, baseTotalPrice, dateThreeDaysFromToday);
    Double expectedDiscountedPrice = .27;

    assertEquals(expectedDiscountedPrice, discountedPrice);
  }

}
