package vista;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;
import controlador.GestionAdministrador;
import javax.annotation.PostConstruct;
import modelo.Peticion;
import modelo.Gestionador;

@ManagedBean
@ViewScoped
public class BeanSolicitud implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int i=1;	
	private String opcion1="¿Quienes somos?";
	private String opcion2="Solicitudes";
	private String opcion3="Gestionar solicitudes";
	private String opcion4="Reportes";
	public static int cedulagestionador;
	
	List<Peticion> lista;
	
	public List<Peticion> getLista() {
		return lista;
	}

	public void setLista(List<Peticion> lista) {
		this.lista = lista;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public String getOpcion1() {
		return opcion1;
	}

	public void setOpcion1(String opcion1) {
		this.opcion1 = opcion1;
	}

	public String getOpcion2() {
		return opcion2;
	}

	public void setOpcion2(String opcion2) {
		this.opcion2 = opcion2;
	}

	public String getOpcion3() {
		return opcion3;
	}

	public void setOpcion3(String opcion3) {
		this.opcion3 = opcion3;
	}

	public String getOpcion4() {
		return opcion4;
	}

	public void setOpcion4(String opcion4) {
		this.opcion4 = opcion4;
	}
	
	@PostConstruct
	public void init() {
		GestionAdministrador usuario_Administrador=new GestionAdministrador ();		
		lista=(List<Peticion>) usuario_Administrador.Mostrar_las_peticiones_que_han_llegado_al_Usuario_Administrador(cedulagestionador);		
	}	
}
