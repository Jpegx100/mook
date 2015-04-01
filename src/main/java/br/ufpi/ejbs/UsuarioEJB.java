package br.ufpi.ejbs;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufpi.entidades.*;

@Stateless
public class UsuarioEJB {
	@PersistenceContext
	private EntityManager em;
	
	public void salvar(Usuario user){
		em.merge(user);
	}

	public Usuario login(Usuario user) {
		String consulta = "select u from Usuario u where u.email = :email";
		TypedQuery<Usuario> query = em.createQuery(consulta, Usuario.class);
		query.setParameter("email", user.getEmail());
		
		List<Usuario> result =	(List<Usuario>) query.getResultList();
		for(Usuario aux: result){
			if(aux.getSenha().equals(user.getSenha()))
				return aux;
		}
		return null;
	}
}
