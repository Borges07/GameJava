package model;

import java.util.ArrayList;
import view.TerminalView;


public class Mainplayer extends Player{

    public Mainplayer(String namePlayer, ArrayList<ObjectItem> inventory, TerminalView display) {
        super(namePlayer, inventory, display);

    }

    public Mainplayer(String namePlayer) {
        super(namePlayer);

    }

    @Override
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

    @Override
    public void showInventory() {
        if (!inventory.isEmpty()) {
            display.displayMenssage("\nPlayer " + getNamePlayer() + " Inventory");
            for (ObjectItem objectItemPer : inventory) {
                display.displayMenssage("- " + objectItemPer.getNameObject() + "( "
                        + objectItemPer.getDescription() +
                        " )");

            }
        } else {
            display.displayMenssage("Player nventory " + getNamePlayer() + "\n Empty inventory");
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
