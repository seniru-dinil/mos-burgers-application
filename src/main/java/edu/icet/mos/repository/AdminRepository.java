package edu.icet.mos.repository;

import edu.icet.mos.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity,Integer> {
}
