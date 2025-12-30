package im.bpu.hexachess;

import im.bpu.hexachess.dao.PlayerDAO;
import im.bpu.hexachess.entity.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.UUID;
import org.mindrot.jbcrypt.BCrypt;

public class RegisterWindow {
    @FXML private TextField handleField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;

@FXML
    private void handleRegister() {
        PlayerDAO dao = new PlayerDAO();
        String id = UUID.randomUUID().toString().substring(0, 11);
        Player newPlayer = new Player(
            id, 
            handleField.getText(), 
            emailField.getText(), 
            BCrypt.hashpw(passwordField.getText(), BCrypt.gensalt()),
            1200, 
            false, 
            java.time.LocalDateTime.now()
        );
        
        try {
            dao.create(newPlayer); 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/mainWindow.fxml"));
            Parent root = loader.load();
            MainWindow controller = loader.getController();
            controller.setSession(newPlayer);
            handleField.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Erreur (Pseudo déjà pris ?)");
            statusLabel.setVisible(true);
            statusLabel.setStyle("-fx-text-fill: red;");
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