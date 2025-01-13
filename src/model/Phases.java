package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Phases {
    private boolean status = false;
    ArrayList<ObjectItem> listPhase = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    private Mainplayer player;
    private ScenarioCriation scenarioManager;

    public Phases(boolean status, Mainplayer player, ScenarioCriation scenarioManager) {
        this.status = status;
        this.player = player;
        this.scenarioManager = scenarioManager;
    }

    public Phases(boolean status) {
        this.status = status;
    }

    public void start() {
        addPhase1();
        boolean continuar = true;

        while (continuar) {
            boolean faseCompleta = checkObjectInPhase1();
            if (faseCompleta) {
                System.out.println("Congratulations! You passed!");
                setStatus(true);
                break;
            } else {
                System.out.println("Incorrect items or you didn't collect the required items. Please try again!");
            }

            continuar = ifiTContinues();
        }
    }

    private boolean ifiTContinues() {
        while (true) {
            System.out.print("Do you want to try again? (yes/no):");
            String response = sc.nextLine().trim().toLowerCase();
            if (response.equals("yes")) {
                return true;
            } else if (response.equals("no")) {
                setStatus(false);
                return false;
            } else {
                System.out.println("INVALID INPUT. PLEASE ENTER AGAIN.\n");
            }
        }
    }

    public boolean checkObjectInPhase1() {
        ArrayList<String> chec = new ArrayList<>();
        if (player.getInventory() != null && checkListInventory()) {

            System.out.println("Enter the required objects:");

            for (int i = 0; i < 3; i++) {
                System.out.print("Object " + (i + 1) + ": ");
                String leitura = sc.nextLine().trim().toLowerCase();
                chec.add(leitura);
            }

            boolean todosPresentes = true;
            for (String item : chec) {
                boolean encontrado = false;
                for (ObjectItem per : listPhase) {
                    if (item.equals(per.getNameObject().toLowerCase())) {
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    todosPresentes = false;
                    break;
                }
            }
            return todosPresentes;
        }
        return false;
    }

    private void addPhase1() {
        ObjectItem shild = new ObjectItem("shild", null,
                null, null);
        ObjectItem sword = new ObjectItem("sword", null,
                null, null);
        ObjectItem book = new ObjectItem("book", null,
                null, null);

        listPhase.add(sword);
        listPhase.add(shild);
        listPhase.add(book);
    }

    private boolean checkListInventory() {
        if (player.getInventory() != null) {
            for (ObjectItem objectItemPer : listPhase) {
                for (ObjectItem perInventory : player.getInventory()) {
                    if (objectItemPer.getNameObject().equals(perInventory.getNameObject())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ScenarioCriation getScenarioManager() {
        return scenarioManager;
    }

    public void setScenarioManager(ScenarioCriation scenarioManager) {
        this.scenarioManager = scenarioManager;
    }
}