package model;

import view.TerminalView;

import java.util.ArrayList;

public class Player {

    protected String namePlayer;

    protected ArrayList<ObjectItem> inventory = new ArrayList<>();

    protected TerminalView display = new TerminalView();

    protected Player(String namePlayer, ArrayList<ObjectItem> inventory, TerminalView display) {
        this.namePlayer = namePlayer;
        this.inventory = inventory;
        this.display = display;
    }

    public Player(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    protected void addItem (ObjectItem object) {}

    protected void removeIten () {}

    protected void showInventory () {}



    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public ArrayList<ObjectItem> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<ObjectItem> inventory) {
        this.inventory = inventory;
    }

    public TerminalView getDisplay() {
        return display;
    }

    public void setDisplay(TerminalView display) {
        this.display = display;
    }




}
