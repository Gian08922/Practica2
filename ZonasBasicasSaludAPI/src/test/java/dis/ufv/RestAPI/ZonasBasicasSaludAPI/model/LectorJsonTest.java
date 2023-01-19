package dis.ufv.RestAPI.ZonasBasicasSaludAPI.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LectorJsonTest {

    /*
        Con este test probamos el funcionamiento de las clases de datos
        y que se esté leyendo y almacenando bien el Json. Así como que
        tenga el tamaño adecuado y no de errores de almacenamiento.
    */
    @Test
    public void testLeeFicheroJson() {

        LectorJson lector = new LectorJson();
        List<ZonasBasicasSalud> listaZonasBasicasSalud = lector.leeFicheroJson();

        assertEquals(listaZonasBasicasSalud.size(), 36322);
        assertEquals(listaZonasBasicasSalud.get(0).getCodigo_geometria(), "001");
        assertEquals(listaZonasBasicasSalud.get(0).getZona_basica_salud(), "Abrantes");
        assertEquals((double)listaZonasBasicasSalud.get(0).getTasa_incidencia_acumulada_ultimos_14dias(), 3.252243995666504);
        assertEquals((double)listaZonasBasicasSalud.get(0).getTasa_incidencia_acumulada_total(), 1014.7001342773438);
        assertEquals(listaZonasBasicasSalud.get(0).getCasos_confirmados_totales(), 312);
        assertEquals(listaZonasBasicasSalud.get(0).getFecha_informe(), "2020/07/01 09:00:00");

    }

    @Test
    public void testLeeFicheroJson2() {
        LectorJson lector = new LectorJson();
        List<ZonasBasicasSalud2> listaZonasBasicasSalud2 = lector.leeFicheroJson2();

        //Test con valores válidos
        assertEquals(listaZonasBasicasSalud2.size(), 10010);
        assertEquals(listaZonasBasicasSalud2.get(0).getCodigo_geometria(), "001");
        assertEquals(listaZonasBasicasSalud2.get(0).getZona_basica_salud(), "Abrantes");
        assertEquals((double)listaZonasBasicasSalud2.get(0).getTasa_incidencia_acumulada_P60mas_ultimos_14dias(), 182.14999389648438);
        assertEquals((double)listaZonasBasicasSalud2.get(0).getCasos_confirmados_P60mas_ultimos_14dias(), 13);
        assertEquals(listaZonasBasicasSalud2.get(0).getFecha_informe(), "2022/11/29 10:47:00");

    }


    /*
        Este test prueba no solo el funcionamiento de editar sino de escribir,
        por lo que estariamos cubriendo la clase LectorJson al 100%
     */

    @Test
    public void testEditarEscribir() throws IOException {
        ZonasBasicasSalud nuevo = new ZonasBasicasSalud("001", "Zona 1-Editada", (float)3.252243995666504, (float)1014.7001342773438, 312, "2020/07/01 09:00:00");

        LectorJson lector = new LectorJson();
        lector.editar(nuevo, "001");

        List<ZonasBasicasSalud> result = lector.leeFicheroJson();

        assertEquals(result.size(), 36322);
        assertEquals(result.get(0).getCodigo_geometria(), "001");
        assertEquals(result.get(0).getZona_basica_salud(), "Zona 1-Editada");

    }

    @Test
    public void testEditarEscribirMayores60() throws IOException {
        ZonasBasicasSalud2 nuevo = new ZonasBasicasSalud2("001", "Zona 1-Editada", (float)182.14999389648438, (float)13, "2022/11/29 10:47:00");

        LectorJson lector = new LectorJson();
        lector.editar2(nuevo, "001");

        List<ZonasBasicasSalud2> result = lector.leeFicheroJson2();

        assertEquals(result.size(), 10010);
        assertEquals(result.get(0).getCodigo_geometria(), "001");
        assertEquals(result.get(0).getZona_basica_salud(), "Zona 1-Editada");

    }

}