package org.dis.practica2.view;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ZonasBasicasSaludAPI {
    private static final String urlPrefix = "http://localhost:8080/%s/%s";

    public String getCasos() throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix, "ZonasBasicasSalud","");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        return response.body();
    }

    public String getCasos2() throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix, "ZonasBasicasSalud2","");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        return response.body();
    }
}
