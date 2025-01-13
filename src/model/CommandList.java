package model;

import java.util.ArrayList;

public class CommandList {
    private ArrayList<String> actionCommandlist = new ArrayList<>();

    public CommandList() {
        addCommand("go to");
        addCommand("go back");
        addCommand("look scenario");
        addCommand("look inventory");
        addCommand("go left");
        addCommand("go right");
        addCommand("get");
        addCommand("look objects");
    }

    private void addCommand(String command) {
        actionCommandlist.add(command.toLowerCase());
    }

    public boolean isValidCommand(String commandCheck) {
        return actionCommandlist.contains(commandCheck.toLowerCase());
    }

    public String findfullCommand(String[] command) {
        for (int i = 0; i < command.length; i++) {
           String fullCommand = command[i];
            for(int j = i + 1; j < command.length && j <= i + 1; j++){
                fullCommand += " " + command[j];
                if (isValidCommand(fullCommand)) {
                    return fullCommand;
                }
            }
        } return command[0];
    }

    public ArrayList<String> getActionCommandlist() {
        return actionCommandlist;
    }

    public void setActionCommandlist(ArrayList<String> actionCommandlist) {
        this.actionCommandlist = actionCommandlist;
    }

    
}
