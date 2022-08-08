package com.example.aplicacionempleos.services.implement;

import com.example.aplicacionempleos.models.entity.Vacante;
import com.example.aplicacionempleos.models.repository.VacanteRepository;
import com.example.aplicacionempleos.services.interfaces.IVacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("VacanteServiceJpa")
public class VacanteServiceJpa implements IVacanteService {
    @Autowired
    private VacanteRepository vacanteRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Vacante> findAll() {
        return vacanteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Vacante findById(Long id) {
        return vacanteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Vacante vacante) {
        vacanteRepository.save(vacante);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vacante> findByDestacadas() {
        return vacanteRepository.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        vacanteRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vacante> findByExample(Example<Vacante> example) {
        return vacanteRepository.findAll(example);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Vacante> findAllPage(Pageable pageable) {
        return vacanteRepository.findAll(pageable);
    }
}
