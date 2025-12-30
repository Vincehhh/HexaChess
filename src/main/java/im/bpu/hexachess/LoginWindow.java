package im.bpu.hexachess;

import im.bpu.hexachess.dao.PlayerDAO;
import im.bpu.hexachess.entity.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;

public class LoginWindow {
    @FXML private TextField handleField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML
    private void handleLogin() {
        PlayerDAO dao = new PlayerDAO();
        String handle = handleField.getText();
        String pass = passwordField.getText();

        Player p = dao.getPlayerByHandle(handle);

        boolean loginSuccess = false;

        if(p != null) {
            if(BCrypt.checkpw(pass, p.getPasswordHash())) {
                loginSuccess = true;
            }
        }

        if (loginSuccess) {
            System.out.println("Connecté en tant que : " + p.getHandle());
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/mainWindow.fxml"));
                Parent root = loader.load();
                MainWindow controller = loader.getController();
                controller.setSession(p);
                handleField.getScene().setRoot(root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            errorLabel.setText("Pseudo ou mot de passe incorrect");
            errorLabel.setVisible(true);
            errorLabel.setStyle("-fx-text-fill: red;");
        }
        dao.close();
    }

    @FXML
    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/mainWindow.fxml"));
            Parent root = loader.load();
            handleField.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}