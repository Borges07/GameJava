import model.CommandList;
import model.Mainplayer;
import model.Phases;
import model.ScenarioService;
import service.TerminalViewService;
import controller.ControllerGame;
import service.impl.TerminalViewServiceImpl;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Mainplayer p1 = new Mainplayer("Maveras");
        TerminalViewService view = new TerminalViewServiceImpl();
        ScenarioService env = new ScenarioService(p1, view);
        Phases phasesIn = new Phases(false, p1, env);
        ControllerGame controllerGameIn = new ControllerGame(p1, env, phasesIn);
        CommandList cmd = new CommandList();

        // --> Matheus arrume todos esses bugs nojentos dentro dessa branch, comentario para commit

        env.initializeFirstScenario();
        view.displayMenssage("\n<===============> Welcome to the game! <===================>");
        
        view.displayMenssage("<=============>COMMANDS TO USE IN THE GAME<==============>");
        for (String commandPer : cmd.getActionCommandlist()) {
            view.displayMenssage(commandPer);
        }

        boolean sair2 = false;

        while (!sair2) {
            view.displayMenssage("                   <--- START --->\n                   <--- CLOSE --->");
            String start = sc.nextLine().trim();

            if (start.equalsIgnoreCase("start")) {
                boolean sair = false;
                view.displayMenssage("\033[H\033[2J");
                env.initializeFirstScenario();
                while (!sair) {
                    try {
                        view.displayMenssage("Enter the commands (or 'exit' to finish):");
                        String input = sc.nextLine().trim();

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

        sc.close();
    }
}
