package fr.formation.training.forum.services;

import java.util.List;

import fr.formation.training.forum.RessourceNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.training.forum.dtos.TechnologyViewDto;
import fr.formation.training.forum.repositories.TechnologyJpaRepository;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    private final TechnologyJpaRepository technologies;

    public TechnologyServiceImpl(TechnologyJpaRepository technologies) {
	this.technologies = technologies;
    }

    @Override
    public TechnologyViewDto getOne(Long id) {
	return technologies.findProjectedById(id)
            .orElseThrow(RessourceNotFoundException::new);
    }

    @Override
    public List<TechnologyViewDto> getAll() {
	return technologies.getAllProjected();
    }
}
