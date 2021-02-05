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
  void promptFor_inventoryItem_selection() {
    String promptForInventoryItem = "Please Specify an Item by Number:\n";
    String selectedItem = "0";
    input.setInputStream(selectedItem);

    String userInput = ui.promptForInventoryItemSelection();

    assertEquals(selectedItem, userInput);
    assertEquals(promptForInventoryItem, output.toString());
  }

  @Test
  void promptFor_inventoryItem_quantity_message() {
    String promptForItemQuantity = "Please Specify a Qty (e.g. 1, 2, 3, etc.) for this item:\n";
    ui.promptForInventoryItemQuantityMessage();
    assertEquals(promptForItemQuantity, output.toString());
  }

  @Test
  void promptFor_inventoryItem_quantity() {
    String selectedItemQty = "1";
    input.setInputStream(selectedItemQty);

    String userInput = ui.promptForInventoryItemQuantity();

    assertEquals(selectedItemQty, userInput);
  }

  @Test
  void promptFor_done_message() {
    String promptForDone = "Press d for done or c to continue:\n";
    ui.promptForDoneMessage();
    assertEquals(promptForDone, output.toString());
  }

  @Test
  void promptFor_done() {
    String done = "d";
    input.setInputStream(done);
    String userInput = ui.promptforDone();
    assertEquals(userInput, done);
  }

  @Test
  void display_totalPrice() {
    String total = "1.00";
    String finalMessage = String.format("Total Price is: %s\n", total);

    ui.displayTotalPrice(total);

    assertEquals(finalMessage, output.toString());
  }

 /* @Test
  void stores_multiple_entered_inventoryItems() {
    String soup = "0";
    String soupQty = "1";
    String bread = "1";
    String breadQty = "2";
    Map<Integer, Integer> enteredItems = new HashMap<Integer, Integer>();
    enteredItems.put(0, 1);
    enteredItems.put(1, 2);

    input.setInputStream(soup);
//    ui.inputAllItems();
    input.setInputStream(soupQty);
//    ui.inputAllItems();
    input.setInputStream(bread);
//    ui.inputAllItems();
    input.setInputStream(breadQty);
//    ui.inputAllItems();
    input.setInputStream("d");
    Map<Integer, Integer> basketOfItems = ui.inputAllItems();

    assertEquals(enteredItems, basketOfItems);
  }*/

  private Map<Integer, String> getStockItems(){
    Map<Integer, String> stockItems = new HashMap<Integer, String>();
    stockItems.put(0, "soup");
    stockItems.put(1, "bread");
      stockItems.put(2, "milk");
    stockItems.put(3, "apples");

    return stockItems;
  }

}
