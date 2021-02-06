package henrys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemDiscountTest {

  ArrayList<StockItem> stockItemsDB;
  RegisterCalculator registerCalculator;

  @BeforeEach
  void setUp() {
    stockItemsDB = new StockItemRepository().findAll();
    registerCalculator = new RegisterCalculator();
  }

  @Test
  void twoSoup_getOne_loafOfBread_halfOff() {
    ItemDiscount itemDiscount = new ItemDiscount();
/*    StockItem soup = stockItemsDB.get(StockItem.ItemName.SOUP.getValue());
    soup.setQuantityPurchased(2);
    StockItem bread = stockItemsDB.get(StockItem.ItemName.BREAD.getValue());
    bread.setQuantityPurchased(2);*/
    ArrayList<StockItem> purchasedItems = new ArrayList<>();
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
}
