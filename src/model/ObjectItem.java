package model;

public class ObjectItem {
    private String nameObject;
    private String description;
    private String path;
    private Integer scenarioId;

    public ObjectItem(String nameObject, String description, Integer scenarioId, String path) {
        this.nameObject = nameObject;
        this.description = description;
        this.scenarioId = scenarioId;
        this.path = path;
    }

    public ObjectItem() {

    }

    public String getNameObject() {
        return nameObject;
    }

    public void setNameObject(String nameObject) {
        this.nameObject = nameObject;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ObjectItem [nameObject=" + nameObject + ", description=" + description + ", path=" + path
                + ", scenarioId=" + scenarioId + "]";
    }

    

}
