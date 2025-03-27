package edu.icet.mos.service;

import edu.icet.mos.dto.Admin;

import java.util.List;

public interface AdminService {
    Admin save(Admin admin);
    void delete(Integer id);
    Admin getById(Integer id);
    void update(Admin admin);
    List<Admin> getAll();
}
