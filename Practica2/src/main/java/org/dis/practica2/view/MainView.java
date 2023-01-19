package org.dis.practica2.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.dis.practica2.model.ZonasBasicasSalud;
import org.dis.practica2.model.data.ZonasBasicasSaludData;
import org.dis.practica2.model.data.ZonasBasicasSaludData2;
import org.dis.practica2.model.ZonasBasicasSalud2;

import org.dis.practica2.model.data.ZonasBasicasSaludData;
import org.dis.practica2.view.GreetService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed bean.
     */
    public MainView(@Autowired ZonasBasicasSaludService service) {
        HorizontalLayout inputs = new HorizontalLayout();
        VerticalLayout results = new VerticalLayout();

        ComboBox<String> comboBox = new ComboBox<>("Selecciona uno...");
        comboBox.setAllowCustomValue(false); //este deja que el usuario escriba lo que quiera en la caja del comboBox. Si se pone a false no deja
        comboBox.setItems("Todos los casos", "Todos los casos de mayores de 60");
        comboBox.setHelperText("Selecciona el tipo de petición");


        Grid<ZonasBasicasSalud> grid = new Grid<>(ZonasBasicasSalud.class, true);
        grid.addColumn(ZonasBasicasSalud::getCodigo_geometria).setHeader("Codigo de geometria");
        grid.addColumn(ZonasBasicasSalud::getZona_basica_salud).setHeader("Zona Basica Salud");
        grid.addColumn(ZonasBasicasSalud::getTasa_incidencia_acumulada_ultimos_14dias).setHeader("Tasa de incidencia acumulada en los ultimos 14 dias");
        grid.addColumn(ZonasBasicasSalud::getTasa_incidencia_acumulada_total).setHeader("Tasa de incidencia acumulada total");
        grid.addColumn(ZonasBasicasSalud::getCasos_confirmados_totales).setHeader("Casos confirmados totales");
        grid.addColumn(ZonasBasicasSalud::getFecha_informe).setHeader("Fecha de informe");

        Grid<ZonasBasicasSalud2> grid2 = new Grid<>(ZonasBasicasSalud2.class, true);
        grid2.addColumn(ZonasBasicasSalud2::getCodigo_geometria).setHeader("Codigo de geometria");
        grid2.addColumn(ZonasBasicasSalud2::getZona_basica_salud).setHeader("Zona Basica Salud");
        grid2.addColumn(ZonasBasicasSalud2::getTasa_incidencia_acumulada_P60mas_ultimos_14dias).setHeader("Tasa de incidencia acumulada en los ultimos 14 dias");
        grid2.addColumn(ZonasBasicasSalud2::getCasos_confirmados_P60mas_ultimos_14dias).setHeader("Casos confirmados totales");
        grid2.addColumn(ZonasBasicasSalud2::getFecha_informe).setHeader("Fecha de informe");

        TextField datos = new TextField("Nombre");
        datos.addThemeName("bordered");
        inputs.add(comboBox, datos);

        Button boton1 = new Button("Mostrar",
                e -> {
                    String tipoPeticion = comboBox.getValue();
                    String dato = datos.getValue();
                    try {
                        results.removeAll();
                        if (tipoPeticion.equals("Todos los casos")){
                            List<ZonasBasicasSalud> lista = service.leeCasos();
                            grid.setItems(lista);
                            results.add(grid);
                        }
                        else if (tipoPeticion.equals("Todos los casos de mayores de 60")){
                            grid2.setItems(service.leeCasos2());
                            results.add(grid2);
                        }
                    } catch (Exception ex) {
                    }
                });

        boton1.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        boton1.addClickShortcut(Key.ENTER);


        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        addClassName("left-content");
        add(inputs, boton1, results, grid, grid2);
    }

}
