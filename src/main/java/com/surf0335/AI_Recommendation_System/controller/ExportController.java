package com.surf0335.AI_Recommendation_System.controller;


import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.surf0335.AI_Recommendation_System.model.Letter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.thymeleaf.context.Context;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;


import java.io.ByteArrayOutputStream;
import java.util.Map;

@RestController
@RequestMapping("/export")
public class ExportController {

    @Value("${etherpad.api.url}")
    private String etherpadApiUrl;

    @Value("${etherpad.api.key}")
    private String etherpadApiKey;

    private final RestTemplate restTemplate;
    private final TemplateEngine templateEngine;

    public ExportController(RestTemplate restTemplate, TemplateEngine templateEngine) {
        this.restTemplate = restTemplate;
        this.templateEngine = templateEngine;
    }

    @PostMapping("/pdf")
    public ResponseEntity<byte[]> exportToPdf(@RequestParam Map<String, String> params) throws Exception {
            // 从请求参数中获取数据
            String padID = params.get("padId");
            String refereeName = params.get("refereeName");
            String position = params.get("position");
            String phone = params.get("phone");
            String email = params.get("email");
            String organization = params.get("organization");
            String address = params.get("address");

        System.out.println(refereeName);
        System.out.println(params);

        String padText = getPadText(padID);  // 获取 Etherpad 文本内容
        System.out.println(padText);
        // 创建输出流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // 设置页边距（左、右、上、下）
        float marginLeft = 54;
        float marginRight = 54;
        float marginTop = 36;
        float marginBottom = 36;

        // 初始化 PdfWriter 和 PdfDocument
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);
        document.setMargins(marginTop, marginRight, marginBottom, marginLeft);

        // 加载衬线字体
        String fontPath = "src/main/resources/static/georgia.ttf"; // 衬线字体路径
        PdfFont font = PdfFontFactory.createFont(fontPath, PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED);

        // 插入渐变图像作为页眉和页脚
        String gradientImagePath = "src/main/resources/static/DocDecoration.jpg"; // 渐变图像路径
        Image gradientImage = new Image(ImageDataFactory.create(gradientImagePath));

        // 页脚
        gradientImage.setFixedPosition(1, 0,0, pdfDocument.getDefaultPageSize().getWidth());
        gradientImage.setHeight(30); // 设置图片高度
        document.add(gradientImage);

        // 插入图片
        String imagePath = "src/main/resources/static/slogan.jpg"; // 图片路径
        Image image = new Image(ImageDataFactory.create(imagePath));
        image.setWidth(184); // 设置图片宽度
        image.setHeight(38); // 设置图片高度

        // 添加表单数据
        Paragraph formParagraph = new Paragraph()
                .add(new Text(refereeName + ", ").setFont(font).setFontSize(11))
                .add(new Text(position + "\n").setFont(font).setFontSize(11))
                .add(new Text(phone + "\n").setFont(font).setFontSize(10))
                .add(new Text(email + "\n").setFont(font).setFontSize(10))
                .add(new Text(organization + "\n").setFont(font).setFontSize(9))
                .add(new Text(address + "\n").setFont(font).setFontSize(9))
                .setTextAlignment(TextAlignment.RIGHT);


        // 设置字体格式、颜色等
        Paragraph title = new Paragraph().add(new Text("Recommendation Letter\n").setFontSize(12).setFont(font));
        title.setTextAlignment(TextAlignment.CENTER);

        Paragraph paragraph = new Paragraph()
                .add(new Text(padText).setFontSize(11).setFont(font));

        paragraph.setTextAlignment(TextAlignment.LEFT);
        paragraph.setVerticalAlignment(VerticalAlignment.TOP);

        Paragraph lastParagraph = new Paragraph()
                .add(new Text(refereeName + ", ").setFont(font).setFontSize(11))
                .add(new Text(position + "\n").setFont(font).setFontSize(11));

        // 将段落添加到文档
        // 添加到文档
        document.add(image);
        document.add(formParagraph);
        document.add(title);
        document.add(paragraph);
        document.add(lastParagraph);

        // 关闭文档
        document.close();

        byte[] pdfBytes = outputStream.toByteArray();

        // 设置 HTTP 响应头并返回 PDF 文件
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "document.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }


    @GetMapping("/word/{padID}")
    public ResponseEntity<byte[]> exportToWord(@PathVariable String padID) throws Exception {
        String padText = getPadText(padID);

        // 使用 Apache POI 生成 Word 文档
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // Word生成逻辑
        // XWPFDocument document = new XWPFDocument();
        // XWPFParagraph paragraph = document.createParagraph();
        // XWPFRun run = paragraph.createRun();
        // run.setText(padText);
        // document.write(outputStream);

        byte[] wordBytes = outputStream.toByteArray();

        // 返回 Word 文件
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "document.docx");

        return ResponseEntity.ok()
                .headers(headers)
                .body(wordBytes);
    }

    private String getPadText(String padID) throws Exception {
        String url = etherpadApiUrl + "/1/getText?apikey=" + etherpadApiKey + "&padID=" + padID;
        String response = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);
        return jsonNode.get("data").get("text").asText();
    }




}

