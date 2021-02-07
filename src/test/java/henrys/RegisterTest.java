package henrys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisterTest {
  private DummyCommandLineUI ui;
  private StockItemRepository repository;
  private Register register;
  private RegisterCalculator registerCalculator;

  @BeforeEach
  void setUp() {
    repository = new StockItemRepository();
    registerCalculator = new RegisterCalculator();
    register = new Register(ui, repository, registerCalculator);
  }

  @Test
  void calculates_totalPrice_oneOfEachItem_noDiscount() {
    Map<Integer, Integer> itemsWithQuantity = new HashMap<>();
    itemsWithQuantity.put(0, 1);
    LocalDate dateToday = LocalDate.now();

    double expectedPrice = 0.65;
    double totalPrice = register.calculateTotalPrice(itemsWithQuantity, dateToday);

    assertEquals(expectedPrice, totalPrice);
  }
}