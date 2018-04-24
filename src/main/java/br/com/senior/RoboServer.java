package br.com.senior;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import br.com.senior.robo.RoboHandler;

public class RoboServer {

	private static final int SERVER_PORT = 8080;
	public static String ENDPOINT = "/rest/mars/";

	public static void main(String[] args) throws Exception {
		System.out.println("Iniciando Robo...");
		new RoboServer().start();
	}

	private void start() throws Exception {
		HttpServer server = HttpServer.create(new InetSocketAddress(SERVER_PORT), 0);
		server.createContext(ENDPOINT, new RoboHandler(ENDPOINT));
		server.setExecutor(null);
		server.start();		
		System.out.println("Robo Iniciado!");
	}

}
