package org.thraex.admin.business.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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
     *      app: test-app
     *      dir: d1/d2/d3
     *      file: (binary)
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
     *     {@link RequestParam} must be added: name -> valueString
     * </p>
     *
     * <pre>
     *     Payload(Form Data):
     *      app: test-app
     *      dir: d1/d2/d3
     *      file: (binary)
     * </pre>
     *
     * @param file
     * @param params
     */
    @PostMapping("v1")
    public void single(MultipartFile file, @RequestParam Map<String, String> params) {
        System.out.println("*********** Single(Map receiving parameters) ***********");
        System.out.println(String.format("Map params: %s", params));
        mockTransfer(file);
    }

    /**
     * POJO receiving parameters
     *
     * <pre>
     *     Payload(Form Data):
     *      app: test-app
     *      dir: d1/d2/d3
     *      file: (binary)
     * </pre>
     *
     * @param file
     * @param params
     */
    @PostMapping("v2")
    public void single(MultipartFile file, Params params) {
        System.out.println("*********** Single(POJO receiving parameters) ***********");
        System.out.println(String.format("POJO params: %s", params));
        mockTransfer(file);
    }

    /**
     * Simple types receive parameters
     *
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
     * @param file or List<MultipartFile>
     * @param app
     * @param dir
     */
    @PostMapping("batch")
    public void batch(MultipartFile[] file, String app, String dir) {
        System.out.println("*********** Batch(Simple types receive parameters) ***********");
        System.out.println(String.format("App: %s, Dir: %s", app, dir));
        Stream.of(file).forEach(this::mockTransfer);
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
    @PostMapping("batch/v1")
    public void batch(MultipartFile[] file, @RequestParam Map<String, String> params) {
        System.out.println("*********** Batch(Map receiving parameters) ***********");
        System.out.println(String.format("Map params: %s", params));
        Stream.of(file).forEach(this::mockTransfer);
    }

    /**
     * POJO receiving parameters
     *
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
    @PostMapping("batch/v2")
    public void batch(MultipartFile[] file, Params params) {
        System.out.println("*********** Batch(POJO receiving parameters) ***********");
        System.out.println(String.format("POJO params: %s", params));
        Stream.of(file).forEach(this::mockTransfer);
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

        private Map<String, String> other1; // rejected value [[object Object]]
        private User other2; // rejected value [[object Object]]
        private List<String> other3;

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

        public Map<String, String> getOther1() {
            return other1;
        }

        public Params setOther1(Map<String, String> other1) {
            this.other1 = other1;
            return this;
        }

        public User getOther2() {
            return other2;
        }

        public Params setOther2(User other2) {
            this.other2 = other2;
            return this;
        }

        public List<String> getOther3() {
            return other3;
        }

        public Params setOther3(List<String> other3) {
            this.other3 = other3;
            return this;
        }

        @Override
        public String toString() {
            return "Params{" +
                    "app='" + app + '\'' +
                    ", dir='" + dir + '\'' +
                    ", other1=" + other1 +
                    ", other2=" + other2 +
                    ", other3=" + other3 +
                    '}';
        }
    }

    static class User {

        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public User setName(String name) {
            this.name = name;
            return this;
        }

        public Integer getAge() {
            return age;
        }

        public User setAge(Integer age) {
            this.age = age;
            return this;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

    }

}
