package service.impl;

import model.Mainplayer;
import model.ObjectItem;
import model.ScenarioService;
import service.BaseServiceController;
import service.TerminalViewService;

public class BaseServiceControllerImpl implements BaseServiceController {
    private final ScenarioService scenarioService;
    private final TerminalViewService terminalViewService;
    private final Mainplayer player = new Mainplayer();

    public BaseServiceControllerImpl(ScenarioService scenarioService, TerminalViewService terminalViewService) {
        this.scenarioService = scenarioService;
        this.terminalViewService = terminalViewService;
    }

    @Override
    public void getObjectsService (String nameObject) {
        ObjectItem objectFound = scenarioService.findObjectById(scenarioService.currenteScenario().getScenarioId(), nameObject);
        if (objectFound != null && objectFound.getScenarioId().equals(scenarioService.currenteScenario().getScenarioId())) {
            player.addItem(objectFound);
            scenarioService.removeObject(scenarioService.currenteScenario().getScenarioId(), objectFound);
            terminalViewService.displayMenssage("You got: " + objectFound.getNameObject());
        } else {
            terminalViewService.displayMenssage("Object not found: " + nameObject);
        }
    }
}
