package org.dis.salud.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.dis.salud.model.ZonasBasicasSalud;
import org.dis.salud.model.data.ZonasBasicasSaludData2;
import org.dis.salud.model.ZonasBasicasSalud2;

import org.dis.salud.model.data.ZonasBasicasSaludData;
import org.dis.salud.view.GreetService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 * <p>
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 * Author:Gian08922
 */
@Route
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

        Button boton1 = new Button("Mostrar",
                e -> {
                    String tipoPeticion = comboBox.getValue();
                    String dato = datos.getValue();
                    try {
                        results.removeAll();
                        System.err.println("Entro en el try");
                        if (tipoPeticion.equals("Todos los casos")){
                            System.err.println("Entro en el if");
                            /*
                            List<ZonasBasicasSalud> lista = service.leeCasos();
                            grid.setItems(lista);
                             */
                            grid.setItems(service.leeCasos());
                            results.add(grid);
                        }
                        else if (tipoPeticion.equals("Todos los casos de mayores de 60")){
                            grid2.setItems(service.leeCasos2());
                            results.add(grid2);
                        }
                    } catch (Exception ex) {
                        System.err.println("Entro en el catch");
                    }
                });

        boton1.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        boton1.addClickShortcut(Key.ENTER);

        datos.addThemeName("bordered");
        inputs.add(comboBox, datos);

        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        addClassName("left-content");
        add(inputs, boton1, results, grid, grid2);
    }




}


/*
        HorizontalLayout inputs = new HorizontalLayout();
        VerticalLayout results = new VerticalLayout();



        ComboBox<String> comboBox = new ComboBox<>("Selecciona uno...");
        comboBox.setAllowCustomValue(false); //este deja que el usuario escriba lo que quiera en la caja del comboBox. Si se pone a false no deja
        comboBox.setItems("Todos los campos");
        comboBox.setHelperText("Selecciona el tipo de petición");


        Grid<ZonasBasicasSalud> grid = new Grid<>(ZonasBasicasSalud.class, true);

        //declaro las columnas del grid
        grid.addColumn(ZonasBasicasSalud::getCodigo_geometria).setHeader("Codigo Geometria");
        grid.addColumn(ZonasBasicasSalud::getCasos_confirmados_totales).setHeader("Casos Confirmados");
        grid.addColumn(ZonasBasicasSalud::getFecha_informe).setHeader("Fecha del informe");
        grid.addColumn(ZonasBasicasSalud::getZona_basica_salud).setHeader("Zona basica de salud");
        grid.addColumn(ZonasBasicasSalud::getTasa_incidencia_acumulada_total).setHeader("Incidencias acumuladas totales");
        grid.addColumn(ZonasBasicasSalud::getTasa_incidencia_acumulada_ultimos_14dias).setHeader("Incidencias acumuladas en los ultimos 14 dias");



        Button boton1 = new Button("Lee caracter",

                e -> {

                    String tipoPeticion = comboBox.getValue();



                    try {
                        results.removeAll();
                        if (tipoPeticion.equals("Todos los campos")){

                            grid.setItems(service.leeCasos());
                            results.add(grid);


                        }
                    } catch (Exception ex) {

                    }
                });


        //temas para el boton, usando boostrap
        boton1.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        boton1.addClickShortcut(Key.ENTER);          // Use custom CSS classes to apply styling. This is defined in shared-styles.css.

        addClassName("centered-content");

        inputs.add(comboBox);
        results.setWidth("1500px");

        add(inputs,boton1,results);

    }

 */