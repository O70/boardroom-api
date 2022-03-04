package org.thraex.admin.business.mock;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2021/09/02 09:27
 */
public class Dict implements Serializable {

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

    public String getId() {
        return id;
    }

    public Dict setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Dict setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Dict setCode(String code) {
        this.code = code;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public Dict setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    interface Convert {

        Dict to();

        static <X extends Convert> List<Dict> to(X[] values) {
            return Stream.of(values).map(it -> it.to()).collect(Collectors.toList());
        }

    }

    enum Location implements Convert {

        ZL("ID-ZL", "主楼", "主楼-备注备注备注备注备注备注备注备注备注备注备注"),
        KJHYZX("ID-KJHYZX", "科技会议中心", "科技会议中心-备注备注备注备注备注备注备注备注备注备注备注"),
        SJZX("ID-SJZX", "数据中心", "数据中心-备注备注备注备注备注备注备注备注备注备注备注");

        private final String id;
        private final String name;
        private final String remark;

        Location(String id, String name, String remark) {
            this.id = id;
            this.name = name;
            this.remark = remark;
        }

        @Override
        public Dict to() {
            return new Dict(id, name, name(), remark);
        }

    }

    enum RoomType implements Convert {

        BGT("ID-BGT", "报告厅"),
        SPHYS("ID-SPHYS", "视频会议室"),
        PTHYS("ID-PTHYS", "普通会议室"),
        BMHYS("ID-BMHYS", "保密会议室"),
        HJS("ID-HJS", "会见室");

        private final String id;
        private final String name;

        RoomType(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public Dict to() {
            return new Dict(id, name, name(), null);
        }

    }

    enum Projection implements Convert {

        NONE("ID-NONE", "无"),
        STY("ID-STY", "双投影"),
        DTY("ID-DTY", "单投影");

        private final String id;
        private final String name;

        Projection(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public Dict to() {
            return new Dict(id, name, name(), null);
        }

    }

    enum MeetingType implements Convert {
        MT_0("ID-MT_0", "普通会议"),
        MT_1("ID-MT_1", "科研会"),
        MT_2("ID-MT_2", "报告会"),
        MT_3("ID-MT_3", "部门会议"),
        MT_4("ID-MT_4", "外事会见/签字仪式"),
        MT_5("ID-MT_5", "培训"),
        MT_6("ID-MT_6", "视频会议"),
        MT_7("ID-MT_7", "院务会"),
        MT_8("ID-MT_8", "其他");

        private final String id;
        private final String name;

        MeetingType(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public Dict to() {
            return new Dict(id, name, name(), null);
        }

    }

    enum Leader implements Convert {

        L_0("ID-L_0", "领导-0"),
        L_1("ID-L_1", "领导-1"),
        L_2("ID-L_2", "领导-2"),
        L_3("ID-L_3", "领导-3"),
        L_4("ID-L_4", "领导-4");

        private final String id;
        private final String name;

        Leader(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public Dict to() {
            return new Dict(id, name, name(), null);
        }

    }

}
