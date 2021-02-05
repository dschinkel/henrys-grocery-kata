package henrys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandLineUITest {
  private ByteArrayOutputStream output;
  private StubInputStream input;
  CommandLineUI ui;

  @BeforeEach
  void setUp() {
    output = new ByteArrayOutputStream();
    input = new StubInputStream();
    ui = new CommandLineUI(input, output);
  }

  @Test
  void displayStartMessage() {
    ui.displayStartMessage();
    assertTrue(output.toString().contains("READY FOR CHECKOUT!\n"));
  }

  @Test
  void display_items_toSelectFrom() {
    Map<Integer, String> stockItems = getStockItems();
    String finalMessage = "Items in Stock: 0:soup 1:bread 2:milk 3:apples \n";

    ui.displayItemsForSelection(stockItems);

    assertEquals(finalMessage, output.toString());
  }

  @Test
  void promptFor_inventoryItem_Selection() {
    String promptForInventoryItem = "Please Specify an Item by Number:\n";
    String selectedItem = "0";
    input.setInputStream(selectedItem);

    String userInput = ui.promptForInventoryItemSelection();

    assertEquals(selectedItem, userInput);
    assertEquals(promptForInventoryItem, output.toString());
  }

  @Test
  void promptFor_inventoryItem_Quantity_Message() {
    String promptForItemQuantity = "Please Specify a Qty (e.g. 1, 2, 3, etc.) for this item:\n";
    ui.promptForInventoryItemQuantityMessage();
    assertEquals(promptForItemQuantity, output.toString());
  }

  @Test
  void promptFor_inventoryItem_Quantity() {
    String selectedItemQty = "1";
    input.setInputStream(selectedItemQty);

    String userInput = ui.promptForInventoryItemQuantity();

    assertEquals(selectedItemQty, userInput);
  }


  private Map<Integer, String> getStockItems(){
    Map<Integer, String> stockItems = new HashMap<Integer, String>();
    stockItems.put(0, "soup");
    stockItems.put(1, "bread");
    stockItems.put(2, "milk");
    stockItems.put(3, "apples");

    return stockItems;
  }

}
