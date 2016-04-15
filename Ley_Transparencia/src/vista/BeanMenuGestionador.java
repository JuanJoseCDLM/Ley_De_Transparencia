package vista;

import java.io.Serializable;
import java.util.List;


//import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import modelo.Estado;

@ManagedBean
@ViewScoped
public class BeanMenuGestionador implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String opcion1="¿Quienes somos?";
	private String opcion2="Solicitudes";
	private String opcion3="Gestionar solicitudes";
	private String opcion4="Reportes";
	private String opcion5="Cerrar Sesion";

	private int i=0;

	public static String login;
	private List<Estado> lista2;
	private String cedula="";
	
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
	
	public String getCedula() {
		cedula=login;
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
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
	
	public String getOpcion5() {
		return opcion5;
	}

	public void setOpcion5(String opcion5) {
		this.opcion5 = opcion5;
	}
	

}
