package br.ufpi.ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufpi.entidades.*;

@Stateless
public class AutorEjb {
	@PersistenceContext
	private EntityManager em;

	public void salvar(Autor user) {
		em.merge(user);
	}

	public List<Autor> autores() {
		String consulta = "select u from Autor u";
		TypedQuery<Autor> query = em.createQuery(consulta, Autor.class);

		List<Autor> result = (List<Autor>) query.getResultList();
		return result;
	}
	
	public void excluir(Autor a){
		em.remove(em.merge(a));
	}
}
