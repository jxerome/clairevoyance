package com.mainaud.clairevoyance;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Clairevoyance extends Application {

    private boolean showLabel = true;

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

        CodeArea codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        mainPane.getItems().add(codeArea);

        StackPane responsePane = new StackPane();
        mainPane.getItems().add(responsePane);

        mainPane.setDividerPositions(0.25);

        return mainPane;
    }

    private ToolBar createToolBar(Stage primaryStage) {
        ToolBar toolBar = new ToolBar();

        Button runBtn = new Button("Run");
        toolBar.getItems().add(runBtn);

        Button quitBtn = new Button("Quitter");
        quitBtn.setOnAction((ActionEvent e) -> {
            primaryStage.close();
        });
        toolBar.getItems().add(quitBtn);
        return toolBar;
    }

    public static void main(String[] args) {
        launch(Clairevoyance.class, args);
    }
}
