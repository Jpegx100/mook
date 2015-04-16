package br.ufpi.managedbeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufpi.ejbs.AutorEjb;
import br.ufpi.entidades.Autor;

@Named
@ViewScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AutorEjb aejb;

	@Inject
	private Autor autor;
	
	public List<Autor> autores(){
		return aejb.autores();	
	}
	
	public void excluir(Autor a){
		aejb.excluir(a);
	}
	
	public void salvar() {
		//user.setImagem(file.getContents());
		aejb.salvar(autor);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
}
