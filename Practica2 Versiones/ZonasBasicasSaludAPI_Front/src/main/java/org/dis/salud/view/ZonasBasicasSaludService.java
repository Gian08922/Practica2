package org.dis.salud.view;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.dis.salud.model.ZonasBasicasSalud;
import org.dis.salud.model.data.ZonasBasicasSaludData;

import org.dis.salud.model.ZonasBasicasSalud2;
import org.dis.salud.model.data.ZonasBasicasSaludData2;
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

}
