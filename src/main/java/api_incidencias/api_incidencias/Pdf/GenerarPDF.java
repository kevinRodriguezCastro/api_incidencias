package api_incidencias.api_incidencias.Pdf;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

import java.io.FileOutputStream;
import java.io.IOException;

public class GenerarPDF {
    private String nOrden, nombreCliente, apellidosCliente, emailCliente;
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
                "            min-height: 140px; /* Establece la altura mínima para el contenedor */\n" +
                "            max-height: 140px; /* Ajusta esta altura según sea necesario */\n" +
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
                "                            <img src=\"\\Pdf\\logo_DonDigital.png\" alt=\"Logo de la empresa\" width=\"200\" height=\"50\">\n" +
                "                        </div>\n" +
                "                    </td>\n" +
                "                    <td style=\"width: 70%; border: none;\">\n" +
                "                        <!-- Aquí va la información del cliente -->\n" +
                "                        <label>PARTE DE TRABAJO | SERVICIOS INFORMÁTICOS</label>\n" +
                "                        <textarea class=\"text-input\" placeholder=\"CLIENTE : \" style=\"width: 100%; height: 85px;\"></textarea>\n" +
                "                    </td>\n" +
                "                    <td style=\"width: 20%; border: none;\">\n" +
                "                        <!-- Contenedor para el número de orden -->\n" +
                "                        <label>Nº DE ORDEN</label>\n" +
                "                        <textarea class=\"text-input\" style=\"width: 100%; height: 40px;\"></textarea>\n" +
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
                "        <textarea class=\"text-input\" style=\"height: 60px;\"></textarea>\n" +
                "    </div>\n" +
                "\n" +
                "    <!-- Contenedor para DIAGNÓSTICO -->\n" +
                "    <div class=\"diagnostico-container\">\n" +
                "        <label>DIAGNÓSTICO</label>\n" +
                "        <textarea class=\"text-input\" style=\"height: 120px;\"></textarea>\n" +
                "    </div>\n" +
                "\n" +
                "    <!-- Contenedor para TRABAJO REALIZADO -->\n" +
                "    <div class=\"trabajo-realizado-container\">\n" +
                "        <label>SOLUCIÓN / TRABAJO REALIZADO</label>\n" +
                "        <textarea class=\"text-input\" style=\"height: 120px;\"></textarea>\n" +
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
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>1</td>\n" +
                "                    <td>Material A</td>\n" +
                "                    <td>$10.00</td>\n" +
                "                </tr>\n" +
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
                "    <div class=\"situacion-container\" style=\"padding: 2px\">\n" +
                "        <div class=\"checkbox-options\">\n" +
                "            <label for=\"situacion\" style=\"margin-right: 30px\">SITUACIÓN : </label>\n" +
                "            <label for=\"terminado\">TERMINADO</label>\n" +
                "            <input type=\"checkbox\" id=\"terminado\" name=\"situacion\" value=\"terminado\">\n" +
                "            <label for=\"pendiente\">PENDIENTE</label>\n" +
                "            <input type=\"checkbox\" id=\"pendiente\" name=\"situacion\" value=\"pendiente\">\n" +
                "            <label for=\"seguimiento\">EN SEGUIMIENTO</label>\n" +
                "            <input type=\"checkbox\" id=\"seguimiento\" name=\"situacion\" value=\"seguimiento\">\n" +
                "        </div>\n" +
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
                "                </tr>\n" +
                "                <tr>\n" +
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
                "                </tr>\n" +
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
                "                        <textarea class=\"text-input\" style=\"height: 80px;\"></textarea>\n" +
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
    public static void convertHTMLToPDF(String htmlContent,String outputFile) throws IOException {
        // Convertir el HTML a PDF
        ConverterProperties properties = new ConverterProperties();
        HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(outputFile), properties);
    }

    public static void main(String[] args) {

        String htmlContent = contenidoHTML();
        String archivoSalidaPdf = "Pdf_ParteTrabajo_DonDigital.pdf";

        try {
            convertHTMLToPDF(htmlContent, archivoSalidaPdf);
            System.out.println("-----> Se ha generado el archivo PDF correctamente en : "+ archivoSalidaPdf);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
