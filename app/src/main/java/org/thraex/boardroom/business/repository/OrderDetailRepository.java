package org.thraex.boardroom.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thraex.boardroom.business.entity.OrderDetail;

/**
 * @author 鬼王
 * @date 2021/07/23 10:42
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
