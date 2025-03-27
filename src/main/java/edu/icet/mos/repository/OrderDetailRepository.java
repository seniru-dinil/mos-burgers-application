package edu.icet.mos.repository;

import edu.icet.mos.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity,Integer> {
}
