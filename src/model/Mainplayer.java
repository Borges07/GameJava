package model;

import service.TerminalViewService;

import java.util.ArrayList;
import java.util.List;


public class Mainplayer extends Player {

    public Mainplayer(String namePlayer, List<ObjectItem> inventory, TerminalViewService display) {
        super(namePlayer, inventory, display);

    }

    public Mainplayer() {
    }

    public Mainplayer(String namePlayer) {
        super(namePlayer);
        this.display = getDisplay();
    }

}
