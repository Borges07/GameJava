package model;

import service.TerminalViewService;
import service.impl.TerminalViewServiceImpl;

import java.util.ArrayList;


public class Mainplayer extends Player {

    public Mainplayer(String namePlayer, ArrayList<ObjectItem> inventory, TerminalViewService display) {
        super(namePlayer, inventory, display);

    }

    public Mainplayer(String namePlayer) {
        super(namePlayer);
        this.display = getDisplay();
        this.inventory = getInventory();
    }

    public void removeItem(ObjectItem removeObjectItem) {
        inventory.remove(removeObjectItem);
    }

    public void showInventory() {
        display.showInventory(this.namePlayer, this.inventory);
    }

}
