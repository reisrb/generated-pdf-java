package br.com.bandtec.gerador.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pdf")
public class PdfController {

  @Autowired
  private PdfFormatService pdfFormatService;

  @PostMapping
  public ResponseEntity<byte[]> gerarPdf(@RequestParam("nome") String nome) throws Exception {
    String filenameTemplate = pdfFormatService.formatThymeleaf(nome);

    File file = Paths.get("./" + filenameTemplate + ".pdf").toFile();
    InputStream fileInStream = new FileInputStream(file);
    // file.delete();

    byte[] data = IOUtils.toByteArray(fileInStream);

    return ResponseEntity.ok()
        .header("Content-Disposition", "attachment; filename=" + file.getName())
        .contentLength(data.length).contentType(MediaType.valueOf("application/pdf")).body(data);
  }

}
