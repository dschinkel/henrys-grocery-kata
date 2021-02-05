package henrys;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
  @Test
  void calculates_totalPrice_purchasedItems_soup() {
    Calculator calculator = new Calculator();
    StockItem soup = new StockItem();
    soup.setQuantityPurchased(1);
    ArrayList<StockItem> purchasedItems = new ArrayList<StockItem>();
    purchasedItems.add(soup);
    Double expectedTotalPrice = .65;

    Double total = calculator.calculateTotalPriceForItems(purchasedItems);

    assertEquals(expectedTotalPrice, total);
  }
}