package henrys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RegisterTest {
  private StubCommandLineUI ui;
  private StockItemRepository repository;
  private Register register;

  @BeforeEach
  void setUp() {
    ui = new StubCommandLineUI();
    repository = new StockItemRepository();
    register = new Register(ui, repository);
  }

  @Test
  void startRegister_displaysStartMessage() {
    register.start();
    assertTrue(ui.showedStartMessage());
  }

  @Test
  void startRegister_displaysList_InventoryItems_forSelection() {
    register.start();
    assertTrue(ui.showedItemsList());
  }

  @Test
  void startRegister_promptsFor_InventoryItemSelection() {
    register.start();
    assertTrue(ui.promptedForInventoryItemSelection());
  }
}