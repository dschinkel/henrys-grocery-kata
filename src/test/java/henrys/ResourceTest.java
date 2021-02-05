package henrys;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ResourceTest {
  @Test
  void startRegister_displaysStartMessage() {
    StubCommandLineUI ui = new StubCommandLineUI();
    StockItemRepository repository = new StockItemRepository();
    Register register = new Register(ui, repository);

    register.start();

    assertTrue(ui.showedStartMessage());
  }

  @Test
  void startRegister_displaysList_Items_forSelection() {
    StubCommandLineUI ui = new StubCommandLineUI();
    StockItemRepository repository = new StockItemRepository();
    Register register = new Register(ui, repository);

    register.start();

    assertTrue(ui.showedItemsList());
  }
}