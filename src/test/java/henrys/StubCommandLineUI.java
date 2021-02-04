package henrys;

public class StubCommandLineUI implements RegisterUI {

  private boolean showedStartMessage;

  public void displayStartMessage() {
    showedStartMessage = true;
  }

  public boolean showedStartMessage(){
    return showedStartMessage;
  }
}
