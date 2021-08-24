package org.thraex.boardroom.business.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.boardroom.business.entity.Room;
import org.thraex.boardroom.business.service.RoomService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 鬼王
 * @date 2021/07/23 10:38
 */
@RestController
@RequestMapping("rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> list() {
        return roomService.findAll();
    }

    @GetMapping("{id}")
    public Room one(@PathVariable String id) {
        return roomService.getOne(id);
    }

    @PostMapping
    public Room save(@Valid @RequestBody Room room) {
        return roomService.save(room);
    }

    @PutMapping
    public Room update(@RequestBody Room room) {
        Room old = roomService.getOne(room.getId());
        BeanUtils.copyProperties(room, old);
        roomService.save(old);
        return old;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        roomService.deleteById(id);
    }

}
