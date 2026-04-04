package model;

import service.InputService;

import java.util.ArrayList;

public class PhaseOne extends Phase{
    private boolean status = false;
    private InputService inputService;
    private Mainplayer player;
    private ScenarioService scenarioManager;
    ArrayList<ObjectItem> listPhase = new ArrayList<>();

    public PhaseOne(boolean status, Mainplayer player, ScenarioService scenarioManager, InputService inputService) {
        this.status = status;
        this.player = player;
        this.scenarioManager = scenarioManager;
        this.inputService = inputService;
    }

    public PhaseOne(boolean status) {
        this.status = status;
    }

    public Boolean start() {

        addPhase1();

        boolean continuePhase = true;

        while (continuePhase) {
            boolean phaseCompleted = checkObjectInPhase1();
            if (phaseCompleted) {
                setStatus(true);
                System.out.println("Congratulations! You passed!");
                return true;
            } else {
                System.out.println("Incorrect items or you didn't collect the required items. Please try again!");
            }

            continuePhase = ifiTContinues();
        }

        return false;
    }

    private boolean ifiTContinues() {
        while (true) {
            System.out.print("Do you want to try again? (yes/no):");
            String response = inputService.readNormalizedInput("Do you want to try again? (yes/no):");
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
                String response = inputService.readNormalizedInput("Object " + (i + 1) + ": ");
                chec.add(response);
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

    private boolean addPhase1() {

        if (!listPhase.isEmpty()) {
            return false;
        }

        ObjectItem shild = new ObjectItem("shild", null,
                null, null);
        ObjectItem sword = new ObjectItem("sword", null,
                null, null);
        ObjectItem book = new ObjectItem("book", null,
                null, null);

        listPhase.add(sword);
        listPhase.add(shild);
        listPhase.add(book);

        return true;

    }

    private boolean checkListInventory() {
        if (player.getInventory() == null) return false;

        return listPhase.stream().allMatch(itemFase ->
                player.getInventory().stream().anyMatch(itemInv ->
                        itemInv.getNameObject().equalsIgnoreCase(itemFase.getNameObject())
                )
        );
    }
}