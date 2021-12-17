package br.com.bandtec.gerador.pdf;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Service
public class PdfFormatService {
  
  public String formatThymeleaf(String nome){

    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);

		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);

    String filename = "boas-vindas";

    Context context = new Context();

    context.setVariable("nome", nome);

    PdfGenerator.convertToPdf(templateEngine.process("/pdf/" + filename, context), filename + ".pdf");

    return filename;
  }

}
