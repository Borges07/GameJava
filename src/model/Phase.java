package model;

import service.TerminalViewService;
import java.util.ArrayList;

public abstract class Phase {
    private boolean status = false;
    private ArrayList<ObjectItem> listOfPhase = new ArrayList<>();
    private Mainplayer mainplayer;
    private ScenarioService scenarioService;
    private TerminalViewService display;

    public Phase(boolean status, ArrayList<ObjectItem> listOfPhase, Mainplayer mainplayer, ScenarioService scenarioService,
                 TerminalViewService display) {
        this.status = status;
        this.listOfPhase = listOfPhase;
        this.mainplayer = mainplayer;
        this.scenarioService = scenarioService;
        this.display = display;
    }

    public Phase(boolean status) {
        this.status = status;
    }

    public Phase() {
    }

    protected Boolean start () {}

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<ObjectItem> getListOfPhase() {
        return listOfPhase;
    }

    public void setListOfPhase(ArrayList<ObjectItem> listOfPhase) {
        this.listOfPhase = listOfPhase;
    }

    public Mainplayer getMainplayer() {
        return mainplayer;
    }

    public void setMainplayer(Mainplayer mainplayer) {
        this.mainplayer = mainplayer;
    }

    public TerminalViewService getDisplay() {
        return display;
    }

    public void setDisplay(TerminalViewService display) {
        this.display = display;
    }

    public ScenarioService getScenarioCriation() {
        return scenarioService;
    }

    public void setScenarioCriation(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }
}
