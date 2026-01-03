package im.bpu.hexachess;

import im.bpu.hexachess.entity.Player;
import im.bpu.hexachess.network.API;

import java.util.List;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class SearchWindow {
	private static final String BASE_URL =
		"https://www.chess.com/bundles/web/images/noavatar_l.gif";
	@FXML private TextField searchField;
	@FXML private VBox playerContainer;
	@FXML private Button backButton;
	@FXML
	private void handleSearch() {
		playerContainer.getChildren().clear();
		String query = searchField.getText();
		if (query.isEmpty())
			return;
		List<Player> players = API.search(query);
		for (Player player : players) {
			String handle = player.getHandle();
			int rating = player.getRating();
			String location = player.getLocation();
			String avatarUrl = (player.getAvatar() != null && !player.getAvatar().isEmpty())
				? player.getAvatar()
				: BASE_URL;
			ImageView avatarIcon = new ImageView(new Image(avatarUrl, true));
			avatarIcon.setFitHeight(42);
			avatarIcon.setFitWidth(42);
			avatarIcon.setPreserveRatio(true);
			VBox avatarContainer = new VBox(avatarIcon);
			avatarContainer.getStyleClass().add("avatar-container");
			Label handleLabel = new Label(handle);
			HBox handleCountryFlag = new HBox(handleLabel);
			handleCountryFlag.setSpacing(8);
			Label ratingLabel = new Label("Rating: " + rating);
			if (location != null && !location.isEmpty()) {
				Region countryFlagIcon = new Region();
				countryFlagIcon.getStyleClass().addAll(
					"country-flags-large", "country-" + location);
				handleCountryFlag.getChildren().add(countryFlagIcon);
			}
			VBox playerInfo = new VBox(handleCountryFlag, ratingLabel);
			Region spacer = new Region();
			Button challengeButton = new Button("⚔");
			HBox.setHgrow(spacer, Priority.ALWAYS);
			HBox playerItem = new HBox(avatarContainer, playerInfo, spacer, challengeButton);
			playerItem.setSpacing(12);
			playerItem.getStyleClass().add("player-item");
			playerItem.setOnMouseClicked(event -> openProfile(handle));
			challengeButton.getStyleClass().add("square-button");
			challengeButton.setOnAction(event -> startMatchmaking(handle));
			playerContainer.getChildren().add(playerItem);
		}
	}
	private void startMatchmaking(String target) {
		new Thread(() -> {
			String handle = Settings.userHandle;
			while (true) {
				String resp = API.challenge(handle, target);
				if (resp != null && !resp.equals("Pending")) {
					Platform.runLater(() -> {
						State state = State.getState();
						state.clear();
						state.isMultiplayer = true;
						state.gameId = resp;
						state.isWhitePlayer = handle.compareTo(target) < 0;
						openMain();
					});
					break;
				}
				try {
					Thread.sleep(2000);
				} catch (Exception ignored) { // high-frequency polling operation
				}
			}
		}).start();
	}
	private void openProfile(String handle) {
		try {
			Settings.userHandle = handle;
			FXMLLoader profileWindowLoader =
				new FXMLLoader(getClass().getResource("ui/profileWindow.fxml"));
			profileWindowLoader.setController(new ProfileWindow());
			Parent root = profileWindowLoader.load();
			backButton.getScene().setRoot(root);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	@FXML
	private void openMain() {
		try {
			FXMLLoader mainWindowLoader =
				new FXMLLoader(getClass().getResource("ui/mainWindow.fxml"));
			mainWindowLoader.setController(new MainWindow());
			Parent root = mainWindowLoader.load();
			backButton.getScene().setRoot(root);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}