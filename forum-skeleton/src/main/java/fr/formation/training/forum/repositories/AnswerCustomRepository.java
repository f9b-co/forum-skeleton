package fr.formation.training.forum.repositories;

import javax.persistence.*;

import org.springframework.stereotype.Repository;

@Repository
public class AnswerCustomRepository {

    private final EntityManager em;

    public AnswerCustomRepository(EntityManager em) {
	this.em = em;
    }

    public void deleteConcat(String id) { // 9
	String sql = "delete from answers where id = " + id;
	Query query = em.createNativeQuery(sql);
	query.executeUpdate();
    }

    public void deleteParameterized(String id) {
	String sql = "delete from answers where id = :id";
	Query query = em.createNativeQuery(sql);
	query.setParameter("id", id);
	query.executeUpdate();
    }
}
