package com.example.pruebascovid.pdfGenerator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.pruebascovid.R;
import com.example.pruebascovid.pojos.paciente;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class pdfGenerator {
    private final static String NOMBRE_DIRECTORIO = "MiPdf";
    private final static String ETIQUETA_ERROR = "ERROR";
    private Context contexto ;
    private String dirigido,resultado,referencia,metodo,tipo,observaciones,fechaActual,nombreDocumento;
    private paciente paci;
    public pdfGenerator(paciente pacie,String dirigido,String resultado,String referencia,String metodo,String tipo,String observaciones, Context contexto) {
        setPaciente(pacie);
        setDirigido(dirigido);
        setResultado(resultado);
        setReferencia(referencia);
        setMetodo(metodo);
        setTipo(tipo);
        setObservaciones(observaciones);
        Date myDate = new Date();
        setFechaActual(new SimpleDateFormat("dd/MM/yyyy").format(myDate));
        setNombreDocumento("Prueba_Covid_"+pacie.getNombre()+"_"+pacie.getApPaterno()+"_"+pacie.getApMaterno()+".pdf");
        setContexto(contexto);
    }

    public paciente getPaciente() {
        return paci;
    }

    public void setPaciente(paciente paciente) {
        this.paci= paciente;
    }

    public String getDirigido() {
        return dirigido;
    }

    public void setDirigido(String dirigido) {
        this.dirigido = dirigido;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void generarPDF(){
        // Creamos el documento.
        Document documento = new Document(PageSize.A4);
        try {
            File f = crearFichero(getNombreDocumento());
            // Creamos el flujo de datos de salida para el fichero donde
            // guardaremos el pdf.
            //Log.d("Hugo" , "error"+getPaciente().getNombre());
            FileOutputStream ficheroPdf = new FileOutputStream(f.getAbsolutePath());
            // Asociamos el flujo que acabamos de crear al documento.
            PdfWriter writer = PdfWriter.getInstance(documento, ficheroPdf);
            // Incluimos el pie de pagina y una cabecera
            /*HeaderFooter cabecera = new HeaderFooter(new Phrase("Esta es mi cabecera"), false);
            HeaderFooter pie = new HeaderFooter(new Phrase("Este es mi pie de pagina"), false);
            documento.setHeader(cabecera);
            documento.setFooter(pie);*/
            // Abrimos el documento.
            documento.open();
            // Añadimos un titulo con la fuente por defecto.
            //documento.add(new Paragraph("Titulo 1"));
            Font datosPersonales = FontFactory.getFont(FontFactory.TIMES, 14,Font.ITALIC);
            Font resutado = FontFactory.getFont(FontFactory.TIMES, 14,Font.BOLD);
            Font metodo = FontFactory.getFont(FontFactory.TIMES, 11,Font.ITALIC);
            //documento.add(new Paragraph("Titulo personalizado", font));
            // Insertamos una imagen que se encuentra en los recursos de la aplicacion.
            Bitmap bitmap = BitmapFactory.decodeResource(getContexto().getResources(),R.drawable.certificado_en_blanco);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image imagen = Image.getInstance(stream.toByteArray());
            imagen.scaleToFit(595, 842);
            Log.d("dareck","pagesize "+documento.getPageSize());
            imagen.setAbsolutePosition(0,50);
            documento.add(imagen);
            ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_LEFT, new Paragraph(getPaciente().getNombre()+" "+getPaciente().getApPaterno()+" "+getPaciente().getApMaterno(), datosPersonales), 145, 626, 0);
            ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_LEFT, new Paragraph(getPaciente().getEdad()+"", datosPersonales), 450, 626, 0);
            ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_LEFT, new Paragraph(LocalDate.now()+"", datosPersonales), 200, 597, 0);
            ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_LEFT, new Paragraph(LocalDate.now()+"", datosPersonales), 410, 597, 0);
            ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_LEFT, new Paragraph(getDirigido(), datosPersonales), 277, 557, 0);
            ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_LEFT, new Paragraph(getResultado(), resutado), 277, 413, 0);
            ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_LEFT, new Paragraph(getReferencia(), resutado), 410, 413, 0);
            ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_LEFT, new Paragraph(getMetodo(), metodo), 132, 392, 0);
            ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_LEFT, new Paragraph(getTipo(), metodo), 165, 380, 0);
            ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_LEFT, new Paragraph(getObservaciones(), metodo), 163, 367, 0);
            /*// Insertamos una tabla.
            PdfPTable tabla = new PdfPTable(5);
            for (int i = 0; i < 15; i++) {
                tabla.addCell("Celda " + i);
            }
            documento.add(tabla);*/
            // Agregar marca de agua
            /*font = FontFactory.getFont(FontFactory.HELVETICA, 42, Font.BOLD);
            ColumnText.showTextAligned(writer.getDirectContentUnder(),Element.ALIGN_CENTER, new Paragraph("androfast.com", font), 297.5f, 421, 0);*/
        } catch (DocumentException e) {
            Log.e(ETIQUETA_ERROR, e.getMessage());
        } catch (IOException e) {
            Log.e(ETIQUETA_ERROR, e.getMessage());
        } finally {
            // Cerramos el documento.
            documento.close();
        }
    }
    public static File crearFichero(String nombreFichero) throws IOException {
        File ruta = getRuta();
        File fichero = null;
        if (ruta != null)
            fichero = new File(ruta, nombreFichero);
        return fichero;
    }

    /**
     * Obtenemos la ruta donde vamos a almacenar el fichero.
     *
     * @return
     */
    public static File getRuta() {

        // El fichero sera almacenado en un directorio dentro del directorio
        // Descargas
        File ruta = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            ruta = new File(
                    Environment
                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    NOMBRE_DIRECTORIO);

            if (ruta != null) {
                if (!ruta.mkdirs()) {
                    if (!ruta.exists()) {
                        return null;
                    }
                }
            }
        } else {
        }

        return ruta;
    }
}
