package org.thraex.admin.business.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.admin.business.entity.Room;
import org.thraex.admin.business.service.RoomService;

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
        return roomService.findById(id).orElse(null);
    }

    @PostMapping
    public Room save(@Valid @RequestBody Room room) {
        return roomService.save(room);
    }

    /**
     * TODO: Valid
     *
     * @param rooms
     * @return
     */
    @PostMapping("batch")
    public List<Room> saveBatch(@RequestBody List<Room> rooms) {
        return roomService.saveAll(rooms);
    }

    @PutMapping
    public Room update(@RequestBody Room room) {
        return roomService.update(room);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        roomService.deleteById(id);
    }

}
