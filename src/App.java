import model.*;
import service.BaseServiceController;
import service.TerminalViewService;
import controller.ControllerGame;
import service.impl.BaseServiceControllerImpl;
import service.impl.TerminalViewServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        TerminalViewService view = new TerminalViewServiceImpl();
        Mainplayer p1 = new Mainplayer("Maveras");
        ScenarioService env = new ScenarioService(p1, view, null);
        PhaseOne phaseOneIn = new PhaseOne(false, p1, env, null);
        BaseServiceController baseServiceController = new BaseServiceControllerImpl(p1, env, view);
        ControllerGame controllerGameIn = new ControllerGame(p1, env, view, baseServiceController);
        CommandList cmd = new CommandList();

        env.initializeFirstScenario();

        view.startPhaseView();

        for (String commandPer : cmd.getActionCommandlist()) {
            view.displayMenssage(commandPer);
        }

        boolean sair2 = false;

        while (!sair2) {
            view.displayMenssage("                   <--- START --->\n                   <--- CLOSE --->");
            String start = sc.nextLine().trim(); // mudar aqui <-- mudar aqui também matheus

            if (start.equalsIgnoreCase("start")) {
                boolean sair = false;
                view.displayMenssage("\033[H\033[2J");
                env.initializeFirstScenario();
                while (!sair) {
                    try {
                        view.displayMenssage("Enter the commands (or 'exit' to finish):");
                        String input = sc.nextLine().trim(); // Mudar aqui também <-- mudar aqui matheus
                        p1.toString();

                        if (input.equalsIgnoreCase("exist")) {
                            sair = true;
                        } else {
                            controllerGameIn.useCommand(input);
                        }
                    } catch (Exception e) {
                        view.displayMenssage("An error occurred: " + e.getMessage());
                        e.printStackTrace();
                    }
                }

            } else if (start.equalsIgnoreCase("close")) {
                sair2 = true;
                view.displayMenssage("leaving the game...");
            } else {
                view.displayMenssage("INVALID ENTRY");
            }
        }

        sc.close(); // mudar aqui também <-- mudar aqui Matheus
    }
}
