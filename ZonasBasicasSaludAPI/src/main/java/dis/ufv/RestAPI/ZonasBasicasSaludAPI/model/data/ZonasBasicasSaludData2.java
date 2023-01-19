package dis.ufv.RestAPI.ZonasBasicasSaludAPI.model.data;

import dis.ufv.RestAPI.ZonasBasicasSaludAPI.model.ZonasBasicasSalud2;

import java.util.List;

public class ZonasBasicasSaludData2 {
    List<ZonasBasicasSalud2> data;

    public ZonasBasicasSaludData2(List<ZonasBasicasSalud2> data) {
        this.data = data;
    }

    public List<ZonasBasicasSalud2> getData() {
        return data;
    }

    public void setData(List<ZonasBasicasSalud2> data) {
        this.data = data;
    }
}
