package fr.formation.training.forum.services;

import java.util.List;

import fr.formation.training.forum.RessourceNotFoundException;
import fr.formation.training.forum.dtos.TechnologyAddDto;
import fr.formation.training.forum.repositories.TechnologyCustomRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import fr.formation.training.forum.dtos.TechnologyViewDto;
import fr.formation.training.forum.repositories.TechnologyJpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    private final TechnologyJpaRepository technologies;
    private final TechnologyCustomRepository technologiesCustomRepo;

    public TechnologyServiceImpl(TechnologyJpaRepository technologies, TechnologyCustomRepository technologiesCustomRepo) {
	    this.technologies = technologies;
        this.technologiesCustomRepo = technologiesCustomRepo;
    }

    @Transactional(readOnly = true)
    @Override
    public TechnologyViewDto getOne(Long id) {
	return technologies.findProjectedById(id)
            .orElseThrow(RessourceNotFoundException::new);
    }

    @Transactional(readOnly = true)
    @Override
    @Cacheable("technologies")
    public List<TechnologyViewDto> getAll() {
	return technologies.getAllProjected();
    }

    @Transactional
    @Override
    public void insertNative(TechnologyAddDto dto) {
        technologiesCustomRepo.insertConcat(dto);
        // technologiesCustomRepo.insertParameterized(dto);

    }
}
