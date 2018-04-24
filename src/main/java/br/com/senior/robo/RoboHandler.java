package br.com.senior.robo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import br.com.senior.robo.exceptions.InvalidRoboStateException;

public class RoboHandler implements HttpHandler {

	private static final String BAD_REQUEST_MESSAGE = "400 Bad Request";
	private final String endpoint;

	public RoboHandler(String endpoint) {
		this.endpoint = endpoint;
	}

	public void handle(HttpExchange request) throws IOException {
		String uri = request.getRequestURI().getPath();
		uri = uri.replace(endpoint, "");
		String response;
		try {
			response = execute(uri, new RoboInMemory());
			request.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());

		} catch (Exception e) {
			response = BAD_REQUEST_MESSAGE;
			request.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, response.length());
		}

		OutputStream os = request.getResponseBody();
		os.write(response.getBytes());
		os.close();

	}

	protected static String execute(String fullCommand, RoboInMemory roboInMemory) throws InvalidRoboStateException {
		if (fullCommand.length() > 0) {
			String command = fullCommand.substring(0, 1);
			String newFullCommand = fullCommand.substring(1, fullCommand.length());
			roboInMemory.execute(command);
			return execute(newFullCommand, roboInMemory);
		}
		return roboInMemory.toString();
	}

}