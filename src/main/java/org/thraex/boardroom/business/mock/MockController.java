package org.thraex.boardroom.business.mock;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.boardroom.business.entity.Room;
import org.thraex.boardroom.business.service.RoomService;
import org.thraex.toolkit.constant.Whether;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2021/07/23 15:37
 */
@RestController
@RequestMapping("mocks")
public class MockController {

    private final RoomService roomService;

    public MockController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("room")
    public List<Room> room() {
        List<Dict> locations = Mock.locations();
        List<Dict> roomTypes = Mock.roomTypes();
        int size1 = roomTypes.size();
        List<Dict> projections = Mock.projections();
        int size2 = projections.size();
        Whether[] whether = Whether.values();
        int len = whether.length;

        BiFunction<Integer, Dict, Room> newRoom = (i, l) -> new Room()
                .setName(String.format("%s-第%d会议室", l.getCode(), i + 1))
                .setTypeId(roomTypes.get(i % size1).getId())
                .setLocationId(l.getId())
                .setAddress(String.format("%s-具体位置-%d", l.getCode(), i + 1))
                .setNumber(String.valueOf(200 + i))
                .setPhone("10096")
                .setProjectionTypeId(projections.get(i % size2).getId())
                .setNetwork(whether[i % len])
                .setComputer(whether[i % len])
                .setPa(whether[i % len])
                .setPaperless(whether[i % len])
                .setRestroom(whether[i % len])
                .setToilet(whether[i % len])
                .setMaintain(Whether.NO)
                .setReserved(whether[i % len])
                .setCapacity(20 + i)
                .setTableCapacity(5 + i)
                .setSort(i);

        List<Room> collect = locations.stream()
                .map(l -> IntStream.range(0, 10).mapToObj(i -> newRoom.apply(i, l)).toArray(Room[]::new))
                .flatMap(Stream::of)
                .collect(Collectors.toList());

        return roomService.saveAll(collect);
    }

}
