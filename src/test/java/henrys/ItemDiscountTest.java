package henrys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

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
    StockItem soup = stockItemsDB.get(StockItem.ItemName.SOUP.getValue());
    soup.setQuantityPurchased(1);
    purchasedItems.add(soup);
    purchasedItems.add(soup);
    purchasedItems.add(soup);
    StockItem bread = stockItemsDB.get(StockItem.ItemName.BREAD.getValue());
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
    StockItem soup = stockItemsDB.get(StockItem.ItemName.SOUP.getValue());
    soup.setQuantityPurchased(1);
    purchasedItems.add(soup);
    purchasedItems.add(soup);
    purchasedItems.add(soup);
    StockItem bread = stockItemsDB.get(StockItem.ItemName.BREAD.getValue());
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
  void apples_tenPercent_off() {
    StockItem apple = stockItemsDB.get(StockItem.ItemName.APPLE.getValue());
    apple.setQuantityPurchased(1);
    purchasedItems.add(apple);
    purchasedItems.add(apple);
    purchasedItems.add(apple);

    Double baseTotalPrice = .30;

    Double discountedPrice = itemDiscount.applyAppleTenPercentDiscount(purchasedItems, baseTotalPrice);
    Double expectedDiscountedPrice = .27;

    assertEquals(expectedDiscountedPrice, discountedPrice);
  }

}
