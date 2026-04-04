package model;

import service.TerminalViewService;

import java.util.ArrayList;
import java.util.List;

public abstract class Player implements InventoryHandler{

    protected String namePlayer;

    protected TerminalViewService display;

    protected List <ObjectItem> inventory = new ArrayList<>();

    protected Player(String namePlayer, List <ObjectItem> inventory, TerminalViewService display) {
        this.namePlayer = namePlayer;
        this.display = display;
        this.inventory = inventory;
    }

    public Player(String namePlayer) {
        this(namePlayer, new ArrayList<>(), null);
    }

    public Player() {}

    @Override
    public void addItem (ObjectItem object) {
        if (!inventory.contains(object)) {
            inventory.add(object);
            System.out.println("Added to inventory: " + object.getNameObject());
        } else {
            System.out.println("Item already in inventory: " + object.getNameObject());
        }
    }

    @Override
    public void removeItem(ObjectItem object) {

    }

    @Override
    public void showInventory() {
        for (ObjectItem objectItemPer: inventory) {
            display.displayMenssage(objectItemPer.getNameObject());
        }
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public List<ObjectItem> getInventory() {
        return inventory;
    }

    public void setInventory(List<ObjectItem> inventory) {
        this.inventory = inventory;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public TerminalViewService getDisplay() {
        return display;
    }

    public void setDisplay(TerminalViewService display) {
        this.display = display;
    }

}
