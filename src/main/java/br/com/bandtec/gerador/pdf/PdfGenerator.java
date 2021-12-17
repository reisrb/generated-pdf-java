package br.com.bandtec.gerador.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

public class PdfGenerator {
  public static void convertToPdf(String html, String filename) {
    try {
      try (OutputStream os = new FileOutputStream(filename)) {
        new PdfRendererBuilder()
            .useFastMode()
            .withHtmlContent(html, null)
            .toStream(os)
            .run();
      }
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }
}
