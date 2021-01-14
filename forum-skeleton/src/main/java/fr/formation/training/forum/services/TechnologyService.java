package fr.formation.training.forum.services;

import java.util.List;

import fr.formation.training.forum.dtos.TechnologyAddDto;
import fr.formation.training.forum.dtos.TechnologyViewDto;

public interface TechnologyService {

    List<TechnologyViewDto> getAll();

    TechnologyViewDto getOne(Long id);

    void insertNative(TechnologyAddDto dto);
}
