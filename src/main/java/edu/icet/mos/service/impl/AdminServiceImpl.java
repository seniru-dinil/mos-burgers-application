package edu.icet.mos.service.impl;

import edu.icet.mos.dto.Admin;
import edu.icet.mos.entity.AdminEntity;
import edu.icet.mos.repository.AdminRepository;
import edu.icet.mos.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final ModelMapper mapper;
    private final AdminRepository repository;

    @Override
    public List<Admin> getAll() {
        List<AdminEntity> all = repository.findAll();
        List<Admin> list= new ArrayList<>();
        all.forEach(admin->list.add(mapper.map(admin,Admin.class)));
        return list;
    }

    @Override
    public Admin save(Admin admin) {
        AdminEntity save = repository.save(mapper.map(admin, AdminEntity.class));
        return admin;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Admin getById(Integer id) {
        return mapper.map(repository.findById(id), Admin.class);
    }

    @Override
    public void update(Admin admin) {
       repository.save(mapper.map(admin,AdminEntity.class));
    }
}
