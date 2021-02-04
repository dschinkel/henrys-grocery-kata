package henrys;

public class Register {
  private final RegisterUI ui;

  Register(RegisterUI ui){
    this.ui = ui;
  }

  public void start() {
    ui.displayStartMessage();
  }
}
