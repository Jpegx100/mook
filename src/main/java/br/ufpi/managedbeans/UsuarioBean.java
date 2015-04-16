package br.ufpi.managedbeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.primefaces.model.UploadedFile;

import br.ufpi.ejbs.UsuarioEJB;
import br.ufpi.entidades.Usuario;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UsuarioEJB uejb;

	@Inject
	private Usuario user;
	
	private UploadedFile file;

	public void login() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Usuario usuario = uejb.login(user);
		if (usuario != null) {
			Map<String, Object> session = facesContext.getExternalContext()
					.getSessionMap();
			session.put("usuario", usuario);
			try {
				facesContext.getExternalContext().redirect("home.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				facesContext.getExternalContext().redirect("index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void logout() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		setUsuarioLogado(new Usuario());
		try {
			facesContext.getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Usuario getUsuarioLogado() {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		return (Usuario) session.get("usuario");
	}

	public void setUsuarioLogado(Usuario u) {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		session.put("usuario", u);
	}

	public void salvar() {
		//user.setImagem(file.getContents());
		uejb.salvar(user);
		login();
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	
}
