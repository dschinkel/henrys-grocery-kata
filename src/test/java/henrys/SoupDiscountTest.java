package henrys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static henrys.Constants.ItemName.BREAD;
import static henrys.Constants.ItemName.SOUP;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SoupDiscountTest {

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
  void threeSoup_twoLoavesBread_getOneloafOfBread_halfOff_noDiscount_whenPurchasedPriorToYesterday() {
    addToPurchasedItems(SOUP, 3);
    addToPurchasedItems(BREAD, 2);
    double baseTotalPrice = 3.55;
    LocalDate dateTwoDaysAgo = LocalDate.now().minusDays(2);

    double nonDiscountedPrice = soupDiscount.twoSoupGetOneLoafBreadHalfOff(purchasedItems, baseTotalPrice, dateTwoDaysAgo);

    assertEquals(baseTotalPrice, nonDiscountedPrice);
  }

  @Test
  void threeSoup_twoLoavesBread_withDiscount_purchasedDateToday() {
    addToPurchasedItems(SOUP, 3);
    addToPurchasedItems(BREAD, 2);
    double baseTotalPrice = 3.55;
    double expectdDiscountedPrice = 3.15;
    LocalDate dateTwoDaysAgo = LocalDate.now();

    double discountedPrice = soupDiscount.twoSoupGetOneLoafBreadHalfOff(purchasedItems, baseTotalPrice, dateTwoDaysAgo);

    assertEquals(expectdDiscountedPrice, discountedPrice);
  }

  @Test
  void threeSoup_twoLoavesBread_withDiscount_purchasedYesterday() {
    addToPurchasedItems(SOUP, 3);
    addToPurchasedItems(BREAD, 2);
    double baseTotalPrice = 3.55;
    double expectdDiscountedPrice = 3.15;
    LocalDate dateTwoDaysAgo = LocalDate.now().minusDays(1);

    double discountedPrice = soupDiscount.twoSoupGetOneLoafBreadHalfOff(purchasedItems, baseTotalPrice, dateTwoDaysAgo);

    assertEquals(expectdDiscountedPrice, discountedPrice);
  }


  private void addToPurchasedItems(Constants.ItemName itemName, Integer howManyToAdd) {
    StockItem item = stockItemsDB.get(itemName.getValue());
    for (int i = 0; i < howManyToAdd; i++) {
      purchasedItems.add(item);
    }
  }
}
