package org.dis.salud.view;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.dis.salud.model.ZonasBasicasSalud;
import org.dis.salud.model.data.ZonasBasicasSaludData;

import org.dis.salud.model.ZonasBasicasSalud2;
import org.dis.salud.model.data.ZonasBasicasSaludData2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ZonasBasicasSaludService implements Serializable {

    private static final String urlPrefix = "http://localhost:8080/";

    public List<ZonasBasicasSalud> leeCasos() throws URISyntaxException, IOException, InterruptedException {
        ZonasBasicasSaludAPI api = new ZonasBasicasSaludAPI();
        String resultsAPI = api.getCasos();
        //Gson gson = new Gson();

        //ZonasBasicasSaludData listaZonasBasicasSalud = new Gson().fromJson(resultsAPI, ZonasBasicasSaludData.class);

        List<ZonasBasicasSalud> listaZonasBasicasSalud = new Gson().fromJson(resultsAPI, new TypeToken<List<ZonasBasicasSalud>>() {
        }.getType());;

        return listaZonasBasicasSalud;
    }



    public List<ZonasBasicasSalud2> leeCasos2() throws URISyntaxException, IOException, InterruptedException {
        ZonasBasicasSaludAPI api = new ZonasBasicasSaludAPI();
        String resultsAPI = api.getCasos2();

        List<ZonasBasicasSalud2> listaZonasBasicasSalud2 = new Gson().fromJson(resultsAPI, new TypeToken<List<ZonasBasicasSalud2>>() {
        }.getType());;
        return listaZonasBasicasSalud2;
    }



    public ZonasBasicasSaludData Post(ZonasBasicasSalud salud) throws URISyntaxException, IOException, InterruptedException {
        ZonasBasicasSaludAPI api = new ZonasBasicasSaludAPI();
        ZonasBasicasSaludData updatedSalud = api.PostCaso(salud);
        return updatedSalud;
    }

/*
    public ZonasBasicasSaludData Put(ZonasBasicasSalud salud, String codigo) throws URISyntaxException, IOException, InterruptedException {
        ZonasBasicasSaludAPI api = new ZonasBasicasSaludAPI();
        ZonasBasicasSaludData updatedSalud = api.PutCaso(salud, codigo);
        return updatedSalud;
    }


 */

    public List<ZonasBasicasSalud> Put(ZonasBasicasSalud salud, String codigo) throws URISyntaxException, IOException, InterruptedException {
        ZonasBasicasSaludAPI api = new ZonasBasicasSaludAPI();
        List<ZonasBasicasSalud> updatedSalud = api.PutCaso(salud, codigo);
        return updatedSalud;
    }




}




/*
    public CompletableFuture<ZonasBasicasSalud> put(String codigo,ZonasBasicasSalud salud) throws URISyntaxException, IOException, InterruptedException {
        //ZonasBasicasSalud salud = new ZonasBasicasSalud();
        ZonasBasicasSaludAPI api = new ZonasBasicasSaludAPI();
        salud = api.PutCaso(salud,codigo);
        return CompletableFuture.completedFuture(salud);
    }

 */
/*
    public CompletableFuture<ZonasBasicasSalud> put(String codigo,ZonasBasicasSalud salud) throws URISyntaxException, IOException, InterruptedException {
        //ZonasBasicasSalud salud = new ZonasBasicasSalud();
        return CompletableFuture.supplyAsync(() -> {
        ZonasBasicasSaludAPI api = new ZonasBasicasSaludAPI();

        ZonasBasicasSalud devolver = new ZonasBasicasSalud();
            try {
                devolver = api.putCaso(salud,codigo);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return devolver;
        });
    }

 */
/*
    public ZonasBasicasSalud put(ZonasBasicasSalud salud, String codigo) throws URISyntaxException, IOException, InterruptedException {
        ZonasBasicasSaludAPI api = new ZonasBasicasSaludAPI();
        ZonasBasicasSalud updatedSalud = api.PutCaso(salud, codigo);
        return updatedSalud;
    }
*/
    /*
    public CompletableFuture<ZonasBasicasSalud> put(ZonasBasicasSalud updatedZonaBasicaSalud, String codigo) throws URISyntaxException, IOException, InterruptedException {
        ZonasBasicasSaludAPI api = new ZonasBasicasSaludAPI();
        ZonasBasicasSalud zonaBasicaSalud = api.PutCaso(updatedZonaBasicaSalud, codigo);
        return CompletableFuture.completedFuture(zonaBasicaSalud);
    }
*/
/*
    public ZonasBasicasSalud put(ZonasBasicasSalud salud, String codigo) throws URISyntaxException, IOException, InterruptedException {

        // Creo la URI con el codigo de geometria
        URI uri = new URI(urlPrefix+"ZonasBasicasSalud/"+codigo);
        // Creo un objeto Gson para convertir el objeto a json
        Gson gson = new Gson();
        String json = gson.toJson(salud);
        // Creo el cuerpo de la peticion
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .setHeader("Content-Type", "application/json")
                .build();
        // Envio la peticion y recibo la respuesta
        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        // Si la respuesta es OK devuelvo el objeto actualizado
        if(response.statusCode()==200) {
            return gson.fromJson(response.body(), ZonasBasicasSalud.class);
        }else {
            // Si no, lanzo una excepcion
            throw new RuntimeException("Error al actualizar el objeto");
        }
    }

 */