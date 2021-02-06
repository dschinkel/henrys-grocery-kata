package henrys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterCalculatorTest {
  RegisterCalculator registerCalculator;
  ArrayList<StockItem> purchasedItems;

  @BeforeEach
  void setUp() {
    registerCalculator = new RegisterCalculator();
    purchasedItems = new ArrayList<StockItem>();
  }

  @Test
  void calculates_totalPrice_purchasedItems_soup() {
    StockItem soup = createStockItem(0,1);
    purchasedItems.add(soup);
    Double expectedTotalPrice = .65;

    Double total = registerCalculator.tallyTotalForPurchasedStockItems(purchasedItems);

    assertEquals(expectedTotalPrice, total);
  }

  @Test
  void calculates_totalPrice_purchasedItems_bread() {
    StockItem bread = createStockItem(1,1);
    purchasedItems.add(bread);
    Double expectedTotalPrice = .80;

    Double total = registerCalculator.tallyTotalForPurchasedStockItems(purchasedItems);

    assertEquals(expectedTotalPrice, total);
  }

  @Test
  void calculates_totalPrice_purchasedItems_milk() {
    StockItem milk = createStockItem(2,1);
    purchasedItems.add(milk);
    Double expectedTotalPrice = 1.30;

    Double total = registerCalculator.tallyTotalForPurchasedStockItems(purchasedItems);

    assertEquals(expectedTotalPrice, total);
  }

  @Test
  void calculates_totalPrice_purchasedItems_apples() {
    StockItem apples = createStockItem(3,1);
    purchasedItems.add(apples);
    Double expectedTotalPrice = .10;

    Double total = registerCalculator.tallyTotalForPurchasedStockItems(purchasedItems);

    assertEquals(expectedTotalPrice, total);
  }

  @Test
  void calculates_totalPrice_multiplePurchasedItems() {
    StockItem soup = createStockItem(0,1);
    purchasedItems.add(soup);
    StockItem milk = createStockItem(2,1);
    purchasedItems.add(milk);
    Double expectedTotalPrice = 1.95;

    Double total = registerCalculator.tallyTotalForPurchasedStockItems(purchasedItems);

    assertEquals(expectedTotalPrice, total);
  }

  private StockItem createStockItem(Integer itemId, Integer quantity) {
    StockItem stockItem = new StockItem();
    stockItem.setItemId(itemId);
    stockItem.setQuantityPurchased(quantity);
    return stockItem;
  }
}