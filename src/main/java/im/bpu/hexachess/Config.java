package im.bpu.hexachess;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
	private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
	public static String get(String key, String defaultValue) {
		String value = dotenv.get(key);
		return (value != null) ? value : defaultValue;
	}
}