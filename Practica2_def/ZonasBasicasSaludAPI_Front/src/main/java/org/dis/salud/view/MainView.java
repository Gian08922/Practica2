package org.dis.salud.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.dis.salud.model.ZonasBasicasSalud;
import org.dis.salud.model.data.ZonasBasicasSaludData2;
import org.dis.salud.model.ZonasBasicasSalud2;

import org.dis.salud.model.data.ZonasBasicasSaludData;
import org.dis.salud.view.GreetService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
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

    private Span status;
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
        results.setWidth("1000px");

        HorizontalLayout input2 = new HorizontalLayout();
        VerticalLayout result2 = new VerticalLayout();
        result2.setWidth("1000px");


        HorizontalLayout dialogo=new HorizontalLayout();
        FormLayout formulario=new FormLayout();

        HorizontalLayout dialogo2=new HorizontalLayout();
        FormLayout formulario2=new FormLayout();

        FormLayout nuevaZonaSalud=new FormLayout();
        FormLayout nuevaZonaSalud2=new FormLayout();





        Grid<ZonasBasicasSalud> grid = new Grid<>(ZonasBasicasSalud.class, false);



        grid.addColumn(ZonasBasicasSalud::getCodigo_geometria).setHeader("Codigo de geometria");
        grid.addColumn(ZonasBasicasSalud::getZona_basica_salud).setHeader("Zona Basica Salud");
        grid.addColumn(ZonasBasicasSalud::getTasa_incidencia_acumulada_ultimos_14dias).setHeader("Tasa de incidencia acumulada en los ultimos 14 dias");
        grid.addColumn(ZonasBasicasSalud::getTasa_incidencia_acumulada_total).setHeader("Tasa de incidencia acumulada total");
        grid.addColumn(ZonasBasicasSalud::getCasos_confirmados_totales).setHeader("Casos confirmados totales");
        grid.addColumn(ZonasBasicasSalud::getFecha_informe).setHeader("Fecha de informe");

        grid.addItemDoubleClickListener(e->{


            status = new Span();
            status.setVisible(false);
            formulario.removeAll();


            Dialog dialog = new Dialog();

            TextField codigoDeGeometria = new TextField("Codigo de geometria");
            TextField zonaBasicaSalud = new TextField("Zona Basica Salud");
            TextField tasaincidencia14 = new TextField("Tasa de incidencia acumulada en los ultimos 14 dias");
            TextField tasainicidenciatotal = new TextField("Tasa de incidencia acumulada total");
            TextField casosConfirmadosTotales = new TextField("Casos confirmados totales");
            TextField fechaDeInforme = new TextField("Fecha de informe");

            ZonasBasicasSalud fila = e.getItem();
            ZonasBasicasSalud modificado = new ZonasBasicasSalud();


            codigoDeGeometria.setValue(fila.getCodigo_geometria());
            codigoDeGeometria.setEnabled(false);
            zonaBasicaSalud.setValue(fila.getZona_basica_salud());
            tasaincidencia14.setValue(String.valueOf(fila.getTasa_incidencia_acumulada_ultimos_14dias()));
            tasainicidenciatotal.setValue(String.valueOf(fila.getTasa_incidencia_acumulada_total()));
            casosConfirmadosTotales.setValue(String.valueOf(fila.getCasos_confirmados_totales()));
            fechaDeInforme.setValue(fila.getFecha_informe());

            modificado.setCodigo_geometria(codigoDeGeometria.getValue());
            modificado.setZona_basica_salud(zonaBasicaSalud.getValue());
            modificado.setTasa_incidencia_acumulada_ultimos_14dias(Float.parseFloat(tasaincidencia14.getValue()));
            modificado.setTasa_incidencia_acumulada_total(Float.parseFloat(tasainicidenciatotal.getValue()));
            modificado.setCasos_confirmados_totales(Integer.parseInt(casosConfirmadosTotales.getValue()));
            modificado.setFecha_informe(fechaDeInforme.getValue());





            formulario.add(codigoDeGeometria,zonaBasicaSalud,tasaincidencia14,tasainicidenciatotal,casosConfirmadosTotales,fechaDeInforme);
            formulario.setResponsiveSteps(
                    // Use one column by default
                    new FormLayout.ResponsiveStep("0", 1),
                    // Use two columns, if layout's width exceeds 500px
                    new FormLayout.ResponsiveStep("500px", 2));
            // Stretch the username field over 2 columns



                dialog.add(formulario);
                Button cancelar= new Button("Cancelar",
                cierre->{
                    dialog.close();
            });
            Button confirmar= new Button("Confirmar",
                    confirmacion->{


                        try {
                            service.Put(modificado, codigoDeGeometria.getValue());
                        } catch (URISyntaxException | IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }

/*
                        ZonasBasicasSaludAPI api = new ZonasBasicasSaludAPI();
                        try {
                            api.putCaso(modificado, codigoDeGeometria.getValue());
                        } catch (URISyntaxException ex) {
                            throw new RuntimeException(ex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }


 */
                        dialog.close();

                    });

                dialog.open();
                status.setVisible(false);

                dialogo.add(status);
                add(dialogo);
                formulario.add(cancelar);
                formulario.add(confirmar);



        });



        Grid<ZonasBasicasSalud2> grid2 = new Grid<>(ZonasBasicasSalud2.class, false);
        grid2.addColumn(ZonasBasicasSalud2::getCodigo_geometria).setHeader("Codigo de geometria");
        grid2.addColumn(ZonasBasicasSalud2::getZona_basica_salud).setHeader("Zona Basica Salud");
        grid2.addColumn(ZonasBasicasSalud2::getTasa_incidencia_acumulada_P60mas_ultimos_14dias).setHeader("Tasa de incidencia acumulada en los ultimos 14 dias");
        grid2.addColumn(ZonasBasicasSalud2::getCasos_confirmados_P60mas_ultimos_14dias).setHeader("Casos confirmados totales");
        grid2.addColumn(ZonasBasicasSalud2::getFecha_informe).setHeader("Fecha de informe");

        grid2.addItemDoubleClickListener(e2->{
            status = new Span();
            status.setVisible(false);
            formulario2.removeAll();


            Dialog dialog = new Dialog();

            TextField codigoDeGeometria = new TextField("Codigo de geometria");
            TextField zonaBasicaSalud = new TextField("Zona Basica Salud");
            TextField tasaincidencia14 = new TextField("Tasa de incidencia acumulada en los ultimos 14 dias");
            TextField tasainicidenciatotal = new TextField("Casos confirmados p60 dias");
            TextField fechaDeInforme = new TextField("Fecha");

            ZonasBasicasSalud2 fila2= e2.getItem();

            codigoDeGeometria.setValue(fila2.getCodigo_geometria());
            codigoDeGeometria.setEnabled(false);
            zonaBasicaSalud.setValue(fila2.getZona_basica_salud());
            tasaincidencia14.setValue(String.valueOf(fila2.getTasa_incidencia_acumulada_P60mas_ultimos_14dias()));
            tasainicidenciatotal.setValue(String.valueOf(fila2.getCasos_confirmados_P60mas_ultimos_14dias()));

            fechaDeInforme.setValue(fila2.getFecha_informe());



            formulario2.add(codigoDeGeometria,zonaBasicaSalud,tasaincidencia14,tasainicidenciatotal,fechaDeInforme);
            formulario2.setResponsiveSteps(
                    // Use one column by default
                    new FormLayout.ResponsiveStep("0", 1),
                    // Use two columns, if layout's width exceeds 500px
                    new FormLayout.ResponsiveStep("500px", 2));
            // Stretch the username field over 2 columns


            dialog.add(formulario2);
            Button cancelar2= new Button("Cancelar",
                    cierre->{
                        dialog.close();
                    });

            Button confirmar2= new Button("Confirmar",
                    cierre->{
                        dialog.close();
                    });

            dialog.add(formulario2);
            dialog.open();
            status.setVisible(false);

            dialogo2.add(status);
            add(dialogo2);
            formulario2.add(cancelar2);
            formulario2.add(confirmar2);

        });




                    try {
                        results.removeAll();
                        System.err.println("Entro en el try");

                            System.err.println("Entro en el if");


                            grid.setItems(service.leeCasos());
                            results.add(grid);


                    } catch (Exception ex) {
                        System.err.println("Entro en el catch");
                    }





        //Boton de la segunda tabla




                    try {
                        result2.removeAll();
                        System.err.println("Entro en el try");

                        System.err.println("Entro en el if");


                        grid2.setItems(service.leeCasos2());
                        result2.add(grid2);

                    } catch (Exception ex) {
                        System.err.println("Entro en el catch");
                    }








        Button crearnuevo = new Button("CrearNuevoObjeto",
                pe-> {
                    status = new Span();
                    status.setVisible(false);
                    nuevaZonaSalud.removeAll();


                    Dialog dialog = new Dialog();

                    TextField codigoDeGeometria = new TextField("Codigo de geometria");
                    TextField zonaBasicaSalud = new TextField("Zona Basica Salud");
                    TextField tasaincidencia14 = new TextField("Tasa de incidencia acumulada en los ultimos 14 dias");
                    TextField tasainicidenciatotal = new TextField("Tasa de incidencia acumulada total");
                    TextField casosConfirmadosTotales = new TextField("Casos confirmados totales");
                    TextField fechaDeInforme = new TextField("Fecha de informe");


                    nuevaZonaSalud.add(codigoDeGeometria,zonaBasicaSalud,tasaincidencia14,tasainicidenciatotal,casosConfirmadosTotales,fechaDeInforme);
                    nuevaZonaSalud.setResponsiveSteps(
                            // Use one column by default
                            new FormLayout.ResponsiveStep("0", 1),
                            // Use two columns, if layout's width exceeds 500px
                            new FormLayout.ResponsiveStep("500px", 2));
                    // Stretch the username field over 2 columns
/*
                    ZonasBasicasSalud fila = pe.getItem();

                    codigoDeGeometria.setValue(fila.getCodigo_geometria());
                    codigoDeGeometria.setEnabled(false);
                    zonaBasicaSalud.setValue(fila.getZona_basica_salud());
                    tasaincidencia14.setValue(String.valueOf(fila.getTasa_incidencia_acumulada_ultimos_14dias()));
                    tasainicidenciatotal.setValue(String.valueOf(fila.getTasa_incidencia_acumulada_total()));
                    casosConfirmadosTotales.setValue(String.valueOf(fila.getCasos_confirmados_totales()));
                    fechaDeInforme.setValue(fila.getFecha_informe());


 */




                    dialog.add(nuevaZonaSalud);
                    Button cancelar= new Button("Cancelar",
                            cierre->{
                                dialog.close();
                            });

                    Button confirmar1= new Button("Confirmar",
                            cierre->{
                                ZonasBasicasSalud modificado = new ZonasBasicasSalud();
                                modificado.setCodigo_geometria(codigoDeGeometria.getValue());
                                modificado.setZona_basica_salud(zonaBasicaSalud.getValue());
                                modificado.setTasa_incidencia_acumulada_ultimos_14dias(Float.parseFloat(tasaincidencia14.getValue()));
                                modificado.setTasa_incidencia_acumulada_total(Float.parseFloat(tasainicidenciatotal.getValue()));
                                modificado.setCasos_confirmados_totales(Integer.parseInt(casosConfirmadosTotales.getValue()));
                                modificado.setFecha_informe(fechaDeInforme.getValue());

                                try {
                                    service.Post(modificado);
                                } catch (URISyntaxException | IOException ex) {
                                    throw new RuntimeException(ex);
                                } catch (InterruptedException ex) {
                                    throw new RuntimeException(ex);
                                }
                                dialog.close();
                            });


                    dialog.add(nuevaZonaSalud);
                    dialog.open();
                    status.setVisible(false);

                    dialogo.add(status);
                    add(dialogo);
                    nuevaZonaSalud.add(cancelar,confirmar1);


                });

        Button crearnuevo2 = new Button("CrearNuevoObjeto",
                pe2-> {
                    status = new Span();
                    status.setVisible(false);
                    nuevaZonaSalud2.removeAll();


                    Dialog dialog = new Dialog();

                    TextField codigoDeGeometria = new TextField("Codigo de geometria");
                    TextField zonaBasicaSalud = new TextField("Zona Basica Salud");
                    TextField tasaincidencia14 = new TextField("Tasa de incidencia acumulada en los ultimos 14 dias");
                    TextField tasainicidenciatotal = new TextField("Casos confirmados p60 dias");
                    TextField fechaDeInforme = new TextField("Fecha");







                    nuevaZonaSalud2.add(codigoDeGeometria,zonaBasicaSalud,tasaincidencia14,tasainicidenciatotal,fechaDeInforme);
                    nuevaZonaSalud2.setResponsiveSteps(
                            // Use one column by default
                            new FormLayout.ResponsiveStep("0", 1),
                            // Use two columns, if layout's width exceeds 500px
                            new FormLayout.ResponsiveStep("500px", 2));
                    // Stretch the username field over 2 columns


                    dialog.add(nuevaZonaSalud2);
                    Button cancelar= new Button("Cancelar",
                            cierre->{
                                dialog.close();
                            });

                    Button confirmar2= new Button("Confirmar",
                            cierre->{
                                dialog.close();
                            });


                    dialog.add(nuevaZonaSalud2);
                    dialog.open();
                    status.setVisible(false);

                    dialogo2.add(status);
                    add(dialogo2);
                    nuevaZonaSalud2.add(cancelar,confirmar2);

                });





        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        addClassName("left-content");

       //add(inputs, boton1,botontabla, results, grid, grid2,crearnuevo);

        TabSheet pestana=new TabSheet();
        pestana.add("Primera tabla",new Div(inputs,results,grid,crearnuevo));


        pestana.add("Segunda tabla",new Div(input2,result2,grid2,crearnuevo2));

        add(pestana);
    }




}



