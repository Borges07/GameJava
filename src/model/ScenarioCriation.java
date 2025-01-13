package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import view.TerminalView;

public class ScenarioCriation {
    private final List<Scenario> listOfSceneObjects = new ArrayList<>();
    private TerminalView vv = new TerminalView();
    private Phases ph = new Phases(false);
    private Scanner sc = new Scanner(System.in);

    public ScenarioCriation(Mainplayer player) {
        this.ph = new Phases(false, player, this);
        startScenario();
    }

    private void startScenario() {

        List<ObjectItem> objectsScenario = new ArrayList<>(List.of(
                new ObjectItem("Shild", "Old shild", 44,
                        "public/image0.txt"),
                new ObjectItem("sword", "Sword with brooken blade", 44,
                        "public/image10.txt")));

        List<ObjectItem> objectsScenarioA45 = new ArrayList<>(List.of(
                new ObjectItem("Book", "Ancient scripture impossible to read", 45,
                        "public/image11.txt")));

        Scenario cenario1 = new Scenario("Pyramids to the horizon",
                1, true,
                "public/image1.txt", null);

        Scenario cenario2 = new Scenario("One of the pyranids",
                2, false,
                "public/image2.txt", null);

        Scenario cenario3 = new Scenario("Corridors, can be explored",
                3, false,
                "public/image3.txt", null);

        Scenario cenario4 = new Scenario("You will need to open it to advance",
                4, false,
                "public/image4.txt", null);

        Scenario cenario5 = new Scenario("You managed to open",
                5, false,
                "public/image5.txt", null);

        Scenario cenario6 = new Scenario("You have completed the game",
                6, null,
                "public/image8.txt", null);

        Scenario cenario7 = new Scenario("Fireplace, there may be objects here",
                45, false,
                "public/image7.txt", objectsScenarioA45);

        Scenario cenario8 = new Scenario("Laboratory, there are objects here",
                44, false,
                "public/image6.txt", objectsScenario);

        listOfSceneObjects.add(cenario1);
        listOfSceneObjects.add(cenario2);
        listOfSceneObjects.add(cenario3);
        listOfSceneObjects.add(cenario4);
        listOfSceneObjects.add(cenario5);
        listOfSceneObjects.add(cenario6);
        listOfSceneObjects.add(cenario7);
        listOfSceneObjects.add(cenario8);

    }

    public void initializeFirstScenario() {
        Scenario scenarioController = currenteScenario();
        if (scenarioController != null) {
            setCurrentScenario(1);
            returnDisplay(1);
        }
    }

    public void setCurrentScenario(Integer newId) {
        listOfSceneObjects.forEach(scenario -> scenario.setActual(false));
        listOfSceneObjects.stream().filter(scenarioFilter -> scenarioFilter.getScenarioId().equals(newId))
                .forEach(cenarioCurrent -> cenarioCurrent.setActual(true));

    }

    public Integer currentScenarioId() {
        for (Scenario scenarioPer : listOfSceneObjects) {
            if (scenarioPer.isCurrent()) {
                return scenarioPer.getScenarioId();

            }
        }
        return null;
    }

    public Scenario currenteScenario() {
        return listOfSceneObjects.stream()
                .filter(Scenario::isCurrent)
                .findFirst()
                .orElse(null);
    }

    public void removeObject(Integer idScenario, ObjectItem objectRemove) {
        for (Scenario scenarioPer : listOfSceneObjects) {
            if (scenarioPer.getScenarioId().equals(idScenario) && scenarioPer.getObjectScenarioList() != null) {
                scenarioPer.getObjectScenarioList().remove(objectRemove);
            }
        }
    }

    public ObjectItem findObjectById(Integer idScenario, String objectName) {
        for (Scenario scenario : listOfSceneObjects) {
            if (scenario.getScenarioId().equals(idScenario)) {
                if (scenario.getObjectScenarioList() != null) {
                    for (ObjectItem objPer : scenario.getObjectScenarioList()) {
                        if (objPer.getNameObject().equalsIgnoreCase(objectName)) {
                            return objPer;
                        }
                    }
                }
            }
        }
        return null;
    }

    public void returnDisplay(Integer newId) {
        Scenario targetScenario = listOfSceneObjects.stream()
                .filter(scenario -> scenario.getScenarioId().equals(newId))
                .findFirst()
                .orElse(null);

        if (targetScenario == null) {
            vv.displayMenssage("Scenario not found with ID: " + newId);
            return;
        }

        try {
            setCurrentScenario(newId);
            vv.displayImage(targetScenario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nextScenario() {
        Scenario currentScenario = currenteScenario();
        if (currentScenario != null) {
            int nextid = currentScenario.getScenarioId() + 1;
    
            if (nextid > listOfSceneObjects.size()) {
                if (currentScenario.getScenarioId() == 44 || currentScenario.getScenarioId() == 45) {
                    vv.displayMenssage("There are no options to move forward in this scenario");
                    return;
                }
                vv.displayMenssage("You are already in the last scenario.");
                return;
            }
    
            if (nextid == 4 && !ph.isStatus()) {
                vv.displayMenssage("\033[H\033[2J");
                returnDisplay(4);
                setCurrentScenario(4);
                boolean between = false;
                while (!between) {
                    vv.displayMenssage("Do you want to join the stage?");
                    String ent = sc.nextLine();
                    if (ent.equalsIgnoreCase("yes")) {
                        between = true;
                        ph.start();
                    } else if (ent.equalsIgnoreCase("no")) {
                        between = true;
                        setCurrentScenario(4);
                        return;
                    } else {
                        vv.displayMenssage("Invalid command enter yes or no");
                    }
                }
            } else if (currentScenario.getScenarioId() == 4 && !ph.isStatus()) {
                vv.displayMenssage("You need to pass the stage to enter. Go back one scenario and try again");
                return;
            } else if (currentScenario.getScenarioId() == 4 && ph.isStatus()) {
                setCurrentScenario(5);
                vv.displayMenssage("\033[H\033[2J");
                returnDisplay(5);
                return; 
            }
    
            vv.displayMenssage("\033[H\033[2J");
            returnDisplay(nextid);
    
        } else {
            vv.displayMenssage("Current scenario not found.");
        }
    }
    

    public void backScenario() {
        vv.displayMenssage("\033[H\033[2J");
        Scenario curretScenario = currenteScenario();
        if (curretScenario != null) {
            int previouId = currentScenarioId() - 1;

            if (currentScenarioId() == 1) {
                vv.displayMenssage("You are already in the first scenario, can't go back.");
                return;
            }

            if (previouId >= 1 && previouId <= 7) {
                setCurrentScenario(previouId);
                returnDisplay(previouId);
            } else if (curretScenario.getScenarioId() == 44 || curretScenario.getScenarioId() == 45) {
                setCurrentScenario(3);
                returnDisplay(3);
            } else {
                vv.displayMenssage("You can't go back anymore");
            }
        } else {
            vv.displayMenssage("Current scenario not found");
        }
    }

    public void leftCommand() {
        vv.displayMenssage("\033[H\033[2J");
        Scenario scenariocheck = currenteScenario();
        if (scenariocheck.getScenarioId() == 3) {
            setCurrentScenario(44);
            returnDisplay(44);
        } else {
            vv.displayMenssage("Invalid command in this scenario");
        }
    }

    public void rightCommand() {
        vv.displayMenssage("\033[H\033[2J");
        Scenario right = currenteScenario();
        if (right.getScenarioId() == 3) {
            setCurrentScenario(45);
            returnDisplay(45);
        } else {
            vv.displayMenssage("Ivalid command in this scenario");
        }
    }

    public void DisplayObjects() {
        Scenario currentScenario = currenteScenario();

        if (currentScenario != null && currentScenario.getObjectScenarioList() != null) {
            vv.displayMenssage("\nObjects in the current scenario:\n");

            vv.displayObjectImages(currentScenario);

            for (ObjectItem obj : currentScenario.getObjectScenarioList()) {
                vv.displayMenssage("\n" + obj.getDescription());
            }

        } else {
            vv.displayMenssage("There are no objects in this scene.");
        }
    }

    public List<Scenario> getListOfSceneObjects() {
        return listOfSceneObjects;
    }

}
