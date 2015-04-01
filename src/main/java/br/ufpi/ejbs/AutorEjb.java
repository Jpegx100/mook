package br.ufpi.ejbs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AutorEjb {
	@PersistenceContext
	private EntityManager em;
}
