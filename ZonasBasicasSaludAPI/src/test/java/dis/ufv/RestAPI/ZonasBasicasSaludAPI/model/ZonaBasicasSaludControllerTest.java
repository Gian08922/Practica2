package dis.ufv.RestAPI.ZonasBasicasSaludAPI.model;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ZonaBasicasSaludControllerTest {

    @Test
    void zonaBasicasSalud() {
        //Test sencillo, comprobamos si el json ZonaBasicasSalud contiene algún objeto
        ZonaBasicasSaludController controller = new ZonaBasicasSaludController();

        List<ZonasBasicasSalud> result = controller.ZonaBasicasSalud();

        assertNotNull(result);
        assertTrue(result.size() > 0);
    }

    @Test
    void zonasBasicasSalud2() {
        //Test sencillo, comprobamos si el json ZonaBasicasSaludMayores60 contiene algún objeto
        ZonaBasicasSaludController controller = new ZonaBasicasSaludController();

        List<ZonasBasicasSalud2> result = controller.ZonasBasicasSalud2();

        assertNotNull(result);
        assertTrue(result.size() > 0);
    }

    @Test
    void create() {
        //CREO un objeto en la lista y le asigna el valor "101" a codigo_geometria
        // y también el valor de Zona_basica_salud a "Test Zona 1" en el json de ZonaBasicasSalud
        ZonaBasicasSaludController controller = new ZonaBasicasSaludController();
        ZonasBasicasSalud newSalud = new ZonasBasicasSalud();
        newSalud.setCodigo_geometria(String.valueOf(101));
        newSalud.setZona_basica_salud("Test Zona 1");

        ResponseEntity<List<ZonasBasicasSalud>> result = controller.create(newSalud);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    void edit() {
        //Edito TODOS los objetos de la lista que poseen el codigo geometria "101"
        // y edito el valor de Zona_basica_salud a "Updated Test Zona" en el json de ZonaBasicasSalud
        ZonaBasicasSaludController controller = new ZonaBasicasSaludController();
        ZonasBasicasSalud updateSalud = new ZonasBasicasSalud();
        updateSalud.setCodigo_geometria(String.valueOf(101));
        updateSalud.setZona_basica_salud("Updated Test Zona");

        ResponseEntity<List<ZonasBasicasSalud>> result = controller.edit("101", updateSalud);

        assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    void testCreate() {
        //CREO un objeto en la lista y le asigna el valor "101" a codigo geometria
        // y también el valor de Zona_basica_salud a "Test Zona 2" en el json de ZonaBasicasSaludMayores60
        ZonaBasicasSaludController controller = new ZonaBasicasSaludController();
        ZonasBasicasSalud2 newSalud = new ZonasBasicasSalud2();
        newSalud.setCodigo_geometria(String.valueOf(101));
        newSalud.setZona_basica_salud("Test Zona 2");

        ResponseEntity<List<ZonasBasicasSalud2>> result = controller.create(newSalud);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    void testEdit() {
        //Edito TODOS los objetos de la lista que poseen el codigo_geometria "101"
        // y edito el valor de Zona_basica_salud a "Updated Test Zona" en el json de ZonaBasicasSaludMayores60
        ZonaBasicasSaludController controller = new ZonaBasicasSaludController();
        ZonasBasicasSalud2 updateSalud = new ZonasBasicasSalud2();
        updateSalud.setCodigo_geometria(String.valueOf(101));
        updateSalud.setZona_basica_salud("Updated Test Zona");

        ResponseEntity<List<ZonasBasicasSalud2>> result = controller.edit("101", updateSalud);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}