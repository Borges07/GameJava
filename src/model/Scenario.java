package model;

import java.util.ArrayList;
import java.util.List;

public class Scenario {
    private String description;
    private Integer scenarioId;
    private Boolean isCurrent;
    private String path;
    private List<ObjectItem> objectScenarioList;

    public Scenario(String description, Integer scenarioId, Boolean isCurrent, String path, List<ObjectItem> objScenario) {
        this.description = description;
        this.scenarioId = scenarioId;
        this.isCurrent = isCurrent != null ? isCurrent : false;
        this.path = path;
        this.objectScenarioList = objScenario != null ? objScenario : new ArrayList<>();
    }
    

    public Scenario() {

    }

    public Scenario addObjectInScenario(List<ObjectItem> objAddScenario) {
        objectScenarioList.addAll(objAddScenario);
        return new Scenario(description, scenarioId, isCurrent, path, objAddScenario);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(Integer scenarioId) {
        this.scenarioId = scenarioId;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setActual(boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<ObjectItem> getObjectScenarioList() {
        if (objectScenarioList == null) {
            objectScenarioList = new ArrayList<>();
        }
        return new ArrayList<>(objectScenarioList);
    }
    

    public void setObjectScenarioList(List<ObjectItem> objectScenarioList) {
        this.objectScenarioList = objectScenarioList;
    }

}
