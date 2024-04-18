package api_incidencias.api_incidencias.Pdf;

import java.io.*;

import api_incidencias.api_incidencias.Entidades.Clases.*;
import api_incidencias.api_incidencias.Entidades.Enum.Estado;
import api_incidencias.api_incidencias.Entidades.Enum.ModoResolucion;
import api_incidencias.api_incidencias.Entidades.Enum.Prioridad;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class GenerarPDF {
    private static final String rutaLogo = "src\\main\\java\\api_incidencias\\api_incidencias\\Pdf\\logo_DonDigital.png";
    private static final String ruta = "./pdf_generados/Pdf_ParteTrabajo_DonDigital.pdf";
    private static String nOrden, nombreCliente, apellidosCliente, emailCliente, telefonoCLiente;
    private static String cliente ="nombre apellidoooo aaa \n direccion = adadddadwadadd a aaaaaaaaadw wwww \n telefono = 1241412419410";
    private static String motivo, diagnostico, solucion, observacion;
    private static ArrayList<MaterialUtilizado> material;
    private static ArrayList<TiempoEmpleado> tiempoEmpleado;
    private static Estado estado;
    public static ResponseEntity<byte[]> generarPDF(ParteTrabajo parteTrabajo){

         Cliente clienteTmp = parteTrabajo.getIncidencia().getUsuarioCliente();
         nOrden = parteTrabajo.getIdOrden().toString();
         nombreCliente = clienteTmp.getNombre();
         apellidosCliente = clienteTmp.getApellido();
         emailCliente = clienteTmp.getCorreoElectronico();
         telefonoCLiente = clienteTmp.getTelefono();

         cliente = " cliente: "+nombreCliente + apellidosCliente +"\n"
                    +" dni: "+clienteTmp.getDni()
                    +" telefono: "+telefonoCLiente + "\n"
                    +" correo: "+emailCliente +"\n"
                    +"direccion: "+clienteTmp.getCalle();

         motivo = parteTrabajo.getIncidencia().getDescripcion();
         diagnostico = parteTrabajo.getDiagnostico();
         solucion = parteTrabajo.getTrabajoRealizado();
         observacion = parteTrabajo.getObservaciones();

         material = (ArrayList<MaterialUtilizado>) parteTrabajo.getListaMaterialUtilizado();
         tiempoEmpleado = (ArrayList<TiempoEmpleado>) parteTrabajo.getListaTiempoEmpleados();

         estado = parteTrabajo.getIncidencia().getEstado();
        try {
            convertHTMLToPDF(ruta);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return enviarPdf();
    }
    private static String filasMaterial(){
        String txt = "";
        for (MaterialUtilizado tmp : material){
            txt += "                <tr>\n" +
                    "                    <td>"+tmp.getCantidad()+"</td>\n" +
                    "                    <td>"+tmp.getNombre()+"</td>\n" +
                    "                    <td>"+tmp.getCoste()+"€</td>\n" +
                    "                </tr>\n";
        }
        return txt;
    }
    //limite 5
    private static String filasTiempo(){
        String txt = "";
        for (TiempoEmpleado tmp: tiempoEmpleado){
            String modoResolucion = tmp.getModoResolucion().name();
            txt +=      "         <tr>\n" +
                "                    <td style=\"padding: 0 3px 0;\">"+tmp.getFecha()+"</td>\n" +
                        "                    <td style=\"padding: 0 3px 0;\">"+tmp.getHoraEntrada()+"</td>\n" +
                        "                    <td style=\"padding: 0 3px 0;\">"+tmp.getHoraSalida()+"</td>\n" +
                        "                    <td style=\"padding: 0 3px 0;\">\n" +
                        "                        <input type=\"checkbox\" id=\"remota\" name=\"resolucion\" value=\"remota\"" + (modoResolucion.equals("remota") ? " checked" : "") + ">\n" +
                                "                <label for=\"remota\">REMOTA</label>\n" +
                                "                <input type=\"checkbox\" id=\"presencial\" name=\"resolucion\" value=\"presencial\"" + (modoResolucion.equals("presencial") ? " checked" : "") + ">\n" +
                                "                <label for=\"presencial\">PRESENCIAL</label>\n" +
                                "                <input type=\"checkbox\" id=\"telefonica\" name=\"resolucion\" value=\"telefonica\"" + (modoResolucion.equals("telefonica") ? " checked" : "") + ">\n" +
                                "                <label for=\"telefonica\">TELEFÓNICA</label>\n" +
                        "                    </td>\n" +
                        "                    <td style=\"padding: 0 3px 0;\">"+ tmp.getHoraEntrada().until(tmp.getHoraSalida(), ChronoUnit.HOURS)+"</td>\n" +
                        "          </tr>\n";
            }
        return txt;
    }
    private static String situacion(){
        String estadoTxt = estado.name();
        return  "<label for=\"situacion\" style=\"margin-right: 30px\">SITUACIÓN : </label>\n" +
                "<label for=\"terminado\">TERMINADO</label>\n" +
                "<input type=\"checkbox\" id=\"terminado\" name=\"situacion\" value=\"terminado\"" +
                (estadoTxt.equals("terminado") ? " checked" : "") + ">\n" +  // Aquí se comprueba y marca la opción

                "<label for=\"pendiente\">PENDIENTE</label>\n" +
                "<input type=\"checkbox\" id=\"pendiente\" name=\"situacion\" value=\"pendiente\"" +
                (estadoTxt.equals("pendiente") ? " checked" : "") + ">\n" +  // Aquí se comprueba y marca la opción

                "<label for=\"seguimiento\">EN SEGUIMIENTO</label>\n" +
                "<input type=\"checkbox\" id=\"seguimiento\" name=\"situacion\" value=\"seguimiento\"" +
                (estadoTxt.equals("seguimiento") ? " checked" : "") + ">\n";
    }
    private static String contenidoHTML(){
        return  "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>PDF Document</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            font-size: 12px;\n" +
                "        }\n" +
                "\n" +
                "        .container {\n" +
                "            display: flex;\n" +
                "            margin-bottom: 15px;\n" +
                "        }\n" +
                "\n" +
                "        .logo-container {\n" +
                "            width: 200px;\n" +
                "        }\n" +
                "\n" +
                "        .border {\n" +
                "            border: 1px solid black;\n" +
                "            padding: 10px;\n" +
                "        }\n" +
                "\n" +
                "        .motivo-container,\n" +
                "        .diagnostico-container,\n" +
                "        .trabajo-realizado-container,\n" +
                "        .situacion-container,\n" +
                "        .tiempo-empleado-container,\n" +
                "        .observaciones-container,\n" +
                "        .signature-container {\n" +
                "            margin-bottom: 10px;\n" +
                "        }\n" +
                "\n" +
                "        .text-input {\n" +
                "            width: 100%;\n" +
                "            height: 80px;\n" +
                "            /* Ajustar la altura según tus especificaciones */\n" +
                "        }\n" +
                "\n" +
                "        .rayas-negras {\n" +
                "            border: none; /* Elimina el borde predeterminado del textarea */\n" +
                "            resize: none; /* Evita que el usuario redimensione el textarea */\n" +
                "            outline: none; /* Elimina el contorno al enfocar el textarea */\n" +
                "            background-image: linear-gradient(to bottom, transparent 95%, black 95%); /* Agrega líneas negras */\n" +
                "            background-size: 100% 5px; /* Espacio entre líneas */\n" +
                "            background-repeat: repeat-y; /* Repetir las líneas verticalmente */\n" +
                "        }\n" +
                "\n" +
                "        .checkbox-options {\n" +
                "            display: flex;\n" +
                "            width: 100%;\n" +
                "            justify-content: flex-start;\n" +
                "        }\n" +
                "\n" +
                "        .checkbox-options input[type=\"checkbox\"] {\n" +
                "            margin-right: 50px;\n" +
                "        }\n" +
                "\n" +
                "        .checkbox-options label {\n" +
                "            display: inline-block;\n" +
                "            margin-right: 5px;\n" +
                "        }\n" +
                "\n" +
                "        table {\n" +
                "            width: 100%;\n" +
                "            border-collapse: collapse;\n" +
                "            margin-bottom: 10px;\n" +
                "        }\n" +
                "\n" +
                "        th,\n" +
                "        td {\n" +
                "            border: 1px solid black;\n" +
                "            padding: 3px;\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "\n" +
                "        th {\n" +
                "            background-color: lightgray;\n" +
                "        }\n" +
                "\n" +
                "        .titulos_tablas {\n" +
                "            border: none;\n" +
                "            /* Elimina el borde */\n" +
                "            padding: 0;\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "\n" +
                "        .altura_fija_tabla_materiales {\n" +
                "            min-height: 118px; /* Establece la altura mínima para el contenedor */\n" +
                "            max-height: 118px; /* Ajusta esta altura según sea necesario */\n" +
                "            overflow-y: auto; /* Agrega una barra de desplazamiento vertical si es necesario */\n" +
                "        }\n" +
                "        .altura_fija_tabla_tiempo {\n" +
                "            min-height: 170px; /* Establece la altura mínima para el contenedor */\n" +
                "            max-height: 170px; /* Ajusta esta altura según sea necesario */\n" +
                "            overflow-y: auto; /* Agrega una barra de desplazamiento vertical si es necesario */\n" +
                "        }\n" +
                "\n" +
                "        .horizontal-container {\n" +
                "            display: flex;\n" +
                "            width: 100%;\n" +
                "        }\n" +
                "\n" +
                //"        .horizontal-container .border {\n" +
                //"            flex-grow: 1;\n" +
                //"            margin-right: 10px;\n" +
                //"        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body class=\"border\">\n" +
                "    <!-- Contenedor para la información del cliente -->\n" +
                "    <div class=\"container\">\n" +
                "        <table>\n" +
                "            <tbody>\n" +
                "                <tr>\n" +
                "                    <td style=\"width: 10%; border: none;\">\n" +
                "                        <!-- Contenedor para el logo -->\n" +
                "                        <div class=\"logo-container\">\n" +
                "                            <!-- Aquí va el logo de la empresa -->\n" +
                "                             <img src=\"" + rutaLogo + "\" alt=\"Logo de la empresa\" width=\"200\" height=\"50\">\n" +
                "                        </div>\n" +
                "                    </td>\n" +
                "                    <td style=\"width: 70%; border: none;\">\n" +
                "                        <!-- Aquí va la información del cliente -->\n" +
                "                        <label>PARTE DE TRABAJO | SERVICIOS INFORMÁTICOS</label>\n" +
                "                        <textarea class=\"text-input\" placeholder=\"CLIENTE : \" style=\"width: 100%; height: 85px;\">"+cliente+"</textarea>\n" +
                "                    </td>\n" +
                "                    <td style=\"width: 20%; border: none;\">\n" +
                "                        <!-- Contenedor para el número de orden -->\n" +
                "                        <label>Nº DE ORDEN</label>\n" +
                "                        <textarea class=\"text-input\" style=\"width: 100%; height: 40px;\">"+nOrden+"</textarea>\n" +
                "                        <textarea class=\"rayas-negras\" style=\"width: 100%; height: 40px;\"></textarea>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "\n" +
                "    <!-- Contenedor para MOTIVO DE LA LLAMADA -->\n" +
                "    <div class=\"motivo-container\">\n" +
                "        <label>MOTIVO DE LA LLAMADA O CONSULTA</label>\n" +
                "        <textarea class=\"text-input\" style=\"height: 60px;\">"+motivo+"</textarea>\n" +
                "    </div>\n" +
                "\n" +
                "    <!-- Contenedor para DIAGNÓSTICO -->\n" +
                "    <div class=\"diagnostico-container\">\n" +
                "        <label>DIAGNÓSTICO</label>\n" +
                "        <textarea class=\"text-input\" style=\"height: 120px;\">"+diagnostico+"</textarea>\n" +
                "    </div>\n" +
                "\n" +
                "    <!-- Contenedor para TRABAJO REALIZADO -->\n" +
                "    <div class=\"trabajo-realizado-container\">\n" +
                "        <label>SOLUCIÓN / TRABAJO REALIZADO</label>\n" +
                "        <textarea class=\"text-input\" style=\"height: 120px;\">"+solucion+"</textarea>\n" +
                "    </div>\n" +
                "\n" +
                "    <!-- Contenedor para la tabla de materiales -->\n" +
                "    <div class=\"altura_fija_tabla_materiales\">\n" +
                "        <table>\n" +
                "            <tbody>\n" +
                "                <tr>\n" +
                "                    <td class=\"titulos_tablas\">CANTIDAD</td>\n" +
                "                    <td class=\"titulos_tablas\">MATERIAL/SOFTWARE UTILIZADO</td>\n" +
                "                    <td class=\"titulos_tablas\">COSTE</td>\n" +
                "                </tr>\n" + filasMaterial() +
         /*       "                <tr>\n" +
                "                    <td>1</td>\n" +
                "                    <td>Material A</td>\n" +
                "                    <td>$10.00</td>\n" +
                "                </tr>\n" +                                 */
                "                <!--\n" +
                "                <tr>\n" +
                "                    <td>2</td>\n" +
                "                    <td>Material B</td>\n" +
                "                    <td>$20.00</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>3</td>\n" +
                "                    <td>Material C</td>\n" +
                "                    <td>$30.00</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>4</td>\n" +
                "                    <td>Material D</td>\n" +
                "                    <td>$40.00</td>\n" +
                "                </tr>\n" +
                "                -->\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "\n" +
                "    <!-- Contenedor para las opciones de SITUACIÓN -->\n" +
                "    <div class=\"situacion-container\" style=\"padding: 1px\">\n" +
                "        <div class=\"checkbox-options\" style=\"margin-top: 8px; margin-bottom: 10px\">\n" + situacion() +
                /*"           <label for=\"situacion\" style=\"margin-right: 30px\">SITUACIÓN : </label>\n" +
                "            <label for=\"terminado\">TERMINADO</label>\n" +
                "            <input type=\"checkbox\" id=\"terminado\" name=\"situacion\" value=\"terminado\">\n" +
                "            <label for=\"pendiente\">PENDIENTE</label>\n" +
                "            <input type=\"checkbox\" id=\"pendiente\" name=\"situacion\" value=\"pendiente\">\n" +
                "            <label for=\"seguimiento\">EN SEGUIMIENTO</label>\n" +
                "            <input type=\"checkbox\" id=\"seguimiento\" name=\"situacion\" value=\"seguimiento\">\n" +
                "        </div>\n" +        */
                "    </div>\n" +
                "\n" +
                "    <!-- Contenedor para la tabla de TIEMPO EMPLEADO -->\n" +
                "    <div class=\"tiempo-empleado-container altura_fija_tabla_tiempo\">\n" +
                "        <table>\n" +
                "            <label><b>TIEMPO EMPLEADO</b></label>\n" +
                "            <tbody>\n" +
                "                <tr>\n" +
                "                    <td class=\"titulos_tablas\">FECHA</td>\n" +
                "                    <td class=\"titulos_tablas\">HORA ENTRADA</td>\n" +
                "                    <td class=\"titulos_tablas\">HORA SALIDA</td>\n" +
                "                    <td class=\"titulos_tablas\">MODO DE RESOLUCIÓN</td>\n" +
                "                    <td class=\"titulos_tablas\">TOTAL</td>\n" +
                "                </tr>\n" + filasTiempo() +
               /* "                <tr>\n" +
                "                    <td style=\"padding: 0 3px 0;\">01/04/2024</td>\n" +
                "                    <td style=\"padding: 0 3px 0;\">09:00</td>\n" +
                "                    <td style=\"padding: 0 3px 0;\">17:00</td>\n" +
                "                    <td style=\"padding: 0 3px 0;\">\n" +
                "                        <input type=\"checkbox\" id=\"remota\" name=\"resolucion\" value=\"remota\">\n" +
                "                        <label for=\"remota\">REMOTA</label>\n" +
                "                        <input type=\"checkbox\" id=\"presencial\" name=\"resolucion\" value=\"presencial\">\n" +
                "                        <label for=\"presencial\">PRESENCIAL</label>\n" +
                "                        <input type=\"checkbox\" id=\"telefonica\" name=\"resolucion\" value=\"telefonica\">\n" +
                "                        <label for=\"telefonica\">TELEFÓNICA</label>\n" +
                "                    </td>\n" +
                "                    <td style=\"padding: 0 3px 0;\">8 horas</td>\n" +
                "                </tr>\n" + */
                "                <!-- Aquí van más filas si es necesario -->\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "\n" +
                "    <!-- Contenedor para OBSERVACIONES, FIRMA TÉCNICO y FIRMA CLIENTE -->\n" +
                "    <div class=\"horizontal-container\" style=\"margin-bottom: 0; padding-bottom: 0;\">\n" +
                "        <table>\n" +
                "            <tbody>\n" +
                "                <tr>\n" +
                "                    <td style=\"width: 50%; border: none;\">\n" +
                "                        <label>OBSERVACIONES</label>\n" +
                "                        <textarea class=\"text-input\" style=\"height: 80px;\">"+observacion+"</textarea>\n" +
                "                    </td>\n" +
                "                    <td style=\"border: none;\">\n" +
                "                        <label>FIRMA TÉCNICO</label>\n" +
                "                        <textarea class=\"text-input\" style=\"height: 80px;\"></textarea>\n" +
                "                    </td>\n" +
                "                    <td style=\"border: none;\">\n" +
                "                        <label>FIRMA CLIENTE</label>\n" +
                "                        <textarea class=\"text-input\" style=\"height: 80px;\"></textarea>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";

    }
    private static void convertHTMLToPDF(String outputFile) throws IOException {
        // Convertir el HTML a PDF
        ConverterProperties properties = new ConverterProperties();
        HtmlConverter.convertToPdf(contenidoHTML(), new FileOutputStream(outputFile), properties);
    }
    private static ResponseEntity<byte[]> enviarPdf() {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ConverterProperties properties = new ConverterProperties();

        // Aquí llamas a tu método contenidoHTML() para obtener el contenido HTML
        String htmlContent = contenidoHTML();

        HtmlConverter.convertToPdf(htmlContent, outputStream, properties);

        byte[] pdfContents = outputStream.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "documento.pdf");
        headers.setContentLength(pdfContents.length);

        return new ResponseEntity<>(pdfContents, headers, org.springframework.http.HttpStatus.OK);

    }
    public static void main(String[] args) {

        //String htmlContent = contenidoHTML();
        String archivoSalidaPdf = "./pdf_generados/Pdf_ParteTrabajo_DonDigital.pdf";
/*
        try {
            convertHTMLToPDF(archivoSalidaPdf);


            System.out.println("-----> Se ha generado el archivo PDF correctamente en : "+ archivoSalidaPdf);
        } catch (IOException e) {
            e.printStackTrace();
        }

*/
        Cliente c = new Cliente();
        c.setNombre("nombre");
        c.setApellido("apellido apellidoo");
        c.setCorreoElectronico("correo@gmail.com");
        c.setDni("12345678a");
        c.setTelefono("123456789");

        Incidencia i = new Incidencia();
        i.setUsuarioCliente(c);
        i.setTitulo("titulo");
        i.setDescripcion("descripcion");
        i.setFechaCreacion(LocalDateTime.now());
        i.setEstado(Estado.pendiente);
        i.setPrioridad(Prioridad.alta);

        MaterialUtilizado m = new MaterialUtilizado();
        m.setCoste(23);
        m.setCantidad(2);
        m.setNombre("obj1");

        MaterialUtilizado m2 = new MaterialUtilizado();
        m2.setCoste(278);
        m2.setCantidad(1);
        m2.setNombre("obj2");

        MaterialUtilizado m3 = new MaterialUtilizado();
        m3.setCoste(56575);
        m3.setCantidad(17);
        m3.setNombre("obj3");

        MaterialUtilizado m4 = new MaterialUtilizado();
        m4.setCoste(56575);
        m4.setCantidad(17);
        m4.setNombre("obj3");

        MaterialUtilizado m5 = new MaterialUtilizado();
        m5.setCoste(56575);
        m5.setCantidad(17);
        m5.setNombre("obj3");



        TiempoEmpleado t = new TiempoEmpleado();
        t.setHoraEntrada(LocalTime.now());
        t.setHoraSalida(LocalTime.now());
        t.setModoResolucion(ModoResolucion.presencial);

        TiempoEmpleado t1 = new TiempoEmpleado();
        t1.setHoraEntrada(LocalTime.now());
        t1.setHoraSalida(LocalTime.now());
        t1.setModoResolucion(ModoResolucion.remota);

        TiempoEmpleado t2 = new TiempoEmpleado();
        t2.setHoraEntrada(LocalTime.now());
        t2.setHoraSalida(LocalTime.now());
        t2.setModoResolucion(ModoResolucion.remota);

        TiempoEmpleado t3 = new TiempoEmpleado();
        t3.setHoraEntrada(LocalTime.now());
        t3.setHoraSalida(LocalTime.now());
        t3.setModoResolucion(ModoResolucion.remota);

        TiempoEmpleado t4 = new TiempoEmpleado();
        t4.setHoraEntrada(LocalTime.now());
        t4.setHoraSalida(LocalTime.now());
        t4.setModoResolucion(ModoResolucion.telefonica);

        TiempoEmpleado t5 = new TiempoEmpleado();
        t5.setHoraEntrada(LocalTime.now());
        t5.setHoraSalida(LocalTime.now());
        t5.setModoResolucion(ModoResolucion.remota);

        TiempoEmpleado t6 = new TiempoEmpleado();
        t6.setHoraEntrada(LocalTime.now());
        t6.setHoraSalida(LocalTime.now());
        t6.setModoResolucion(ModoResolucion.remota);


        List<TiempoEmpleado> lis = new ArrayList<>();
        lis.add(t);
        lis.add(t1);
        lis.add(t2);
        lis.add(t3);
        lis.add(t4);
        lis.add(t5);
        lis.add(t6);

        List<MaterialUtilizado> lista = new ArrayList<>();
        lista.add(m);
        lista.add(m2);
        lista.add(m3);
        lista.add(m4);
        lista.add(m5);



        ParteTrabajo p = new ParteTrabajo();
        p.setIncidencia(i);
        p.setIdOrden(133l);
        p.setTrabajoRealizado("trabajo realizado");
        p.setDiagnostico("diagnostico");
        p.setObservaciones("observaciones");
        p.setListaMaterialUtilizado(lista);
        p.setListaTiempoEmpleados(lis);

        generarPDF(p);
    }
}