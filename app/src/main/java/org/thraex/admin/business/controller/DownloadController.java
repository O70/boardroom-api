package org.thraex.admin.business.controller;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author 鬼王
 * @date 2021/07/29 15:09
 */
@RestController
@RequestMapping("download")
public class DownloadController {

    @GetMapping("loop")
    public void loop(HttpServletResponse response) {
        String charset = "UTF-8";
        String title = "会议室预定情况查询(服务人员)-LOOP";

        try (OutputStream os = response.getOutputStream()) {
            response.reset();
            response.setCharacterEncoding(charset);
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    String.format("attachment;filename=%s.xls", URLEncoder.encode(title, charset)));

            Path path = Excel.create(title, 21);
            InputStream is = new FileInputStream(path.toFile());
            byte[] b = new byte[1024];
            int i;
            while ((i = is.read(b)) > 0) {
                os.write(b, 0, i);
            }
            is.close();
            os.flush();
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("once")
    public void once(HttpServletResponse response) {
        String charset = "UTF-8";
        String title = "会议室预定情况查询(服务人员)-ONCE";

        try (OutputStream os = new BufferedOutputStream(response.getOutputStream())) {
            response.reset();
            response.setCharacterEncoding(charset);
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    String.format("attachment;filename=%s.xls", URLEncoder.encode(title, charset)));

            Path path = Excel.create(title, 21);
            os.write(Files.readAllBytes(path));
            os.flush();
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("spring")
    public ResponseEntity<byte[]> spring() throws IOException, WriteException {
        String charset = "UTF-8";
        String title = "会议室预定情况查询(服务人员)-SPRING";

        HttpHeaders headers = new HttpHeaders();
        //headers.add("Content-Type", "application/vnd.ms-excel");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                String.format("attachment;filename=%s.xls", URLEncoder.encode(title, charset)));

        Path path = Excel.create(title, 21);

        return ResponseEntity.ok()
                .headers(headers)
                .body(Files.readAllBytes(path));
    }

    private static class Excel {

        static Path create(String title, int colLen) throws IOException, WriteException {
            Path tempFile = Files.createTempFile("service-book", ".xls");

            WritableWorkbook workbook = Workbook.createWorkbook(tempFile.toFile());

            WritableFont titleFont = new WritableFont(WritableFont.TIMES, 18, WritableFont.BOLD);
            WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
            titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            titleFormat.setAlignment(Alignment.CENTRE);
            titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            titleFormat.setWrap(true);

            WritableSheet sheet = workbook.createSheet(title, 0);

            Label label = new Label(0, 0, title, titleFormat);
            sheet.addCell(label);
            sheet.setRowView(0, 1500);
            sheet.mergeCells(0, 0, colLen, 0);

            workbook.write();
            workbook.close();

            return tempFile;
        }

        @Deprecated
        static byte[] toBuffer(File file) throws IOException {
            InputStream fis = new FileInputStream(file);
            InputStream bis = new BufferedInputStream(fis);
            byte[] buffer = new byte[bis.available()];
            bis.read(buffer);
            bis.close();

            return buffer;
        }

    }

}
