package org.thraex.admin.business.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2021/12/21 16:06
 */
@RestController
@RequestMapping("upload/bundle")
public class UploadPartController {

    @PostMapping
    public void upload(@Valid Params params) {
        System.out.println("*********** Bundle params upload ***********");
        System.out.println(String.format("Bundle params: %s", params));
        Stream.of(params.getFile()).forEach(this::mockTransfer);
    }

    /**
     * Failed
     *
     * @param params
     */
    @Deprecated
    @PostMapping("v1")
    public void upload(@RequestPart PartParams params) {
        System.out.println("*********** Bundle params upload ***********");
        System.out.println(String.format("Bundle part params: %s", params));
        Stream.of(params.getFile()).forEach(this::mockTransfer);
    }

    private void mockTransfer(MultipartFile file) {
        System.out.println("##########################################");
        System.out.println(String.format("Filename: %s, Size: %d",
                file.getOriginalFilename(), file.getSize()));
        System.out.println("##########################################");
    }

    static class Params {

        /**
         * Note the null pointer exception
         */
        @NotNull
        private MultipartFile[] file;

        private String app;

        private String dir;

        public MultipartFile[] getFile() {
            return file;
        }

        public Params setFile(MultipartFile[] file) {
            this.file = file;
            return this;
        }

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
                    "file=" + Arrays.toString(file) +
                    ", app='" + app + '\'' +
                    ", dir='" + dir + '\'' +
                    '}';
        }

    }

    static class PartParams {

        private MultipartFile[] file;

        private String app;

        private String dir;

        private Map<String, Object> other1;
        private UploadController.User other2;
        private List<String> other3;

        public MultipartFile[] getFile() {
            return file;
        }

        public PartParams setFile(MultipartFile[] file) {
            this.file = file;
            return this;
        }

        public String getApp() {
            return app;
        }

        public PartParams setApp(String app) {
            this.app = app;
            return this;
        }

        public String getDir() {
            return dir;
        }

        public PartParams setDir(String dir) {
            this.dir = dir;
            return this;
        }

        public Map<String, Object> getOther1() {
            return other1;
        }

        public PartParams setOther1(Map<String, Object> other1) {
            this.other1 = other1;
            return this;
        }

        public UploadController.User getOther2() {
            return other2;
        }

        public PartParams setOther2(UploadController.User other2) {
            this.other2 = other2;
            return this;
        }

        public List<String> getOther3() {
            return other3;
        }

        public PartParams setOther3(List<String> other3) {
            this.other3 = other3;
            return this;
        }

        @Override
        public String toString() {
            return "PartParams{" +
                    "file=" + Arrays.toString(file) +
                    ", app='" + app + '\'' +
                    ", dir='" + dir + '\'' +
                    ", other1=" + other1 +
                    ", other2=" + other2 +
                    ", other3=" + other3 +
                    '}';
        }

    }

}
