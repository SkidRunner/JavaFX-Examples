package io.skidrunner.examples;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

import java.util.HashMap;

public class BasicBrowserController {

    private static final String DEFAULT_URL = "https://github.com/SkidRunner/index.html";
    
    @FXML
    private MenuButton historyMenuButton;
    @FXML
    private Button reloadButton;
    @FXML
    private Button githubButton;
    @FXML
    private TextField urlTextField;
    @FXML
    private WebView webView;
    @FXML
    private ProgressBar progressBar;

    private final HashMap<String, MenuItem> webHistory = new HashMap<>();

    @FXML
    public void initialize() {
        final WebEngine webEngine = webView.getEngine();

        progressBar.progressProperty().bind(webEngine.getLoadWorker().progressProperty());
        urlTextField.textProperty().bind(webEngine.locationProperty());

        webEngine.getHistory().getEntries().addListener((ListChangeListener<WebHistory.Entry>) change -> {
            change.next();
            for (WebHistory.Entry entry : change.getRemoved()) {
                MenuItem menuItem = webHistory.get(entry.getUrl());
                if(menuItem != null) {
                    historyMenuButton.getItems().remove(menuItem);
                }
            }

            for (WebHistory.Entry entry : change.getAddedSubList()) {
                final String url = entry.getUrl();
                MenuItem menuItem = new MenuItem(entry.getTitle());
                menuItem.setOnAction((event) -> webEngine.load(url));
                webHistory.put(url, menuItem);
            }

        });
        reloadButton.setOnMouseClicked((event) -> webEngine.reload());
        githubButton.setOnMouseClicked((event) -> webEngine.load(DEFAULT_URL));
        webEngine.load(DEFAULT_URL);
    }

}
