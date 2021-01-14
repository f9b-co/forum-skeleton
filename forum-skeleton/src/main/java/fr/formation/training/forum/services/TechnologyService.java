package fr.formation.training.forum.services;

import java.util.List;

import fr.formation.training.forum.dtos.*;

public interface TechnologyService {

    List<TechnologyViewDto> getAll();

    TechnologyViewDto getOne(Long id);

    void deleteBad();

    TechnologyInterfaceDto getOneByInterface(Long id);

    void insertNative(TechnologyAddDto dto);
}
