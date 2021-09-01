package org.thraex.boardroom.business.mock;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2021/09/01 15:30
 */
public final class Mock {

    public static List<Dict> locations() {
        return null;
    }

    public static List<Dict> types() {
        return null;
    }

    public static List<Dict> projections() {
        return null;
    }

    public static void main(String[] args) {
        List<Dict> to = to(Location.values());
        List<Dict> to1 = to(Type.values());
        List<Dict> to2 = to(Projection.values());

        List<Dict> to20 = to2(Location.values());
        List<Dict> to21 = to2(Type.values());
        List<Dict> to22 = to2(Projection.values());
        System.out.println(1);
    }
    
    private static <X extends DictItem> List<Dict> to(X[] values) {
        return Stream.of(values)
                .map(it -> new Dict(it.getId(), it.getName(), it.getCode(), it.getRemark()))
                .collect(Collectors.toList());
    }

    private static <X extends ToDict> List<Dict> to2(X[] values) {
        return Stream.of(values)
                .map(it -> it.to())
                .collect(Collectors.toList());
    }

    public static class Dict {

        private String id;

        private String name;

        private String code;

        private String remark;

        public Dict(String id, String name, String code, String remark) {
            this.id = id;
            this.name = name;
            this.code = code;
            this.remark = remark;
        }

    }

    private interface DictItem {

        String getId();

        String getName();

        String getCode();

        String getRemark();

    }

    private interface ToDict {

        Dict to();

    }

    private enum Location implements DictItem, ToDict {

        ZL("id-ZL", "主楼", "主楼-remark"),
        KJHYZX("id-KJHYZX", "科技会议中心", "科技会议中心-remark"),
        SJZX("id-SJZX", "数据中心", "数据中心-remark");

        private final String id;
        private final String name;
        private final String remark;

        Location(String id, String name, String remark) {
            this.id = id;
            this.name = name;
            this.remark = remark;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getCode() {
            return name();
        }

        @Override
        public String getRemark() {
            return remark;
        }

        @Override
        public Dict to() {
            return new Dict(id, name, name(), remark);
        }

    }

    private enum Type implements DictItem, ToDict {

        BGT("id-BGT", "报告厅"),
        SPHYS("id-SPHYS", "视频会议室"),
        PTHYS("id-PTHYS", "普通会议室"),
        BMHYS("id-BMHYS", "保密会议室"),
        HJS("id-HJS", "会见室");

        private final String id;
        private final String name;

        Type(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getCode() {
            return name();
        }

        @Override
        public String getRemark() {
            return null;
        }

        @Override
        public Dict to() {
            return new Dict(id, name, name(), null);
        }

    }

    private enum Projection implements DictItem, ToDict {

        NONE("id-NONE", "无"),
        STY("id-STY", "双投影"),
        DTY("id-DTY", "单投影");

        private final String id;
        private final String name;

        Projection(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getCode() {
            return name();
        }

        @Override
        public String getRemark() {
            return null;
        }

        @Override
        public Dict to() {
            return new Dict(id, name, name(), null);
        }

    }

}
