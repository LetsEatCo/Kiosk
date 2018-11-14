package main.fr.esgi.kiosk.models.ui;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Parent;
import main.fr.esgi.kiosk.controllers.CommandController;
import main.fr.esgi.kiosk.models.Section;

public class SectionUI extends Parent {

    private Section section;
    private CommandController commandController;
    public SectionUI(Section section, CommandController commandController) {

        this.section = section;
        this.commandController = commandController;

        initUI();
    }

    private void initUI() {

        JFXButton btn = new JFXButton(section.getName());
        btn.prefWidth(300);
        btn.prefHeight(200);

        btn.minHeight(btn.getPrefHeight());
        btn.minWidth(btn.getPrefWidth());

        btn.maxWidth(btn.getPrefWidth());
        btn.maxHeight(btn.getPrefHeight());

        btn.getStyleClass().add("btn");
        btn.setOnAction(event -> commandController.createUIElements(this.section));
        this.getChildren().add(btn);
        commandController.addSections(this);

    }
}
