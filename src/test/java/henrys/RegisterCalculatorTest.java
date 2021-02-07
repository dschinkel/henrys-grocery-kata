package henrys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static henrys.StockItem.ItemName.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterCalculatorTest {
  RegisterCalculator registerCalculator;
  ArrayList<StockItem> purchasedItems;
  ArrayList<StockItem> stockItemsDB;

  @BeforeEach
  void setUp() {
    registerCalculator = new RegisterCalculator();
    purchasedItems = new ArrayList<StockItem>();
    stockItemsDB = new StockItemRepository().findAll();
  }

  @Test
  void calculates_totalPrice_purchasedItems_soup() {
    StockItem soup = createStockItem(SOUP.getValue(),1);
    purchasedItems.add(soup);
    Double expectedTotalPrice = .65;
    LocalDate dateToday = LocalDate.now();

    Double total = registerCalculator.tallyTotalForPurchasedStockItems(purchasedItems, dateToday);

    assertEquals(expectedTotalPrice, total);
  }

  @Test
  void calculates_totalPrice_purchasedItems_bread() {
    StockItem bread = createStockItem(BREAD.getValue(),1);
    purchasedItems.add(bread);
    Double expectedTotalPrice = .80;
    LocalDate dateToday = LocalDate.now();

    Double total = registerCalculator.tallyTotalForPurchasedStockItems(purchasedItems, dateToday);

    assertEquals(expectedTotalPrice, total);
  }

  @Test
  void calculates_totalPrice_purchasedItems_oneMilk() {
    StockItem milk = createStockItem(MILK.getValue(),1);
    purchasedItems.add(milk);
    Double expectedTotalPrice = 1.30;
    LocalDate dateToday = LocalDate.now();

    Double total = registerCalculator.tallyTotalForPurchasedStockItems(purchasedItems, dateToday);

    assertEquals(expectedTotalPrice, total);
  }

  @Test
  void calculates_totalPrice_purchasedItems_oneApple_discounted() {
    StockItem apples = createStockItem(APPLE.getValue(),1);
    purchasedItems.add(apples);
    Double expectedTotalPrice = .09;
    LocalDate dateToday = LocalDate.now().plusDays(3);

    Double total = registerCalculator.tallyTotalForPurchasedStockItems(purchasedItems, dateToday);

    assertEquals(expectedTotalPrice, total);
  }

  @Test
  void calculates_totalPrice_multiplePurchasedItems() {
    StockItem soup = createStockItem(SOUP.getValue(),1);
    purchasedItems.add(soup);
    StockItem milk = createStockItem(MILK.getValue(),1);
    purchasedItems.add(milk);
    Double expectedTotalPrice = 1.95;
    LocalDate dateToday = LocalDate.now();

    Double total = registerCalculator.tallyTotalForPurchasedStockItems(purchasedItems, dateToday);

    assertEquals(expectedTotalPrice, total);
  }

  @Test
  void calculates_totalPrice_withDiscount_forApples() {
    StockItem apple = createStockItem(APPLE.getValue(),1);
    purchasedItems.add(apple);
    purchasedItems.add(apple);
    purchasedItems.add(apple);
    Double expectedTotalPrice = .27;
    LocalDate dateToday = LocalDate.now().plusDays(3);

    Double total = registerCalculator.tallyTotalForPurchasedStockItems(purchasedItems, dateToday);
    assertEquals(expectedTotalPrice, total);
  }

  @Test
  void sixApples_and_oneBottleOfMilk_purchasedToday() {
    StockItem apple = createStockItem(APPLE.getValue(),1);
    purchasedItems.add(apple);
    purchasedItems.add(apple);
    purchasedItems.add(apple);
    purchasedItems.add(apple);
    purchasedItems.add(apple);
    purchasedItems.add(apple);
    StockItem milk = createStockItem(MILK.getValue(),1);
    purchasedItems.add(milk);
    Double baseTotalPrice = 1.90;
    LocalDate dateToday = LocalDate.now();

    Double total = registerCalculator.tallyTotalForPurchasedStockItems(purchasedItems, dateToday);

    assertEquals(baseTotalPrice, total);
  }

  @Test
  void sixApples_and_oneBottleOfMilk_purchasedFiveDaysFromToday() {
    StockItem apple = createStockItem(APPLE.getValue(),1);
    purchasedItems.add(apple);
    purchasedItems.add(apple);
    purchasedItems.add(apple);
    purchasedItems.add(apple);
    purchasedItems.add(apple);
    purchasedItems.add(apple);
    StockItem milk = createStockItem(MILK.getValue(),1);
    purchasedItems.add(milk);
    Double baseTotalPrice = 1.84;
    LocalDate dateToday = LocalDate.now().plusDays(5);

    Double total = registerCalculator.tallyTotalForPurchasedStockItems(purchasedItems, dateToday);

    assertEquals(baseTotalPrice, total);
  }

  private StockItem createStockItem(Integer itemId, Integer quantity) {
    StockItem stockItem = new StockItem();
    stockItem.setItemId(itemId);
    stockItem.setQuantityPurchased(quantity);
    stockItem.setPricePerUnit(stockItemsDB.get(itemId).getItemPricePerUnit());
    return stockItem;
  }
}
