package org.dis.practica2.model.data;

import org.dis.practica2.model.ZonasBasicasSalud;

import java.util.List;

public class ZonasBasicasSaludData  {
    List<ZonasBasicasSalud> data;

    public ZonasBasicasSaludData(List<ZonasBasicasSalud> data) {
        this.data = data;
    }

    public List<ZonasBasicasSalud> getData() {
        return data;
    }

    public void setData(List<ZonasBasicasSalud> data) {
        this.data = data;
    }
}
