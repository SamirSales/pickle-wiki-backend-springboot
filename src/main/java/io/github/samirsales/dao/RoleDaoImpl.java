package io.github.samirsales.dao;

import io.github.samirsales.model.dto.RoleDTO;
import io.github.samirsales.model.entity.RoleEntity;
import io.github.samirsales.model.enums.Role;
import io.github.samirsales.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@SuppressWarnings("unused")
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void createIfNotExist(Role role) {
        if(!isPresent(role)) {
            final Long id = null;
            RoleEntity roleEntity = new RoleEntity(id, role);
            roleRepository.save(roleEntity);
        }
    }

    @Override
    public Optional<RoleEntity> getEntityByRole(Role role) {
        return roleRepository.findByRole(role);
    }

    private boolean isPresent(Role role) {
        return roleRepository.findByRole(role).isPresent();
    }

    @Override
    public List<RoleDTO> getAll() {
        List<RoleEntity> roleEntities = (List<RoleEntity>) roleRepository.findAll();
        List<RoleDTO> roleDtoList = new ArrayList<>(roleEntities.size());

        for(RoleEntity roleEntity : roleEntities){
            roleDtoList.add(new RoleDTO(roleEntity));
        }

        return roleDtoList;
    }
}
