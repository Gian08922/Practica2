package dis.ufv.RestAPI.ZonasBasicasSaludAPI.model;

import dis.ufv.RestAPI.ZonasBasicasSaludAPI.model.data.ZonasBasicasSaludData;
import dis.ufv.RestAPI.ZonasBasicasSaludAPI.model.data.ZonasBasicasSaludData2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.rmi.ServerException;
import java.util.List;
/*
@RestController
public class ZonaBasicasSaludController {
    @GetMapping("/ZonasBasicasSalud")
    public ArrayList<ZonasBasicasSalud> ZonaBasicasSalud() {
        ArrayList<ZonasBasicasSalud> listaZonasBasicasSalud = new LectorJson().leeFicheroJson("./src/main/resources/Covid19-TIA_ZonasBásicasSalud.json");
        return listaZonasBasicasSalud;
    }

    @GetMapping("/ZonasBasicasSalud2")
    public ArrayList<ZonasBasicasSalud2> ZonaBasicasSalud2() {
        ArrayList<ZonasBasicasSalud2> listaZonasBasicasSalud2 = new LectorJson().leeFicheroJson2("./src/main/resources/Covid19-TIA_ZonasBásicasSalud_Mayores60.json");
        return listaZonasBasicasSalud2;    }

}

*/

@RestController
public class ZonaBasicasSaludController {

    @GetMapping("/ZonasBasicasSalud")
    public List<ZonasBasicasSalud> ZonaBasicasSalud() {
        return new LectorJson().leeFicheroJson();
    }

    @GetMapping("/ZonasBasicasSalud2")
    public List<ZonasBasicasSalud2> ZonasBasicasSalud2() {
        return new LectorJson().leeFicheroJson2();
    }



    @PostMapping(path = "ZonasBasicasSalud", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ZonasBasicasSalud>> create(@RequestBody ZonasBasicasSalud Salud) {

        LectorJson json = new LectorJson();
        ZonasBasicasSaludData lista = json.añadir(Salud);

        if (lista == null) {
            throw new RuntimeException();
        }
        else {
            return new ResponseEntity<>(lista.getData(), HttpStatus.CREATED);
        }
    }

    @PutMapping(path = "/ZonasBasicasSalud/{codigo_geometria}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ZonasBasicasSalud>> edit(@PathVariable("codigo_geometria") String codigo, @RequestBody ZonasBasicasSalud Salud){

        LectorJson json = new LectorJson();

        ZonasBasicasSaludData lista = json.editar(Salud, codigo);

        if (lista == null) {
            throw new RuntimeException();
        }
        else {
            return new ResponseEntity<>(lista.getData(), HttpStatus.OK);
        }

    }







    @PostMapping(path = "ZonasBasicasSalud2", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ZonasBasicasSalud2>> create(@RequestBody ZonasBasicasSalud2 Salud) {

        LectorJson json = new LectorJson();
        ZonasBasicasSaludData2 lista = json.añadir2(Salud);

        if (lista == null) {
            throw new RuntimeException();
        }
        else {
            return new ResponseEntity<>(lista.getData(), HttpStatus.CREATED);
        }
    }

    @PutMapping(path = "/ZonasBasicasSalud2/{codigo_geometria}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ZonasBasicasSalud2>> edit(@PathVariable("codigo_geometria") String codigo, @RequestBody ZonasBasicasSalud2 Salud){

        LectorJson json = new LectorJson();

        ZonasBasicasSaludData2 lista = json.editar2(Salud, codigo);

        if (lista == null) {
            throw new RuntimeException();
        }
        else {
            return new ResponseEntity<>(lista.getData(), HttpStatus.OK);
        }

    }

/*
    @PutMapping(path = "/ZonasBasicasSalud/{codigo_geometria}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ZonasBasicasSalud>> edit(@PathVariable("codigo_geometria") String codigo, @RequestBody ZonasBasicasSalud Salud){

        LectorJson json = new LectorJson();
        ZonasBasicasSaludData lista = json.editar(Salud, codigo);

        if (lista == null) {
            throw new RuntimeException();
        }
        else {
            return new ResponseEntity<>(lista.getData(), HttpStatus.OK);
        }

    }
*/

}