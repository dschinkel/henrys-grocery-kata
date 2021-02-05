package henrys;

public class Register {
  private final RegisterUI ui;
  private final StockItemRepository stockItemRepository;

  Register(RegisterUI ui, StockItemRepository stockItemRepository){
    this.ui = ui;
    this.stockItemRepository = stockItemRepository;
  }

  public void start() {
    ui.displayStartMessage();
    ui.displayItemsForSelection(this.stockItemRepository.findAll());
    ui.promptForInventoryItemSelection();
  }
}
