package henrys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
  void twoSoup_getOne_loafOfBread_halfOff() {
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

    Double discountedPrice = itemDiscount.twoSoupGetOneLoafBreadHalfOff(purchasedItems, baseTotalPrice);
    Double expectdDiscountedPrice = 3.15;

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
