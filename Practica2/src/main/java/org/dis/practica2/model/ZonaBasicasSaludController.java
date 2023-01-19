package org.dis.practica2.model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}