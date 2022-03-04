package org.thraex.boardroom.business.mock;

import java.util.List;

/**
 * @author 鬼王
 * @date 2021/09/01 15:30
 */
public final class Mock {

    public static List<Dict> locations() {
        return Dict.Convert.to(Dict.Location.values());
    }

    public static List<Dict> roomTypes() {
        return Dict.Convert.to(Dict.RoomType.values());
    }

    public static List<Dict> projections() {
        return Dict.Convert.to(Dict.Projection.values());
    }

    public static List<Dict> meetingTypes() {
        return Dict.Convert.to(Dict.MeetingType.values());
    }

    public static List<Dict> leaders() {
        return Dict.Convert.to(Dict.Leader.values());
    }

}
