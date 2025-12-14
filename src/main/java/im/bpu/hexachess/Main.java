package im.bpu.hexachess;

import im.bpu.hexachess.model.Board;
import im.bpu.hexachess.ui.HexPanel;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		Board board = new Board();
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.add(new HexPanel(board), 0, 0);
		Scene scene = new Scene(gridPane);
		stage.setScene(scene);
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}