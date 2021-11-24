package org.thraex.boardroom.business.service;

import org.springframework.stereotype.Service;
import org.thraex.boardroom.business.entity.Room;
import org.thraex.toolkit.jpa.service.impl.GenericServiceImpl;
import org.thraex.toolkit.util.BeanUtils;

import java.util.Optional;

/**
 * @author 鬼王
 * @date 2021/07/23 101:39
 */
@Service
public class RoomService extends GenericServiceImpl<Room, String> {

    public Room update(Room room) {
        Room old = Optional.ofNullable(room)
                .map(Room::getId)
                .map(id -> this.findById(id).orElse(null))
                .orElse(null);
        Optional.ofNullable(old).ifPresent(it -> {
            BeanUtils.copyProperties(room, it, true);
            this.save(it);
        });

        return old;
    }

}