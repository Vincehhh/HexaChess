package im.bpu.hexachess;

import im.bpu.hexachess.ui.HexPanel;
import im.bpu.hexachess.entity.Player;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;

public class MainWindow {
	private HexPanel hexPanel;
	@FXML private Button settingsHelpButton;
	@FXML private Canvas canvas;
	@FXML private HBox authBox;
	@FXML private HBox userBox;
	@FXML private Label usernameLabel;
	@FXML private Label ratingLabel;

	@FXML
	private void initialize() {
		hexPanel = new HexPanel(canvas, State.getState());
	}

	public void setSession(Player player) {
		if (player != null) {
			authBox.setVisible(false);
			authBox.setManaged(false);
			userBox.setVisible(true);
			userBox.setManaged(true);
			usernameLabel.setText(player.getHandle());
			ratingLabel.setText("Rating: " + player.getRating());
		} else {
			authBox.setVisible(true);
			authBox.setManaged(true);
			userBox.setVisible(false);
			userBox.setManaged(false);
		}
	}

	@FXML
	private void restart() {
		hexPanel.restart();
	}
	@FXML
	private void rewind() {
		hexPanel.rewind();
	}
	@FXML
	private void openSettings() {
		try {
			FXMLLoader settingsWindowLoader = new FXMLLoader(getClass().getResource("ui/settingsWindow.fxml"));
			Parent root = settingsWindowLoader.load();
			settingsHelpButton.getScene().setRoot(root);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@FXML
    private void goToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/login.fxml"));
            Parent root = loader.load();
            settingsHelpButton.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/register.fxml"));
            Parent root = loader.load();
            settingsHelpButton.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}