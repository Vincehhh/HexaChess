package im.bpu.hexachess.ui;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;

class PieceImageLoader {
	private static final String BASE_URL =
		"https://images.chesscomfiles.com/chess-themes/pieces/classic/300/";
	private static final Map<String, Image> images = new HashMap<>();
	private static void loadImage(String key) {
		try {
			images.put(key, new Image(BASE_URL + key + ".png"));
		} catch (Exception exception) {
			System.err.println("Exception: " + exception.getMessage());
		}
	}
	static void loadImages() {
		for (String c : new String[] {"w", "b"})
			for (String t : new String[] {"p", "r", "n", "b", "q", "k"}) loadImage(c + t);
	}
	static Image get(String key) {
		return images.get(key);
	}
}