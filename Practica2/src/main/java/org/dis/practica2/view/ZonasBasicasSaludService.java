package org.dis.practica2.view;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.dis.practica2.model.ZonasBasicasSalud;
import org.dis.practica2.model.data.ZonasBasicasSaludData;

import org.dis.practica2.model.ZonasBasicasSalud2;
import org.dis.practica2.model.data.ZonasBasicasSaludData2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class ZonasBasicasSaludService implements Serializable {

    public List<ZonasBasicasSalud> leeCasos() throws URISyntaxException, IOException, InterruptedException {
        ZonasBasicasSaludAPI api = new ZonasBasicasSaludAPI();
        String resultsAPI = api.getCasos();
        //Gson gson = new Gson();

        ZonasBasicasSaludData listaZonasBasicasSalud = new Gson().fromJson(resultsAPI, ZonasBasicasSaludData.class);

        return listaZonasBasicasSalud.getData();
    }



    public List<ZonasBasicasSalud2> leeCasos2() throws URISyntaxException, IOException, InterruptedException {
        ZonasBasicasSaludAPI api = new ZonasBasicasSaludAPI();
        String resultsAPI = api.getCasos2();
        //Gson gson = new Gson();

        ZonasBasicasSaludData2 listaZonasBasicasSalud2 = new Gson().fromJson(resultsAPI, new TypeToken<ZonasBasicasSaludData2>() {

            //ZonasBasicasSaludData2 lista = gson.fromJson(resultsAPI, new TypeToken<ZonasBasicasSaludData2>() {
        }.getType());
        return listaZonasBasicasSalud2.getData();
    }

}
