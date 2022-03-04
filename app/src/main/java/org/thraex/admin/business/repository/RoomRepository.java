package org.thraex.admin.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thraex.admin.business.entity.Room;

/**
 * @author 鬼王
 * @date 2021/07/23 10:39
 */
public interface RoomRepository extends JpaRepository<Room, String> {
}
