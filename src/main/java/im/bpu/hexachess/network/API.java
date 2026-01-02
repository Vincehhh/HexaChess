package im.bpu.hexachess.network;

import im.bpu.hexachess.Config;
import im.bpu.hexachess.entity.Player;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class API {
	private static final String PROD_URL = Config.get("PROD_URL", "https://hexachess.bpu.im/api");
	private static final String DEV_URL = Config.get("DEV_URL", "http://localhost:8800/api");
	private static final HttpClient client =
		HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
	private static final ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.registerModule(new JavaTimeModule());
	}
	private static HttpResponse<String> sendWithFallback(
		HttpRequest.Builder requestBuilder, String endpoint) throws Exception {
		try {
			HttpRequest request = requestBuilder.uri(URI.create(PROD_URL + endpoint)).build();
			return client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (Exception exception) {
			HttpRequest request = requestBuilder.uri(URI.create(DEV_URL + endpoint)).build();
			return client.send(request, HttpResponse.BodyHandlers.ofString());
		}
	}
	public static Player login(String handle, String password) {
		try {
			ObjectNode jsonNode = mapper.createObjectNode();
			jsonNode.put("handle", handle);
			jsonNode.put("password", password);
			String json = mapper.writeValueAsString(jsonNode);
			HttpRequest.Builder requestBuilder =
				HttpRequest.newBuilder()
					.header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(json))
					.timeout(Duration.ofSeconds(6));
			HttpResponse<String> response = sendWithFallback(requestBuilder, "/login");
			if (response.statusCode() == 200)
				return mapper.readValue(response.body(), Player.class);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}
	public static boolean register(Player player) {
		try {
			String json = mapper.writeValueAsString(player);
			HttpRequest.Builder requestBuilder =
				HttpRequest.newBuilder()
					.header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(json))
					.timeout(Duration.ofSeconds(6));
			HttpResponse<String> response = sendWithFallback(requestBuilder, "/register");
			return response.statusCode() == 200;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}
}