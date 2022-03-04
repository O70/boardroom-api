package org.thraex.admin.business.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2021/12/21 17:04
 */
@RestController
@RequestMapping("upload/final")
public class UploadFinalController {

    /**
     * <pre>
     *     Payload(Form Data):
     *      app: test-app
     *      dir: d1/d2/d3
     *      file: (binary)
     *      file: (binary)
     *      file: (binary)
     *      ...
     * </pre>
     *
     * @param file
     * @param params
     */
    @PostMapping
    public void upload(MultipartFile[] file, Params params) {
        System.out.println("*********** Final upload ***********");
        System.out.println(String.format("Params: %s", params));

        if (file.length == 0) {
            throw new IllegalArgumentException("File is not exists");
        }

        // TODO: process folder
        Stream.of(file).forEach(this::mockTransfer);
    }

    @PostMapping("bundle")
    public void upload(@Valid BundleParams params) {
        System.out.println("*********** Final upload(Bundle) ***********");
        System.out.println(String.format("Params: %s", params));
        // TODO: process folder
        Stream.of(params.getFile()).forEach(this::mockTransfer);
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

    static class BundleParams {

        @NotNull
        private MultipartFile[] file;

        private String app;

        private String dir;

        public MultipartFile[] getFile() {
            return file;
        }

        public BundleParams setFile(MultipartFile[] file) {
            this.file = file;
            return this;
        }

        public String getApp() {
            return app;
        }

        public BundleParams setApp(String app) {
            this.app = app;
            return this;
        }

        public String getDir() {
            return dir;
        }

        public BundleParams setDir(String dir) {
            this.dir = dir;
            return this;
        }

        @Override
        public String toString() {
            return "BundleParams{" +
                    "file=" + Arrays.toString(file) +
                    ", app='" + app + '\'' +
                    ", dir='" + dir + '\'' +
                    '}';
        }

    }

}
