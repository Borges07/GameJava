package model;

import java.util.ArrayList;
import view.TerminalView;

public class Mainplayer {
    private String namePlayer;
    private ArrayList<ObjectItem> inventory = new ArrayList<>();
    private TerminalView dispay = new TerminalView();

    public Mainplayer(String namePlayer) {
        this.namePlayer = namePlayer;
        this.inventory = new ArrayList<>();
        this.dispay = new TerminalView();
    }
    public Mainplayer () {

    }

    public void addItem(ObjectItem object) {
        if (!inventory.contains(object)) {
            inventory.add(object);
            System.out.println("Added to inventory: " + object.getNameObject());
        } else {
            System.out.println("Item already in inventory: " + object.getNameObject());
        }
    }
    

    public void removeItem(ObjectItem removeObjectItem) {
        inventory.remove(removeObjectItem);
    }

    public void showInventory() {
        if (!inventory.isEmpty()) {
            dispay.displayMenssage("\nPlayer " + getNamePlayer() + " Inventory");
            for (ObjectItem objectItemPer : inventory) {
                dispay.displayMenssage("- " + objectItemPer.getNameObject() + "( "
                        + objectItemPer.getDescription() +
                        " )");

            }
        } else {
            dispay.displayMenssage("Player nventory " + getNamePlayer() + "\n Empty inventory");
        }
    }

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

}
