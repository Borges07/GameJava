package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import service.TerminalViewService;

public class ScenarioService {
    private final List<Scenario> scenarios = new ArrayList<>();
    private TerminalViewService display;
    private Phases ph = new Phases(false);
    private Scanner sc = new Scanner(System.in);

    public ScenarioService(Mainplayer player, TerminalViewService display) {
        this.ph = new Phases(false, player, this);
        this.display = display;
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

        scenarios.add(cenario1);
        scenarios.add(cenario2);
        scenarios.add(cenario3);
        scenarios.add(cenario4);
        scenarios.add(cenario5);
        scenarios.add(cenario6);
        scenarios.add(cenario7);
        scenarios.add(cenario8);

    }

    public void initializeFirstScenario() {
        Scenario scenarioController = currenteScenario();
        if (scenarioController != null) {
            setCurrentScenario(1);
            returnDisplay(1);
        }
    }

    public void setCurrentScenario(Integer newId) {
        scenarios.forEach(scenario -> scenario.setActual(false));
        scenarios.stream().filter(scenarioFilter -> scenarioFilter.getScenarioId().equals(newId))
                .forEach(cenarioCurrent -> cenarioCurrent.setActual(true));

    }

    public Integer currentScenarioId() {
        for (Scenario scenarioPer : scenarios) {
            if (scenarioPer.isCurrent()) {
                return scenarioPer.getScenarioId();

            }
        }
        return null;
    }

    public Scenario currenteScenario() {
        return scenarios.stream()
                .filter(Scenario::isCurrent)
                .findFirst()
                .orElse(null);
    }

    public void removeObject(Integer idScenario, ObjectItem objectRemove) {
        for (Scenario scenarioPer : scenarios) {
            if (scenarioPer.getScenarioId().equals(idScenario) && scenarioPer.getObjectScenarioList() != null) {
                scenarioPer.getObjectScenarioList().remove(objectRemove);
            }
        }
    }

    public ObjectItem findObjectById(Integer idScenario, String objectName) {
        for (Scenario scenario : scenarios) {
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
        Scenario targetScenario = scenarios.stream()
                .filter(scenario -> scenario.getScenarioId().equals(newId))
                .findFirst()
                .orElse(null);

        if (targetScenario == null) {
            display.displayMenssage("Scenario not found with ID: " + newId);
            return;
        }

        try {
            setCurrentScenario(newId);
            display.displayImage(targetScenario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nextScenario() {
        Scenario currentScenario = currenteScenario();
        if (currentScenario != null) {
            int nextid = currentScenario.getScenarioId() + 1;
    
            if (nextid > scenarios.size()) {
                if (currentScenario.getScenarioId() == 44 || currentScenario.getScenarioId() == 45) {
                    display.displayMenssage("There are no options to move forward in this scenario");
                    return;
                }
                display.displayMenssage("You are already in the last scenario.");
                return;
            }
    
            if (nextid == 4 && !ph.isStatus()) {
                display.displayMenssage("\033[H\033[2J");
                returnDisplay(4);
                setCurrentScenario(4);
                boolean between = false;
                while (!between) {
                    display.displayMenssage("Do you want to join the stage?");
                    String ent = sc.nextLine();
                    System.out.println("depois do join stage aqui");
                    if (ent.equalsIgnoreCase("yes")) {
                        System.out.println("depois do if de start");
                        between = true;
                        System.out.println("próxima linha é o ph.start");
                        ph.start();
                    } else if (ent.equalsIgnoreCase("no")) {
                        between = true;
                        setCurrentScenario(4);
                        return;
                    } else {
                        display.displayMenssage("Invalid command enter yes or no");
                    }
                }
            } else if (currentScenario.getScenarioId() == 4 && !ph.isStatus()) {
                display.displayMenssage("You need to pass the stage to enter. Go back one scenario and try again");
                return;
            } else if (currentScenario.getScenarioId() == 4 && ph.isStatus()) {
                display.displayMenssage("\033[H\033[2J");
                returnDisplay(5);
                setCurrentScenario(5);
                return;
            }
    
            display.displayMenssage("\033[H\033[2J");
            returnDisplay(nextid);
    
        } else {
            display.displayMenssage("Current scenario not found.");
        }
    }
    

    public void backScenario() {
        display.displayMenssage("\033[H\033[2J");
        Scenario curretScenario = currenteScenario();
        if (curretScenario != null) {
            int previouId = currentScenarioId() - 1;

            if (currentScenarioId() == 1) {
                display.displayMenssage("You are already in the first scenario, can't go back.");
                return;
            }

            if (previouId >= 1 && previouId <= 7) {
                setCurrentScenario(previouId);
                returnDisplay(previouId);
            } else if (curretScenario.getScenarioId() == 44 || curretScenario.getScenarioId() == 45) {
                setCurrentScenario(3);
                returnDisplay(3);
            } else {
                display.displayMenssage("You can't go back anymore");
            }
        } else {
            display.displayMenssage("Current scenario not found");
        }
    }

    public void leftCommand() {
        display.displayMenssage("\033[H\033[2J");
        Scenario scenariocheck = currenteScenario();
        if (scenariocheck.getScenarioId() == 3) {
            setCurrentScenario(44);
            returnDisplay(44);
        } else {
            display.displayMenssage("Invalid command in this scenario");
        }
    }

    public void rightCommand() {
        display.displayMenssage("\033[H\033[2J");
        Scenario right = currenteScenario();
        if (right.getScenarioId() == 3) {
            setCurrentScenario(45);
            returnDisplay(45);
        } else {
            display.displayMenssage("Ivalid command in this scenario");
        }
    }

    public void DisplayObjects() {
        Scenario currentScenario = currenteScenario();

        if (currentScenario != null && currentScenario.getObjectScenarioList() != null) {
            display.displayMenssage("\nObjects in the current scenario:\n");

            display.displayObjectImages(currentScenario);

            for (ObjectItem obj : currentScenario.getObjectScenarioList()) {
                display.displayMenssage("\n" + obj.getDescription());
            }

        } else {
            display.displayMenssage("There are no objects in this scene.");
        }
    }

    public List<Scenario> getListOfSceneObjects() {
        return scenarios;
    }

}
