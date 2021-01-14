package fr.formation.training.forum.services;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.training.forum.ResourceNotFoundException;
import fr.formation.training.forum.dtos.*;
import fr.formation.training.forum.repositories.*;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    private final TechnologyJpaRepository technologies;

    private final TechnologyCustomRepository customRespo;

    public TechnologyServiceImpl(TechnologyJpaRepository technologies,
	    TechnologyCustomRepository customRespo) {
	this.technologies = technologies;
	this.customRespo = customRespo;
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

    // public class TechnologyServiceImplProxy extends TechnologyServiceImpl {
    //
    // boolean modified = false;
    //
    // String query = "update Answer a set ";
    //
    // public TechnologyServiceImplProxy(
    // TechnologyJpaRepository technologies) {
    // super(technologies);
    // // TODO Auto-generated constructor stub
    // }
    //
    // void setText(String text) {
    // if(!text.equals(currentText)) {
    //
    // modified = true;
    // }
    // }
    //
    // void setPhrase(String phrase) {
    // if(!phrase.equals(currentPhrase)) {
    // modified = true;
    // }
    // }
    //
    //
    // public boolean isModified() {
    // return modified;
    // }
    //
    //
    // //
    // //
    // //
    // @Override
    // public void deleteBad() {
    // // @Transactional:
    // // BEGIN TRANSACTION
    // // TRY
    // // super.deleteBad();
    // // COMMIT
    // // CATCH
    // // ROLLBACK
    // // END TRANSACTION
    // }
    // }
    @Transactional(readOnly = true)
    @Override
    public TechnologyInterfaceDto getOneByInterface(Long id) {
	return technologies.getById(id);
    }

    @Transactional
    @Override
    public void insertNative(TechnologyAddDto dto) {
	customRespo.insertConcat(dto);
	// customRespo.insertParameterized(dto);
    }
}
