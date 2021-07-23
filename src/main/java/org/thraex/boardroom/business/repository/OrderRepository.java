package org.thraex.boardroom.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thraex.boardroom.business.entity.Order;

/**
 * @author 鬼王
 * @date 2021/07/23 10:25
 */
public interface OrderRepository extends JpaRepository<Order, String> {
}
