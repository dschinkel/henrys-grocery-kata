package henrys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static henrys.Utilities.formatDoubleToPrecisionOfTwo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandLineUITest {
  private ByteArrayOutputStream output;
  private StubInputStream input;
  CommandLineUI ui;
  ArrayList<StockItem> stockItemsDB;
  ArrayList<StockItem> purchasedItems = new ArrayList<>();

  @BeforeEach
  void setUp() {
    output = new ByteArrayOutputStream();
    input = new StubInputStream();
    ui = new CommandLineUI(input, output);
    stockItemsDB = new StockItemRepository().findAll();
  }

  @Test
  void displayStartMessage() {
    ui.displayStartMessage();
    assertTrue(output.toString().contains("READY FOR CHECKOUT!\n"));
  }

  @Test
  void prompt_for_date() {
    String promptForDate = "Please Specify a purchased date in a format of mm/dd/yyyy):\n";
    String date = "02\06\2021";
    input.setInputStream(date);

    String userInput = ui.promptforDate();

    assertEquals(date, userInput);
    assertEquals(promptForDate, output.toString());
  }

  @Test
  void display_items_toSelectFrom() {
    String finalMessage = "Items in Stock: 0:soup 1:bread 2:milk 3:apples \n";
    ui.displayItemsForSelection(stockItemsDB);
    assertEquals(finalMessage, output.toString());
  }

  @Test
  void promptFor_inventoryItem_selection() {
    String promptForInventoryItem = "Please Specify an Item by Number:\n" +
      "You selected: soup \n";
    String selectedItem = "0";
    ArrayList<StockItem> stockItemsDB = new StockItemRepository().findAll();

    ui.setStockItems(stockItemsDB);
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
    double total = formatDoubleToPrecisionOfTwo(.09);
    String finalMessage = String.format("Total Price is %s\n", total);

    ui.displayTotalPrice(total);

    assertEquals(finalMessage, output.toString());
  }
}
