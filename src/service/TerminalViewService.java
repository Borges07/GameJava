package service;

import model.ObjectItem;
import model.Scenario;

import java.util.List;

public interface TerminalViewService {

     void displayMenssage (String menssage);

     void displayImage (Scenario scenario) throws InterruptedException;

     void displayObjectImages (Scenario scenario);

     void showInventory (String namePlayer, List<ObjectItem> inventoryPlayer);

     void startPhaseView ();
}

