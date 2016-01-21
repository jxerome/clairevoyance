package com.mainaud.clairevoyance;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.List;

import org.fxmisc.richtext.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Clairevoyance extends Application {

    private CodeArea codeArea;
    private StackPane responsePane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane scenePane = new BorderPane();
        scenePane.setPrefSize(1000d, 600);
        scenePane.setTop(createToolBar(primaryStage));
        scenePane.setCenter(createMainPane(primaryStage));

        Scene scene = new Scene(scenePane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private SplitPane createMainPane(Stage primaryStage) {
        SplitPane mainPane = new SplitPane();

        codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        mainPane.getItems().add(codeArea);

        responsePane = new StackPane();
        mainPane.getItems().add(responsePane);

        mainPane.setDividerPositions(0.25);

        mainPane.setOnKeyPressed(this::handleKeyEvent);

        return mainPane;
    }

    private void handleKeyEvent(KeyEvent e) {
        if (isCrtlOrMeta(e)) {
            switch (e.getCode()) {
                case ENTER:
                    runCommand();
                    break;
                case A:
                    codeArea.selectAll();
                    break;
            }
        }
    }

    private boolean isCrtlOrMeta(KeyEvent e) {
        return e.isControlDown() || e.isMetaDown();
    }

    private ToolBar createToolBar(Stage primaryStage) {
        ToolBar toolBar = new ToolBar();

        Button runBtn = new Button("Run");
        runBtn.setOnAction((ActionEvent e) -> runCommand());
        toolBar.getItems().add(runBtn);

        Button quitBtn = new Button("Quitter");
        quitBtn.setOnAction((ActionEvent e) -> primaryStage.close());
        toolBar.getItems().add(quitBtn);
        return toolBar;
    }



    private void runCommand() {
        String text = codeArea.getText().trim();

        TextArea resultTa = new TextArea();
        resultTa.setText(text);
        responsePane.getChildren().clear();
        responsePane.getChildren().add(resultTa);
    }

    public static void main(String[] args) throws IOException {
        launch(Clairevoyance.class, args);
    }
}
