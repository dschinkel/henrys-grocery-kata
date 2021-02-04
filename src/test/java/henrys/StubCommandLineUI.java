package henrys;

public class StubCommandLineUI implements RegisterUI {

  private boolean showedStartMesage;

  @Override
  public void displayStartMessage() {
    showedStartMesage = true;
  }

  public boolean showedStartMessage(){
    return showedStartMesage;
  }
}
