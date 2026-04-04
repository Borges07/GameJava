package controller;

import model.*;
import service.TerminalViewService;
import service.impl.BaseServiceControllerImpl;

public class ControllerGame {
    private final BaseServiceControllerImpl baseServiceController;
    private final CommandList currCommandList = new CommandList();
    private final Mainplayer player;
    private final TerminalViewService view;
    private final ScenarioService scenarioService;

    public ControllerGame(Mainplayer player, ScenarioService scenarioService, TerminalViewService view, BaseServiceControllerImpl baseServiceController) {
        this.player = player;
        this.scenarioService = scenarioService;
        this.view = view;
        this.baseServiceController = baseServiceController;
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
        scenarioService.nextScenario();
    }

    private void backScenarioController() {
        scenarioService.backScenario();
    }

    private void lokScenarioController() {
        view.displayMenssage("\n" + scenarioService.currenteScenario().getDescription());

    }

    private void displayInventory() {
        player.showInventory();
    }

    private void functionLeft() {
        scenarioService.leftCommand();
    }

    private void functionRight() {
        scenarioService.rightCommand();
    }

    private void lookObjectsScenario() {
        scenarioService.DisplayObjects();
    }

    public void getObjects(String nameObject) {
        baseServiceController.getObjectsService(nameObject);
    }

}
