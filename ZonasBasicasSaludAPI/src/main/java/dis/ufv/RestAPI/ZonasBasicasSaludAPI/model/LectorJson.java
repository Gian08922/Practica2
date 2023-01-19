package dis.ufv.RestAPI.ZonasBasicasSaludAPI.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dis.ufv.RestAPI.ZonasBasicasSaludAPI.model.ZonasBasicasSalud;
import dis.ufv.RestAPI.ZonasBasicasSaludAPI.model.ZonasBasicasSalud2;
import dis.ufv.RestAPI.ZonasBasicasSaludAPI.model.data.ZonasBasicasSaludData;
import dis.ufv.RestAPI.ZonasBasicasSaludAPI.model.data.ZonasBasicasSaludData2;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class LectorJson {
    public List<ZonasBasicasSalud> leeFicheroJson() {
        try {
            //lee el fichero que le pasamos y lo carga en un reader
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Covid19-TIA_ZonasBásicasSalud.json")));

            // convierte el array JSON a un arraylist de users
            ZonasBasicasSaludData listaZonasBasicasSalud = new Gson().fromJson(reader, new TypeToken<ZonasBasicasSaludData>() {
            }.getType());

            System.out.println("Se muestra el lee ficheroJson");

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

            System.out.println("Se muestra el lee ficheroJson 2");

            reader.close();// close reader
            return listaZonasBasicasSalud2.getData();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>(); //si no ha leido nada, devuelve un array vacio
        }
    }

    public void EscribirJson(ZonasBasicasSaludData lista){

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("./src/main/resources/Covid19-TIA_ZonasBásicasSalud.json"), lista);
            System.out.println("Se escribio el fichero de Zonas Basicas Salud");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fallo a la hora de escribir");
        }

    }

    public void EscribirJson2(ZonasBasicasSaludData2 lista) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("./src/main/resources/Covid19-TIA_ZonasBásicasSalud_Mayores60.json"), lista);
            System.out.println("Se escribio el fichero de Zonas Basicas Salud Mayores de 60");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fallo a la hora de escribir");
        }

    }


    public ZonasBasicasSaludData añadir(ZonasBasicasSalud nuevo) {
        try {
            // Lee el fichero JSON
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Covid19-TIA_ZonasBásicasSalud.json")));
            String jsonString = reader.lines().collect(Collectors.joining());
            reader.close();

            // Convierten el JSON en un string para nuestro objeto ZonasBasicasData
            ZonasBasicasSaludData data = new Gson().fromJson(jsonString, ZonasBasicasSaludData.class);

            // Añadir el nuevo elemeto dato al objeto
            data.getData().add(nuevo);

            EscribirJson(data);

            return data;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public ZonasBasicasSaludData editar(ZonasBasicasSalud nuevo, String codigo) {
        try {
            // Lee el fichero JSON
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Covid19-TIA_ZonasBásicasSalud.json")));
            String jsonString = reader.lines().collect(Collectors.joining());
            reader.close();

            // Convierten el JSON en un string para nuestro objeto ZonasBasicasData
            ZonasBasicasSaludData data = new Gson().fromJson(jsonString, ZonasBasicasSaludData.class);

            // Encontrar el elemento que coincide con codigo-geometria y actualizar valores
            for (ZonasBasicasSalud zona : data.getData()) {
                if (zona.getCodigo_geometria().equals(codigo)) {

                    zona.setZona_basica_salud(nuevo.getZona_basica_salud());
                    zona.setTasa_incidencia_acumulada_ultimos_14dias(nuevo.getTasa_incidencia_acumulada_ultimos_14dias());

                    zona.setTasa_incidencia_acumulada_total(nuevo.getTasa_incidencia_acumulada_total());
                    zona.setCasos_confirmados_totales(nuevo.getCasos_confirmados_totales());
                    zona.setFecha_informe(nuevo.getFecha_informe());
                }
                else
                {
                    zona.setZona_basica_salud(zona.getZona_basica_salud());
                    zona.setTasa_incidencia_acumulada_ultimos_14dias(zona.getTasa_incidencia_acumulada_ultimos_14dias());

                    zona.setTasa_incidencia_acumulada_total(zona.getTasa_incidencia_acumulada_total());
                    zona.setCasos_confirmados_totales(zona.getCasos_confirmados_totales());
                    zona.setFecha_informe(zona.getFecha_informe());
                }
            }

            EscribirJson(data);

            return data;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }








    public ZonasBasicasSaludData2 añadir2(ZonasBasicasSalud2 nuevo) {
        try {
            // Lee el fichero JSON
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Covid19-TIA_ZonasBásicasSalud_Mayores60.json")));
            String jsonString = reader.lines().collect(Collectors.joining());
            reader.close();

            // Convierten el JSON en un string para nuestro objeto ZonasBasicasData
            ZonasBasicasSaludData2 data = new Gson().fromJson(jsonString, ZonasBasicasSaludData2.class);

            // Añadir el nuevo elemeto dato al objeto
            data.getData().add(nuevo);

            EscribirJson2(data);

            return data;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public ZonasBasicasSaludData2 editar2(ZonasBasicasSalud2 nuevo, String codigo) {
        try {
            // Lee el fichero JSON
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Covid19-TIA_ZonasBásicasSalud_Mayores60.json")));
            String jsonString = reader.lines().collect(Collectors.joining());
            reader.close();

            // Convierten el JSON en un string para nuestro objeto ZonasBasicasData
            ZonasBasicasSaludData2 data = new Gson().fromJson(jsonString, ZonasBasicasSaludData2.class);

            // Encontrar el elemento que coincide con codigo-geometria y actualizar valores
            for (ZonasBasicasSalud2 zona : data.getData()) {
                if (zona.getCodigo_geometria().equals(codigo)) {

                    zona.setZona_basica_salud(nuevo.getZona_basica_salud());
                    zona.setTasa_incidencia_acumulada_P60mas_ultimos_14dias(nuevo.getTasa_incidencia_acumulada_P60mas_ultimos_14dias());

                    zona.setCasos_confirmados_P60mas_ultimos_14dias(nuevo.getCasos_confirmados_P60mas_ultimos_14dias());
                    zona.setFecha_informe(nuevo.getFecha_informe());
                }
                else
                {
                    zona.setZona_basica_salud(zona.getZona_basica_salud());
                    zona.setTasa_incidencia_acumulada_P60mas_ultimos_14dias(nuevo.getTasa_incidencia_acumulada_P60mas_ultimos_14dias());

                    zona.setCasos_confirmados_P60mas_ultimos_14dias(zona.getCasos_confirmados_P60mas_ultimos_14dias());
                    zona.setFecha_informe(zona.getFecha_informe());
                }
            }

            EscribirJson2(data);

            return data;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}







/*

    public ZonasBasicasSaludData editar(ZonasBasicasSalud nuevo, String codigo) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Covid19-TIA_ZonasBásicasSalud_Mayores60.json")));
            String jsonString = reader.lines().collect(Collectors.joining());
            reader.close();

            ZonasBasicasSaludData data = new Gson().fromJson(jsonString, ZonasBasicasSaludData.class);

            for (ZonasBasicasSalud zona : data.getData()) {
                if (zona.getCodigo_geometria()== codigo) {

                    zona.setZona_basica_salud(nuevo.getZona_basica_salud());
                    zona.setTasa_incidencia_acumulada_ultimos_14dias(nuevo.getTasa_incidencia_acumulada_ultimos_14dias());

                    zona.setTasa_incidencia_acumulada_total(nuevo.getTasa_incidencia_acumulada_total());
                    zona.setCasos_confirmados_totales(nuevo.getCasos_confirmados_totales());
                    zona.setFecha_informe(nuevo.getFecha_informe());
                    break;
                }
            }

            //Gson gson = new GsonBuilder().setPrettyPrinting().create();
            //jsonString = gson.toJson(data);

            //FileWriter fileWriter = new FileWriter("Covid19-TIA_ZonasBásicasSalud_Mayores60.json");
            //fileWriter.write(jsonString);
            //fileWriter.flush();
            //fileWriter.close();

            EscribirJson(data);

            return data;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

 */




/*
public ZonasBasicasSaludData editar(ZonasBasicasSalud nuevo, String id) {
    try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Covid19-TIA_ZonasBásicasSalud.json")));
        String jsonString = reader.lines().collect(Collectors.joining());
        reader.close();

        Type listType = new TypeToken<ArrayList<ZonasBasicasSalud>>() {}.getType();
        ZonasBasicasSaludData lista = new Gson().fromJson(jsonString, listType);


        for (ZonasBasicasSalud zona : lista.getData()) {
            if (zona.getCodigo_geometria().equals(id)) {
                zona.setZona_basica_salud(nuevo.getZona_basica_salud());
                zona.setTasa_incidencia_acumulada_ultimos_14dias(nuevo.getTasa_incidencia_acumulada_ultimos_14dias());

                zona.setTasa_incidencia_acumulada_total(nuevo.getTasa_incidencia_acumulada_total());
                zona.setCasos_confirmados_totales(nuevo.getCasos_confirmados_totales());
                zona.setFecha_informe(nuevo.getFecha_informe());


                break;
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        jsonString = gson.toJson(lista);

        FileWriter fileWriter = new FileWriter("Covid19-TIA_ZonasBásicasSalud.json");
        fileWriter.write(jsonString);
        fileWriter.flush();
        fileWriter.close();

        EscribirJson(lista);
        return lista;

                } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return null;
                }
                }

 */

/*
    public void añadir(ZonasBasicasSalud nuevo) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Covid19-TIA_ZonasBásicasSalud.json")));
            String jsonString = reader.lines().collect(Collectors.joining());
            reader.close();

            Type listType = new TypeToken<ArrayList<ZonasBasicasSalud>>() {}.getType();
            List<ZonasBasicasSalud> lista = new Gson().fromJson(jsonString, listType);

            lista.add(nuevo);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            jsonString = gson.toJson(lista);

            FileWriter fileWriter = new FileWriter("Covid19-TIA_ZonasBásicasSalud.json");
            fileWriter.write(jsonString);
            fileWriter.flush();
            fileWriter.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void editar(String id, ZonasBasicasSalud nuevo) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Covid19-TIA_ZonasBásicasSalud.json")));
            String jsonString = reader.lines().collect(Collectors.joining());
            reader.close();

            Type listType = new TypeToken<ArrayList<ZonasBasicasSalud>>() {}.getType();
            List<ZonasBasicasSalud> lista = new Gson().fromJson(jsonString, listType);


            for (ZonasBasicasSalud zona : lista) {
                if (zona.getCodigo_geometria().equals(id)) {
                    zona.setZona_basica_salud(nuevo.getZona_basica_salud());
                    zona.setTasa_incidencia_acumulada_ultimos_14dias(nuevo.getTasa_incidencia_acumulada_ultimos_14dias());

                    zona.setTasa_incidencia_acumulada_total(nuevo.getTasa_incidencia_acumulada_total());
                    zona.setCasos_confirmados_totales(nuevo.getCasos_confirmados_totales());
                    zona.setFecha_informe(nuevo.getFecha_informe());


                    break;
                }
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            jsonString = gson.toJson(lista);

            FileWriter fileWriter = new FileWriter("Covid19-TIA_ZonasBásicasSalud.json");
            fileWriter.write(jsonString);
            fileWriter.flush();
            fileWriter.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

 */











/*
    public List<ZonasBasicasSalud> editar(ZonasBasicasSalud nuevo, int codigo){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Covid19-TIA_ZonasBásicasSalud_Mayores60.json")));

            ZonasBasicasSaludData lista = new Gson().fromJson(reader, new TypeToken<ZonasBasicasSaludData>() {
            }.getType());

            lista.editar(codigo);
            lista.añadir(nuevo);


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>(); //si no ha leido nada, devuelve un array vacio
        }
    }

    public List<ZonasBasicasSalud> añadir(ZonasBasicasSalud nuevo){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Covid19-TIA_ZonasBásicasSalud_Mayores60.json")));

            ZonasBasicasSaludData lista = new Gson().fromJson(reader, new TypeToken<ZonasBasicasSaludData>() {
            }.getType());

            lista.añadir(nuevo);


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>(); //si no ha leido nada, devuelve un array vacio
        }
    }
*/
/*
 *Author:Gian08922
 */

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






