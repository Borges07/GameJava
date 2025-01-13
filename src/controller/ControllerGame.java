package controller;

import model.CommandList;
import model.Mainplayer;
import model.ObjectItem;
import model.Phases;
import model.ScenarioCriation;
import view.TerminalView;

public class ControllerGame {
    private CommandList currCommandList = new CommandList();
    private Mainplayer player;
    private TerminalView view;
    private ScenarioCriation theScearios;

    public ControllerGame(Mainplayer player, ScenarioCriation theScearios, Phases phasesGame) {
        this.player = player;
        this.theScearios = theScearios;
        this.view = new TerminalView();
    }

    public void useCommand(String commandInput) {
        String[] commandIs = commandInput.toLowerCase().trim().split(" ");

        if (commandIs.length == 0 || commandIs[0].isBlank()) {
            view.displayMenssage("Enter a valid command.");
            return;
        }

        String fullCommand;
        try {
            fullCommand = currCommandList.findfullCommand(commandIs);
        } catch (IllegalArgumentException e) {
            view.displayMenssage(e.getMessage());
            return;
        }

        switch (fullCommand.toLowerCase()) {
            case "go to":
                avancedScenario();
                break;
            case "go left":
                functionLeft();
                break;
            case "go right":
                functionRight();
                break;
            case "go back":
                backScenarioController();
                break;
            case "look scenario":
                lokScenarioController();
                break;
            case "look inventory":
                displayInventory();
                break;
            case "look objects":
                lookObjectsScenario();
                break;
            case "get":
                if (commandIs.length < 2) {
                    view.displayMenssage("Specify an object name for the 'get' command.");
                } else {
                    getObjects(commandIs[1]);
                }
                break;
            default:
                view.displayMenssage("Unknown command: " + fullCommand);
                break;
        }
    }

    private void avancedScenario() {
        theScearios.nextScenario();
    }

    private void backScenarioController() {
        theScearios.backScenario();
    }

    private void lokScenarioController() {
        view.displayMenssage("\n" + theScearios.currenteScenario().getDescription());

    }

    private void displayInventory() {
        player.showInventory();
    }

    private void functionLeft() {
        theScearios.leftCommand();
    }

    private void functionRight() {
        theScearios.rightCommand();
    }

    private void lookObjectsScenario() {
        theScearios.DisplayObjects();
    }

    public void getObjects(String nameObject) {
        ObjectItem objectFound = theScearios.findObjectById(theScearios.currenteScenario().getScenarioId(), nameObject);
        if (objectFound != null && objectFound.getScenarioId() == theScearios.currenteScenario().getScenarioId()) {
            player.addItem(objectFound);
            theScearios.removeObject(theScearios.currenteScenario().getScenarioId(), objectFound);
            view.displayMenssage("You got: " + objectFound.getNameObject());
        } else {
            view.displayMenssage("Object not found: " + nameObject);
        }
    }

}
