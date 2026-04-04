package service.impl;

import service.InputService;
import service.TerminalViewService;

import java.util.Scanner;

public class InputServiceImpl implements InputService {
    private final Scanner scanner = new Scanner (System.in);
    private TerminalViewService displayService;

    public InputServiceImpl(TerminalViewService displayService) {
        this.displayService = displayService;
    }

    @Override
    public String readNormalizedInput (String command) {
        displayService.displayMenssage(command);
        return scanner.nextLine().trim().toLowerCase();
    }

    @Override
    public String commandTerminal() {
        return scanner.nextLine();
    }

}
