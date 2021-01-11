package fr.formation.training.forum.services;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.training.forum.ResourceNotFoundException;
import fr.formation.training.forum.dtos.TechnologyViewDto;
import fr.formation.training.forum.repositories.TechnologyJpaRepository;

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
		.orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional(readOnly = true)
    @Override
    @Cacheable("technologies")
    public List<TechnologyViewDto> getAll() {
	return technologies.getAllProjected();
    }

    @Transactional
    @Override
    public void deleteBad() {
	// Questions : 1 et 8
	technologies.deleteById(4L); // OK
	technologies.deleteById(8L); // KO
    }
}
