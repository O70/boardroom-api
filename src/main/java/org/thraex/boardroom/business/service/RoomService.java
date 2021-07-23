package org.thraex.boardroom.business.service;

import org.springframework.stereotype.Service;
import org.thraex.boardroom.business.repository.RoomRepository;

/**
 * @author 鬼王
 * @date 2021/07/23 10:39
 */
@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

}
