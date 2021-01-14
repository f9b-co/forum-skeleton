package fr.formation.training.forum.repositories;

import javax.persistence.*;

import org.springframework.stereotype.Repository;

import fr.formation.training.forum.dtos.TechnologyAddDto;

@Repository
public class TechnologyCustomRepository {

    private final EntityManager em;

    public TechnologyCustomRepository(EntityManager em) {
        this.em = em;
    }

    public void insertConcat(TechnologyAddDto dto) {
        String sql = "insert into technologies (technology_name, technology_rating) values (";
        sql += "\"" + dto.getName() + "\", ";
        sql += dto.getRating();
        sql += ")";
        Query query = em.createNativeQuery(sql);
        query.executeUpdate();
    }

    public void insertParameterized(TechnologyAddDto dto) {
        String sql = "insert into technologies (technology_name, technology_rating) values (:name, :rating)";
        Query query = em.createNativeQuery(sql);
        query.setParameter("name", dto.getName());
        query.setParameter("rating", dto.getRating());
        query.executeUpdate();
    }
}
