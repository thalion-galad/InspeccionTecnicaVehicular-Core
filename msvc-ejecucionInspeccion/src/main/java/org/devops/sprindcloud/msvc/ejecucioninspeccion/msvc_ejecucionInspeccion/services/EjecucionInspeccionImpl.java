package org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.services;


import org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.entity.EjecucionInspeccion;
import org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.repositories.EjecucionInspeccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EjecucionInspeccionImpl implements EjecucionInspeccionService {
    @Autowired
    private EjecucionInspeccionRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<EjecucionInspeccion> listar() {
        return (List<EjecucionInspeccion>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EjecucionInspeccion> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public EjecucionInspeccion guardar(EjecucionInspeccion ejecucionInspeccion) {
        return repository.save(ejecucionInspeccion);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
