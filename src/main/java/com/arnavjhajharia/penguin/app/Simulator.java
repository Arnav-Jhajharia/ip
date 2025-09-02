package com.arnavjhajharia.penguin.app;

import com.arnavjhajharia.penguin.common.exceptions.PenguinException;
import com.arnavjhajharia.penguin.logic.Parser;
import com.arnavjhajharia.penguin.logic.commands.Command;
import com.arnavjhajharia.penguin.logic.commands.CommandResult;
import com.arnavjhajharia.penguin.model.TaskList;
import com.arnavjhajharia.penguin.ui.Ui;

public class Simulator {
    private final Parser parser = new Parser();
    private final TaskList tasks;
    private final Ui ui;

    public Simulator(String filePath, Ui ui) {
        this.tasks = new TaskList(100, filePath);
        this.ui = ui;
    }

    public void start() {
        ui.showIntro();

        while (true) {
            String prompt = ui.readLine();
            if (prompt == null) { // EOF / input closed: behave like bye
                shutdown();
                return;
            }

            try {
                Command cmd = parser.parse(prompt);
                CommandResult result = cmd.execute(tasks);
                ui.showDivider();
                ui.showText(result.message());
                ui.showDivider();

                if (result.isExit()) {
                    shutdown();
                    return;
                }
            } catch (PenguinException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    private void shutdown() {
        tasks.save();
        ui.showExit();
        // Do NOT System.exit() here; let the outer Main decide.
    }
}
