package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FXMainPane extends BorderPane {
    private Button decryptionButton, exitButton, encryptionButton, clearButton;
    private TextField plainTextTextField, inputForEncryptionTextField, encryptedStringTextField, decryptedTextField;
    private Label plainTextLabel, descriptionForInputLabel, encryptedLabel, decryptedLabel;
    private RadioButton radioButtonCaesar, radioButtonBellaso;

    public FXMainPane() {
        initializeComponents();
        addActions();
        buildLayout();
    }

    private void initializeComponents() {
        buildTextFields();
        buildLabels();
        buildRadioButtons();
        buildButtons();
    }

    private void addActions() {
        addActionToExitButton();
        addActionToClearButton();
        addActionToEncryptButton();
        addActionToDecryptButton();
    }

    private void buildLayout() {
        Insets inset = new Insets(10);

        HBox topBox = new HBox();
        HBox.setMargin(radioButtonCaesar, inset);
        topBox.setAlignment(Pos.CENTER);
        topBox.getChildren().addAll(radioButtonCaesar, radioButtonBellaso);
        topBox.setStyle("-fx-border-color: gray;");

        VBox centerBox = new VBox(10);
        centerBox.getChildren().addAll(plainTextLabel, plainTextTextField, encryptedLabel, encryptedStringTextField,
                decryptedLabel, decryptedTextField, descriptionForInputLabel, inputForEncryptionTextField);
        setCenter(centerBox);

        setTop(topBox);

        HBox bottomBox = new HBox();
        HBox.setMargin(decryptionButton, inset);
        HBox.setMargin(encryptionButton, inset);
        HBox.setMargin(clearButton, inset);
        HBox.setMargin(exitButton, inset);
        bottomBox.getChildren().addAll(encryptionButton, decryptionButton, clearButton, exitButton);
        setBottom(bottomBox);
        bottomBox.setAlignment(Pos.CENTER);
    }

    private void buildTextFields() {
        plainTextTextField = new TextField();
        inputForEncryptionTextField = new TextField();
        encryptedStringTextField = new TextField();
        decryptedTextField = new TextField();
    }

    private void buildLabels() {
        plainTextLabel = new Label("Enter plain-text string to encrypt");
        descriptionForInputLabel = new Label("Cyber Key - enter an integer for Caesar Cipher or a string for Bellaso Cipher");
        encryptedLabel = new Label("Encrypted string");
        decryptedLabel = new Label("Decrypted string");
    }

    private void buildRadioButtons() {
        radioButtonCaesar = new RadioButton("Use Caesar cipher");
        radioButtonBellaso = new RadioButton("Use Bellaso cipher");
        ToggleGroup radioButtonGroup = new ToggleGroup();
        radioButtonCaesar.setToggleGroup(radioButtonGroup);
        radioButtonBellaso.setToggleGroup(radioButtonGroup);
        radioButtonCaesar.setSelected(true);
        radioButtonCaesar.setAlignment(Pos.CENTER);
        radioButtonBellaso.setAlignment(Pos.CENTER);
        RadioButtonListener radioButtonListener = new RadioButtonListener();
        radioButtonCaesar.setOnAction(radioButtonListener);
        radioButtonBellaso.setOnAction(radioButtonListener);
    }

    private void buildButtons() {
        exitButton = new Button("Exit");
        clearButton = new Button("Clear");
        decryptionButton = new Button("Decrypt a string");
        encryptionButton = new Button("Encrypt a string");
    }

    private void addActionToExitButton() {
        exitButton.setOnAction(event -> Platform.exit());
    }

    private void addActionToClearButton() {
        clearButton.setOnAction(event -> {
            plainTextTextField.clear();
            inputForEncryptionTextField.clear();
            encryptedStringTextField.clear();
            decryptedTextField.clear();
        });
    }

    private void addActionToEncryptButton() {
        encryptionButton.setOnAction(event -> {
            try {
                String key = inputForEncryptionTextField.getText();
                String plainText = plainTextTextField.getText().toUpperCase();
                String encryptedText = "";
                if (radioButtonCaesar.isSelected()) {
                    int shiftInt = Integer.parseInt(key);
                    encryptedText = CryptoManager.caesarEncryption(plainText, shiftInt);
                } else {
                    encryptedText = CryptoManager.bellasoEncryption(plainText, key);
                }
                encryptedStringTextField.setText(encryptedText.isEmpty() ? "Encryption failed" : encryptedText);
            } catch (NumberFormatException e) {
                System.out.println("Cyber Key should be an integer");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    private void addActionToDecryptButton() {
        decryptionButton.setOnAction(event -> {
            try {
                String key = inputForEncryptionTextField.getText();
                String encryptedText = encryptedStringTextField.getText().toUpperCase();
                String decryptedText = "";
                if (radioButtonCaesar.isSelected()) {
                    int shiftInt = Integer.parseInt(key);
                    decryptedText = CryptoManager.caesarDecryption(encryptedText, shiftInt);
                } else {
                    decryptedText = CryptoManager.bellasoDecryption(encryptedText, key);
                }
                decryptedTextField.setText(decryptedText);
            } catch (NumberFormatException e) {
                System.out.println("Cyber Key should be an integer");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    class RadioButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (radioButtonCaesar.isSelected()) {
                descriptionForInputLabel.setText("Cyber Key - enter an integer for Caesar Cipher");
            } else if (radioButtonBellaso.isSelected()) {
                descriptionForInputLabel.setText("Cyber Key - enter a string for Bellaso Cipher");
            }
        }
    }
}
