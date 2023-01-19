package org.dis.practica2.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.dis.practica2.model.ZonasBasicasSalud;
import org.dis.practica2.model.ZonasBasicasSalud2;
import org.dis.practica2.model.data.ZonasBasicasSaludData;
import org.dis.practica2.model.data.ZonasBasicasSaludData2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class LectorJson {
    public List<ZonasBasicasSalud> leeFicheroJson() {
        try {
            //lee el fichero que le pasamos y lo carga en un reader
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Covid19-TIA_ZonasBásicasSalud.json")));

            // convierte el array JSON a un arraylist de users
            ZonasBasicasSaludData listaZonasBasicasSalud = new Gson().fromJson(reader, new TypeToken<ZonasBasicasSaludData>() {
            }.getType());

            reader.close();// close reader
            return listaZonasBasicasSalud.getData();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>(); //si no ha leido nada, devuelve un array vacio
        }
    }

    public List<ZonasBasicasSalud2> leeFicheroJson2() {
        try {
            //lee el fichero que le pasamos y lo carga en un reader
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Covid19-TIA_ZonasBásicasSalud_Mayores60.json")));

            // convierte el array JSON a un arraylist de users
            ZonasBasicasSaludData2 listaZonasBasicasSalud2 = new Gson().fromJson(reader, new TypeToken<ZonasBasicasSaludData2>() {
            }.getType());

            reader.close();// close reader
            return listaZonasBasicasSalud2.getData();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>(); //si no ha leido nada, devuelve un array vacio
        }
    }
}


/*
public class LectorJson {
    public ArrayList<ZonasBasicasSalud> leeFicheroJson(String fichero){
        try {
            //lee el fichero que le pasemos y lo carga en un reader
            Reader reader = Files.newBufferedReader(Paths.get(fichero));
            // convierte el array JSON a un arraylist de users
            ArrayList<ZonasBasicasSalud> listaZonasBasicasSalud =
                    new Gson().fromJson(reader, new TypeToken<ArrayList<ZonasBasicasSalud>>() {}.getType());
            reader.close();// close reader
            return listaZonasBasicasSalud;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>(); //si no ha leido nada, devuelve un array vacio
        }
    }

    public ArrayList<ZonasBasicasSalud2> leeFicheroJson2(String fichero){
        try {
            //lee el fichero que le pasemos y lo carga en un reader
            Reader reader = Files.newBufferedReader(Paths.get(fichero));
            // convierte el array JSON a un arraylist de users
            ArrayList<ZonasBasicasSalud2> listaZonasBasicasSalud2 =
                    new Gson().fromJson(reader, new TypeToken<ArrayList<ZonasBasicasSalud2>>() {}.getType());
            reader.close();// close reader
            return listaZonasBasicasSalud2;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>(); //si no ha leido nada, devuelve un array vacio
        }
    }
}
*/