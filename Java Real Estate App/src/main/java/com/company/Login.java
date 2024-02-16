package com.company;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


//This class is Controller of login.fxml
public class Login {

    public TextField usernameTextField, passwordTextField;
    public Label statusLabel;
    public TextField registerUsernameTextField;
    public TextField registerPasswordTextField;

    //When the login button is clicked, the login information is checked.
    //If the information is correct, you are logged in.
    //Otherwise the content of status Label is replaced with "Invalid Values".
    public void loginButtonOnActionListener() throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        if (username.equals("admin") && password.equals("123")) {
            try {
                Main.setScene("homePage.fxml");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(
                    "users.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {

                    if (line.split(",")[0].equals(username) &&
                            line.split(",")[1].equals(password)) {
                        Main.setScene("homePageUser.fxml");
                        break;
                    }
                }
            }
        }
    }

    public void registerButtonOnActionListener() {
        Path p = Paths.get("users.txt");
        if (!registerUsernameTextField.getText().isEmpty() &&
                !registerPasswordTextField.getText().isEmpty()) {
            String s = registerUsernameTextField.getText()+","+
                    registerPasswordTextField.getText()+"\n";
            try {
                Files.write(p, s.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.err.println(e);
            }
        }

    }
}