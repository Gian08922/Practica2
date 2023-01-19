package org.dis.salud.view;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.dis.salud.model.ZonasBasicasSalud;
import org.dis.salud.model.ZonasBasicasSalud2;
import org.dis.salud.model.data.ZonasBasicasSaludData;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

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



    public List<ZonasBasicasSalud> PostCaso(ZonasBasicasSalud salud) throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix, "ZonasBasicasSalud","");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .setHeader("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(salud)))
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        try{
            Type listType = new TypeToken<List<ZonasBasicasSalud>>(){}.getType();
            //List<ZonasBasicasSalud> lista = Collections.singletonList(new Gson().fromJson(response.body(), listType));
            List<ZonasBasicasSalud> lista = new Gson().fromJson(response.body(), listType);

            return lista;
        }catch(JsonSyntaxException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<ZonasBasicasSalud> PutCaso(ZonasBasicasSalud salud, String codigo) throws URISyntaxException, IOException, InterruptedException {
        //String fullUrl = urlPrefix + "/ZonasBasicasSalud/" + codigo;
        String fullUrl = String.format(urlPrefix, "ZonasBasicasSalud",codigo);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .setHeader("Content-type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(new Gson().toJson(salud)))
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        try{
            Type listType = new TypeToken<List<ZonasBasicasSalud>>(){}.getType();
            //List<ZonasBasicasSalud> lista = Collections.singletonList(new Gson().fromJson(response.body(), listType));
            List<ZonasBasicasSalud> lista = new Gson().fromJson(response.body(), listType);

            return lista;
        }catch(JsonSyntaxException e){
            e.printStackTrace();
            return null;
        }

    }







    public List<ZonasBasicasSalud2> PostCaso2(ZonasBasicasSalud2 salud) throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix, "ZonasBasicasSalud2","");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .setHeader("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(salud)))
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        try{
            Type listType = new TypeToken<List<ZonasBasicasSalud2>>(){}.getType();
            //List<ZonasBasicasSalud2> lista = Collections.singletonList(new Gson().fromJson(response.body(), listType));
            List<ZonasBasicasSalud2> lista = new Gson().fromJson(response.body(), listType);

            return lista;
        }catch(JsonSyntaxException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<ZonasBasicasSalud2> PutCaso2(ZonasBasicasSalud2 salud, String codigo) throws URISyntaxException, IOException, InterruptedException {
        //String fullUrl = urlPrefix + "/ZonasBasicasSalud/" + codigo;
        String fullUrl = String.format(urlPrefix, "ZonasBasicasSalud2",codigo);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .setHeader("Content-type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(new Gson().toJson(salud)))
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        try{
            Type listType = new TypeToken<List<ZonasBasicasSalud2>>(){}.getType();
            //List<ZonasBasicasSalud2> lista = Collections.singletonList(new Gson().fromJson(response.body(), listType));
            List<ZonasBasicasSalud2> lista = new Gson().fromJson(response.body(), listType);

            return lista;
        }catch(JsonSyntaxException e){
            e.printStackTrace();
            return null;
        }

    }



}





/*
    public ZonasBasicasSalud PutCaso(ZonasBasicasSalud salud, String codigo) throws URISyntaxException, IOException, InterruptedException {
        //String fullUrl = urlPrefix + "/ZonasBasicasSalud/" + codigo;
        String fullUrl = String.format(urlPrefix, "ZonasBasicasSalud",codigo);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .setHeader("Content-type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(new Gson().toJson(salud)))
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        try{
            Type listType = new TypeToken<ZonasBasicasSalud>(){}.getType();
            ZonasBasicasSalud lista = new Gson().fromJson(response.body(), listType);
            return lista;
        }catch(JsonSyntaxException e){
            e.printStackTrace();
            return null;
        }

    }


 */












    /*
    public ZonasBasicasSaludData PutCaso(ZonasBasicasSalud salud, String codigo) throws URISyntaxException, IOException, InterruptedException {
        //String fullUrl = urlPrefix + "/ZonasBasicasSalud/" + codigo;
        String fullUrl = String.format(urlPrefix, "ZonasBasicasSalud",codigo);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .setHeader("Content-type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(new Gson().toJson(salud)))
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        try {
            JsonElement jsonElement = new JsonParser().parse(response.body());
            if (jsonElement.isJsonArray()) {
                // manejar respuesta como arreglo
                ZonasBasicasSaludData lista = new Gson().fromJson(response.body(), ZonasBasicasSaludData.class);
                return lista;
            } else if (jsonElement.isJsonObject()) {
                // manejar respuesta como objeto
                ZonasBasicasSalud updatedSalud = new Gson().fromJson(response.body(), ZonasBasicasSalud.class);
                return new ZonasBasicasSaludData(Arrays.asList(updatedSalud));
            } else {
                // manejar respuesta inesperada
                return null;
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }

    }

     */









/*
    public ZonasBasicasSaludData PostCaso(ZonasBasicasSalud salud) throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix, "ZonasBasicasSalud","");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                //.setHeader("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(salud)))
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        ZonasBasicasSaludData lista = new Gson().fromJson(response.body(), ZonasBasicasSaludData.class);
        return lista;
    }



 */



/*
    public ZonasBasicasSaludData PutCaso(ZonasBasicasSalud salud, String codigo) throws URISyntaxException, IOException, InterruptedException {
        //String fullUrl = urlPrefix + "/ZonasBasicasSalud/" + codigo;
        String fullUrl = String.format(urlPrefix, "ZonasBasicasSalud",codigo);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                //.setHeader("Content-type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(new Gson().toJson(salud)))
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        try{
            ZonasBasicasSaludData lista = new Gson().fromJson(response.body(), ZonasBasicasSaludData.class);
            return lista;
        }catch(JsonSyntaxException e){
            e.printStackTrace();
            return null;
        }

    }



 */








/*
    public String PostCaso() throws URISyntaxException, IOException, InterruptedException {
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
*/


/*
    public ZonasBasicasSalud putCaso(ZonasBasicasSalud salud,String codigo) throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix, "ZonasBasicasSalud/%s",codigo);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        salud = gson.fromJson(response.body(), ZonasBasicasSalud.class);
        return salud;
    }
*/
/*
    public void putCaso(ZonasBasicasSalud salud,String codigo) throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix, "ZonasBasicasSalud/%s",codigo);
        //String fullUrl = String.format("http://localhost:8080/ZonasBasicasSalud/%s", codigo);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .PUT(HttpRequest.BodyPublishers.ofString(new Gson().toJson(salud)))
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

    }


 */


    /*
    public ZonasBasicasSaludData PostCaso(ZonasBasicasSalud salud) throws IOException {
        URL url = new URL(urlPrefix + "ZonasBasicasSalud");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        Gson gson = new Gson();
        String json = gson.toJson(salud);

        OutputStream os = con.getOutputStream();
        os.write(json.getBytes());
        os.flush();
        os.close();

        int responseCode = con.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_CREATED) {
            ZonasBasicasSaludData lista = new Gson().fromJson(json, ZonasBasicasSaludData.class);
            return lista;
        } else {
            return null;
        }


    }



     */