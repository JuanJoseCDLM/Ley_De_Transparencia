package vista;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import controlador.GestionUsuario;
import modelo.Peticion;

@ManagedBean
@ViewScoped
public class BeanConsultaCiudadana implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String opcion1="¿Quienes somos?";
	private String opcion2="Realizar petición";
	private String opcion3="Consulta ciudadana";
	private String opcion4="Reposición";
	private String emailsolicitante="";
	private String direccionsolicitante="";
	private BigInteger celularsolicitante;
	List<Peticion> lista;
	public static String email;
	public static String direccion;
	public static BigInteger celular;
	public static int cedula;
	
	public List<Peticion> getLista() {
		return lista;
	}

	public void setLista(List<Peticion> lista) {
		this.lista = lista;
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
	
	public String getEmailsolicitante() {
		emailsolicitante=email;
		return emailsolicitante;
	}
	
	public void setEmailsolicitante(String emailsolicitante) {
		this.emailsolicitante = emailsolicitante;
	}
	
	public String getDireccionsolicitante() {
		direccionsolicitante=direccion;
		return direccionsolicitante;
	}
	
	public void setDireccionsolicitante(String direccionsolicitante) {
		this.direccionsolicitante = direccionsolicitante;
	}
	
	public BigInteger getCelularsolicitante() {
		celularsolicitante=celular;
		return celularsolicitante;
	}
	
	public void setCelularsolicitante(BigInteger celularsolicitante) {
		this.celularsolicitante = celularsolicitante;
	}
	
	@PostConstruct
	public void init() {
		GestionUsuario gu = new GestionUsuario();
		lista=gu.Mostrar_las_peticiones_que_ha_realizado_un_solicitante(cedula);
	}
}
