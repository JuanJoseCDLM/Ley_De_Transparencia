package vista;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Peticion;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import controlador.GestionUsuario;

@ManagedBean
@ViewScoped
public class BeanRegistrarReposicion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String opcion1="�Quienes somos?";
	private String opcion2="Realizar petici�n";
	private String opcion3="Consulta ciudadana";
	private String opcion4="Reposici�n";
	private String textlabelentidad="Entidad:*";
	private String textlabelnombre="Nombre:";
	private String textlabelcargo="Cargo:";
	private String textlabelarea="Area a la que se solicita la info:*";
	private String textlabeltipo="Tipo de informacion:";
	private String textlabela�o="A�o de la informacion:";
	private String textlabelobservaciones="Observaciones";
	
	private String checkboxcooreo="Comunicarse con usted a traves de correo.";
	private String checkboxcelular="Comunicarse con usted a traves de celular.";
	private String checkboxdireccionfisica="Comunicarse con usted a traves de direcci�n fisica.";
	
	private String tectcajanombre="Nombre ...";
	private String tectcajacargo="Cargo ...";
	
	private String textbotonenviar="Enviar";
	private String textbotonprevisualizacion="Previsualizaci�n";

	private String empresa;
	private Map<String,String> empresas;
	private String area;
	private Map<String,String> areas;
	private String tipo;
	private Map<String,String> tipos;
	private String a�oinfo;	
	private Map<String,String> a�os;
	
	private String emailsolicitante="";
	private String direccionsolicitante="";
	private BigInteger celularsolicitante;
	private String observaciones;
	private String ventanaemergente;
	GestionUsuario gessolicitante = new GestionUsuario();

	public static String email;
	public static String direccion;
	public static BigInteger celular;
	public static String nombre;
	public static String apellido;
	public static int cedula;
	public static String ventana;

	Date dato=null;
	String ciudad="Bogota";
	public String observacion="";	
	private StreamedContent file;
	private String peticion;
	private List<String> peticiones;
	
	public String getPeticion() {
		return peticion;
	}

	public void setPeticion(String peticion) {
		this.peticion = peticion;
	}	
	
	public List<String> getPeticiones() {
		return peticiones;
	}

	public void setPeticiones(List<String> peticiones) {
		this.peticiones = peticiones;
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
	
	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public String getVentanaemergente() {
		ventanaemergente=ventana;
		return ventanaemergente;
	}

	public void setVentanaemergente(String ventanaemergente) {
		this.ventanaemergente = ventanaemergente;
		this.ventanaemergente = ventana;
	}	
	public String getObservaciones() {
		this.observaciones=observacion;
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		observacion=observaciones;
		this.observaciones=observacion;
	}
	
	public String getEmailsolicitante() {
		this.emailsolicitante=email;
		return emailsolicitante;
	}

	public void setEmailsolicitante(String emailsolicitante) {
		this.emailsolicitante = emailsolicitante;
		this.emailsolicitante=email;
	}

	public String getDireccionsolicitante() {
		this.direccionsolicitante=direccion;
		return direccionsolicitante;
	}

	public void setDireccionsolicitante(String direccionsolicitante) {
		this.direccionsolicitante = direccionsolicitante;
		this.direccionsolicitante=direccion;
	}

	public BigInteger getCelularsolicitante() {
		this.celularsolicitante=celular;
		return celularsolicitante;
	}

	public void setCelularsolicitante(BigInteger celularsolicitante) {
		this.celularsolicitante = celularsolicitante;
		this.celularsolicitante=celular;
	}

	public String getTextbotonprevisualizacion() {
		return textbotonprevisualizacion;
	}

	public void setTextbotonprevisualizacion(String textbotonprevisualizacion) {
		this.textbotonprevisualizacion = textbotonprevisualizacion;
	}

	public String getCheckboxcooreo() {
		return checkboxcooreo;
	}

	public void setCheckboxcooreo(String checkboxcooreo) {
		this.checkboxcooreo = checkboxcooreo;
	}

	public String getCheckboxcelular() {
		return checkboxcelular;
	}

	public void setCheckboxcelular(String checkboxcelular) {
		this.checkboxcelular = checkboxcelular;
	}

	public String getCheckboxdireccionfisica() {
		return checkboxdireccionfisica;
	}

	public void setCheckboxdireccionfisica(String checkboxdireccionfisica) {
		this.checkboxdireccionfisica = checkboxdireccionfisica;
	}

	public String getTectcajanombre() {
		return tectcajanombre;
	}

	public void setTectcajanombre(String tectcajanombre) {
		this.tectcajanombre = tectcajanombre;
	}

	public String getTectcajacargo() {
		return tectcajacargo;
	}

	public void setTectcajacargo(String tectcajacargo) {
		this.tectcajacargo = tectcajacargo;
	}

	public String getTextlabelnombre() {
		return textlabelnombre;
	}

	public void setTextlabelnombre(String textlabelnombre) {
		this.textlabelnombre = textlabelnombre;
	}

	public String getTextlabelcargo() {
		return textlabelcargo;
	}

	public void setTextlabelcargo(String textlabelcargo) {
		this.textlabelcargo = textlabelcargo;
	}

	public String getTextbotonenviar() {
		return textbotonenviar;
	}

	public void setTextbotonenviar(String textbotonenviar) {
		this.textbotonenviar = textbotonenviar;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Map<String, String> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(Map<String, String> empresas) {
		this.empresas = empresas;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Map<String, String> getAreas() {
		return areas;
	}

	public void setAreas(Map<String, String> areas) {
		this.areas = areas;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Map<String, String> getTipos() {
		return tipos;
	}

	public void setTipos(Map<String, String> tipos) {
		this.tipos = tipos;
	}

	public String getA�o() {
		return a�oinfo;
	}

	public void setA�o(String a�o) {
		this.a�oinfo = a�o;
	}

	public Map<String, String> getA�os() {
		return a�os;
	}

	public void setA�os(Map<String, String> a�os) {
		this.a�os = a�os;
	}

	public String getTextlabelentidad() {
		return textlabelentidad;
	}
	
	public void setTextlabelentidad(String textlabelentidad) {
		this.textlabelentidad = textlabelentidad;
	}
	
	public String getTextlabelarea() {
		return textlabelarea;
	}
	
	public void setTextlabelarea(String textlabelarea) {
		this.textlabelarea = textlabelarea;
	}
	
	public String getTextlabeltipo() {
		return textlabeltipo;
	}
	
	public void setTextlabeltipo(String textlabeltipo) {
		this.textlabeltipo = textlabeltipo;
	}
	
	public String getTextlabela�o() {
		return textlabela�o;
	}
	
	public void setTextlabela�o(String textlabela�o) {
		this.textlabela�o = textlabela�o;
	}
	
	public String getTextlabelobservaciones() {
		return textlabelobservaciones;
	}
	
	public void setTextlabelobservaciones(String textlabelobservaciones) {
		this.textlabelobservaciones = textlabelobservaciones;
	}
	
	@PostConstruct
    public void init() {
        empresas  = new HashMap<String, String>();
        empresas.put("CODENSA", "CODENSA");
        
        areas  = new HashMap<String, String>();
        areas.put("Sistemas", "Sistemas");
        areas.put("Finanzas", "Finanzas");
        areas.put("Recursos Humanos", "Recursos Humanos");
        
        tipos  = new HashMap<String, String>();
        tipos.put("Balance financiero", "Balance financiero");
        tipos.put("Contratos", "Contratos");
        
        peticiones = new ArrayList();
        peticiones=gessolicitante.peticiones(cedula);
    }
	
	public String registrar_reposicion(){
		Peticion peticion = new Peticion();
		
		SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
		Calendar fecha = Calendar.getInstance();
		int a�o = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH)+1;
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		String a�ostring = Integer.toString(a�o);
		String messtring = Integer.toString(mes);
		String diastring = Integer.toString(dia);
		String fechafinal=a�ostring+"-"+messtring+"-"+diastring;
		
		try{
			dato = formatofecha.parse(fechafinal);
		}catch (ParseException e){
			e.printStackTrace();
		}
		System.out.println(fechafinal);
		System.out.println(cedula);
		gessolicitante.registrarpeticion(dato, observaciones,area,empresa,tipo,a�oinfo, cedula);
		return null;
	}
	
	public Object previsualizacion()throws FileNotFoundException{
		StreamedContent file;
		GestionUsuario gessolicitante = new GestionUsuario();
		file=(StreamedContent) gessolicitante.previsual(dato, empresa, ciudad, observaciones, direccion, email, cedula, nombre, apellido);											
		if(file != null)
        	return file;
        return null;
	}
}

