package henrys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegisterTest {
  private StubCommandLineUI ui;
  private StockItemRepository repository;
  private Register register;
  private RegisterCalculator registerCalculator;

  @BeforeEach
  void setUp() {
    ui = new StubCommandLineUI();
    repository = new StockItemRepository();
    registerCalculator = new RegisterCalculator();
    register = new Register(ui, repository, registerCalculator);
  }

  @Test
  void startRegister_displaysStartMessage() {
    register.start();
    assertTrue(ui.showedStartMessage());
  }

  @Test
  void startRegister_displaysList_inventoryItems_forSelection() {
    register.start();
    assertTrue(ui.showedItemsList());
  }

  @Test
  void startRegister_displays_totalPrice() {
    register.start();
    assertTrue(ui.showedTotalPrice());
  }

  @Test
  void calculates_totalPrice_oneOfEachItem_noDiscount() {
    Map<Integer, Integer> itemsWithQuantity = new HashMap<>();
    itemsWithQuantity.put(0, 1);

    String expectedPrice = "0.65";
    String totalPrice = register.calculateTotalPrice(itemsWithQuantity);

    assertEquals(expectedPrice, totalPrice);
  }
}