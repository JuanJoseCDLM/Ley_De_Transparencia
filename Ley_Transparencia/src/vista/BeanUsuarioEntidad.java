package vista;

import javax.faces.bean.ManagedBean;

import java.io.Serializable;
 
@ManagedBean
public class BeanUsuarioEntidad implements Serializable{
	private static final long serialVersionUID = 1L;
	private String textlabelusuario="Usuario:";
	private String textlabeentidad="Entidad:";
	private String textlabelcontrasena="Contrase�a:";
	private String textlabelrepetircontra="Contrase�a:";
	
	private String textcajausuario="Usuario ...";
	private String textcajaentidad="Entidad ...";
	private String textcajacontrasena="Contrase�a ...";
	private String textcajarepetircontra="Contrase�a ...";
	
	private String textbotonregistrarse="Registrarse";
	
	public String getTextbotonregistrarse() {
		return textbotonregistrarse;
	}

	public void setTextbotonregistrarse(String textbotonregistrarse) {
		this.textbotonregistrarse = textbotonregistrarse;
	}

	public String getTextlabelusuario() {
		return textlabelusuario;
	}
	
	public void setTextlabelusuario(String textlabelusuario) {
		this.textlabelusuario = textlabelusuario;
	}
	
	public String getTextlabeentidad() {
		return textlabeentidad;
	}
	
	public void setTextlabeentidad(String textlabeentidad) {
		this.textlabeentidad = textlabeentidad;
	}
	
	public String getTextlabelcontrasena() {
		return textlabelcontrasena;
	}
	
	public void setTextlabelcontrasena(String textlabelcontrasena) {
		this.textlabelcontrasena = textlabelcontrasena;
	}
	
	public String getTextlabelrepetircontra() {
		return textlabelrepetircontra;
	}
	
	public void setTextlabelrepetircontra(String textlabelrepetircontra) {
		this.textlabelrepetircontra = textlabelrepetircontra;
	}
	
	public String getTextcajausuario() {
		return textcajausuario;
	}
	
	public void setTextcajausuario(String textcajausuario) {
		this.textcajausuario = textcajausuario;
	}
	
	public String getTextcajaentidad() {
		return textcajaentidad;
	}
	
	public void setTextcajaentidad(String textcajaentidad) {
		this.textcajaentidad = textcajaentidad;
	}
	
	public String getTextcajacontrasena() {
		return textcajacontrasena;
	}
	
	public void setTextcajacontrasena(String textcajacontrasena) {
		this.textcajacontrasena = textcajacontrasena;
	}
	
	public String getTextcajarepetircontra() {
		return textcajarepetircontra;
	}
	
	public void setTextcajarepetircontra(String textcajarepetircontra) {
		this.textcajarepetircontra = textcajarepetircontra;
	}
}
