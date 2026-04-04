package service.impl;

import model.ObjectItem;
import model.Scenario;
import service.TerminalViewService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class TerminalViewServiceImpl implements TerminalViewService {

    @Override
    public void displayMenssage(String message) {
        System.out.println(message);
    }

    @Override
    public void displayImage(Scenario scenario) throws InterruptedException {
        String path = scenario.getPath();
        File file = new File(path);

        if (!file.exists()) {
            System.out.println("The file " + path + " does not exist.");
            return;
        }

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                System.out.println(line);
                Thread.sleep(80);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + path);
        }
    }

    @Override
    public void displayObjectImages(Scenario scenario) {
        if (scenario == null || scenario.getObjectScenarioList() == null || scenario.getObjectScenarioList().isEmpty()) {
            System.out.println("No objects to display in this scenario.");
            return;
        }

        for (ObjectItem obj : scenario.getObjectScenarioList()) {
            String path = obj.getPath();
            File file = new File(path);

            if (!file.exists()) {
                System.out.println("Image file for object '" + obj.getNameObject() + "' not found: " + path);
                continue;
            }

            try (Scanner reader = new Scanner(file)) {
                while (reader.hasNextLine()) {
                    System.out.println(reader.nextLine());
                    Thread.sleep(80);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + path);
            } catch (InterruptedException e) {
                System.out.println("Error displaying image for object '" + obj.getNameObject() + "': " + e.getMessage());
            }
        }
    }

    @Override
    public void showInventory(String namePlayer, List<ObjectItem> inventoryPlayer) {
        if (!inventoryPlayer.isEmpty()) {
            displayMenssage("Inventory of: " + namePlayer);
            for (ObjectItem objectItemPer: inventoryPlayer) {
                displayMenssage("- " + objectItemPer.getNameObject() + "( "
                        + objectItemPer.getDescription() +
                        " )");
            }
        } else {
            displayMenssage("Player nventory " + namePlayer + "\n Empty inventory");
        }
    }

    @Override
    public void startPhaseView() {
        displayMenssage("\n<===============> Welcome to the game! <===================>");
        displayMenssage("<=============>COMMANDS TO USE IN THE GAME<==============>");
    }
}
