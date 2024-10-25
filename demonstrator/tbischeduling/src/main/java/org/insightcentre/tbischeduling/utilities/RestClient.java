package org.insightcentre.tbischeduling.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class RestClient {

	private String restUri;
	private Client client;

	public RestClient() {
		restUri = System.getProperty("tbi.rest.uri", "http://4c108.ucc.ie:8080/tbi-ws/api");
		client = buildClient();
	}

	public String ping() {
		Response response = client.target(restUri + "/ping").request().get();
		return response.readEntity(String.class);
	}

	public String solve(String input) {
		Response response = client.target(restUri + "/solve")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(input, MediaType.APPLICATION_JSON));
		return response.readEntity(String.class);
	}

	protected Client buildClient() {
		return ClientBuilder.newClient();
	}

	public static void main(String[] args) {
		try {
			String input = Files.readString(Paths.get("imports", "batch.json"));
			String result = new RestClient().solve(input);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
