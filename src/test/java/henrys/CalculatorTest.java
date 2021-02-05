package henrys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
  Calculator calculator;
  ArrayList<StockItem> purchasedItems;

  @BeforeEach
  void setUp() {
    calculator = new Calculator();
    purchasedItems = new ArrayList<StockItem>();
  }

  @Test
  void calculates_totalPrice_purchasedItems_soup() {
    StockItem soup = createStockItem(0,1);
    purchasedItems.add(soup);
    Double expectedTotalPrice = .65;

    Double total = calculator.calculateTotalPriceForItems(purchasedItems);

    assertEquals(expectedTotalPrice, total);
  }

  @Test
  void calculates_totalPrice_purchasedItems_bread() {
    StockItem bread = createStockItem(1,1);
    purchasedItems.add(bread);
    Double expectedTotalPrice = .80;

    Double total = calculator.calculateTotalPriceForItems(purchasedItems);

    assertEquals(expectedTotalPrice, total);
  }

  private StockItem createStockItem(Integer itemId, Integer quantity) {
    StockItem stockItem = new StockItem();
    stockItem.setItemId(itemId);
    stockItem.setQuantityPurchased(quantity);
    return stockItem;
  }
}
