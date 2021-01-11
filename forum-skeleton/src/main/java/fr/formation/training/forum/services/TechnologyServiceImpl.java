package fr.formation.training.forum.services;

import java.util.List;

import fr.formation.training.forum.RessourceNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import fr.formation.training.forum.dtos.TechnologyViewDto;
import fr.formation.training.forum.repositories.TechnologyJpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    private final TechnologyJpaRepository technologies;

    public TechnologyServiceImpl(TechnologyJpaRepository technologies) {
	this.technologies = technologies;
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
}
