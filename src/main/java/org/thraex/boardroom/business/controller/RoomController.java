package org.thraex.boardroom.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.boardroom.business.entity.Room;
import org.thraex.boardroom.business.service.RoomService;

/**
 * @author 鬼王
 * @date 2021/07/23 10:38
 */
@RestController
@RequestMapping("rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public Room save(@RequestBody Room room) {
        //roomService.save(room);
        return room;
    }

    @PostMapping("mock")
    public void mock() {
        Room room = new Room();
        room.setName("房间1").setAddress("123sd");
        roomService.save(room);
    }

}
