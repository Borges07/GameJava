package model;

import service.TerminalViewService;

import java.util.ArrayList;

public class Player {

    protected String namePlayer;

    protected ArrayList<ObjectItem> inventory = new ArrayList<>();

    protected TerminalViewService display;

    protected Player(String namePlayer, ArrayList<ObjectItem> inventory, TerminalViewService display) {
        this.namePlayer = namePlayer;
        this.inventory = inventory;
        this.display = display;
    }

    public Player(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    protected void addItem (ObjectItem object) {
        if (!inventory.contains(object)) {
            inventory.add(object);
            System.out.println("Added to inventory: " + object.getNameObject());
        } else {
            System.out.println("Item already in inventory: " + object.getNameObject());
        }
    }

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

    public TerminalViewService getDisplay() {
        return display;
    }

    public void setDisplay(TerminalViewService display) {
        this.display = display;
    }

}
