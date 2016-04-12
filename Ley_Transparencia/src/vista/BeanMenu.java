package vista;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
 
@ManagedBean
public class BeanMenu implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String opcion1="¿Quienes somos?";
	private String opcion2="Realizar petición";
	private String opcion3="Consulta ciudadana";
	private String opcion4="Reposición";
	
	public static String a;
	private String usuariosolicitante;

	public void setUsuariosolicitante(String usuariosolicitante) {
		//this.usuariosolicitante = usuariosolicitante;
		this.usuariosolicitante = a;
		System.out.println(usuariosolicitante+" 2");
		
	}
	
	public String getUsuariosolicitante() {
		this.usuariosolicitante = a;
		return this.usuariosolicitante;
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
}
