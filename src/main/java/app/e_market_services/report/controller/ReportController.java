package app.e_market_services.report.controller;

import app.e_market_services.common.response.ApiResponse;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.poi.*;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController {
//
    @GetMapping
    public String createReport() {
//        try (Stream<String> stream
//                = Files.lines(Paths.get(
//                        ClassLoader.getSystemResource("/Users/johnpaulsale/SoftwareProjects/e_market_services/src/main/resources/templates/Sample Doc.docx").toURI()))) {
//            return stream.collect(Collectors.joining(" "));
        try {
            XWPFDocument document = new XWPFDocument();
            XWPFParagraph title = document.createParagraph();
            title.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun titleRun = title.createRun();
            titleRun.setText("Build your rest api");
            titleRun.setColor("009933");
            titleRun.setBold(true);
            titleRun.setFontFamily("Courier");
            titleRun.setFontSize(20);

            FileOutputStream out = new FileOutputStream("/Users/johnpaulsale/SoftwareProjects/e_market_services/src/main/resources/templates/Sample Doc2.docx");
            document.write(out);
            out.close();
            document.close();
            return "Success";
        } catch (Exception e) {
            return "empty";
        }
    }
}
