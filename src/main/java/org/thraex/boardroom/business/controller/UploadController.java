package org.thraex.boardroom.business.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2021/12/20 11:35
 */
@RestController
@RequestMapping("upload")
public class UploadController {

    /**
     * Simple types receive parameters
     *
     * <p>
     *     Content-Type: application/x-www-form-urlencoded, multipart/form-data
     * </p>
     *
     * <pre>
     *     Payload(Form Data):
     *      file: (binary)
     *      app: test-app
     *      dir: d1/d2/d3
     * </pre>
     *
     * @param file
     * @param app
     * @param dir
     */
    @PostMapping
    public void single(MultipartFile file, String app, String dir) {
        System.out.println("*********** Single(Simple types receive parameters) ***********");
        System.out.println(String.format("App: %s, Dir: %s", app, dir));
        mockTransfer(file);
    }

    /**
     * Map receiving parameters
     *
     * <p>
     *     {@link RequestParam} must be added
     * </p>
     *
     * <pre>
     *     Payload(Form Data):
     *      file: (binary)
     *      app: test-app
     *      dir: d1/d2/d3
     * </pre>
     *
     * @param file
     * @param params
     */
    @PostMapping("v1")
    public void single(MultipartFile file, @RequestParam Map<String, Object> params) {
        System.out.println("*********** Single(Map receiving parameters) ***********");
        System.out.println(String.format("Map params: %s", params));
        mockTransfer(file);
    }

    /**
     * POJO accepts parameters
     *
     * <pre>
     *     Payload(Form Data):
     *      file: (binary)
     *      app: test-app
     *      dir: d1/d2/d3
     * </pre>
     *
     * @param file
     * @param params
     */
    @PostMapping("v2")
    public void single(MultipartFile file, Params params) {
        System.out.println("*********** Single(POJO accepts parameters) ***********");
        System.out.println(String.format("POJO params: %s", params));
        mockTransfer(file);
    }

    /**
     *
     * @param files Or List<MultipartFile>
     */
    @PostMapping("batch")
    public void batch(MultipartFile[] files) {
        System.out.println("*********** Batch ***********");
        Stream.of(files).forEach(this::mockTransfer);
    }

    private void mockTransfer(MultipartFile file) {
        System.out.println("##########################################");
        System.out.println(String.format("Filename: %s, Size: %d",
                file.getOriginalFilename(), file.getSize()));
        System.out.println("##########################################");
    }

    static class Params {

        private String app;
        private String dir;

        public String getApp() {
            return app;
        }

        public Params setApp(String app) {
            this.app = app;
            return this;
        }

        public String getDir() {
            return dir;
        }

        public Params setDir(String dir) {
            this.dir = dir;
            return this;
        }

        @Override
        public String toString() {
            return "Params{" +
                    "app='" + app + '\'' +
                    ", dir='" + dir + '\'' +
                    '}';
        }
    }

}
