package vista;

import javax.faces.bean.ManagedBean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
 
@ManagedBean
@ViewScoped
public class BeanGestionarSoli implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String textlabelnumerosolicitud="N° de registro de la solicitud:";
	private String textlabelsubirinfo="Subir información:";
	private String textlabelestadosolu="Estado de solicitud:";	
	private String textbotonenviar="Enviar";	
	private String estado;
	private Map<String,String> estados;
	private int i=2;	
	private String opcion1="¿Quienes somos?";
	private String opcion2="Solicitudes";
	private String opcion3="Gestionar solicitudes";
	private String opcion4="Reportes";
	
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

	public String getTextlabelnumerosolicitud() {
		return textlabelnumerosolicitud;
	}

	public void setTextlabelnumerosolicitud(String textlabelnumerosolicitud) {
		this.textlabelnumerosolicitud = textlabelnumerosolicitud;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Map<String, String> getEstados() {
		return estados;
	}

	public void setEstados(Map<String, String> estados) {
		this.estados = estados;
	}

	public String getTextlabelsubirinfo() {
		return textlabelsubirinfo;
	}

	public void setTextlabelsubirinfo(String textlabelsubirinfo) {
		this.textlabelsubirinfo = textlabelsubirinfo;
	}

	public String getTextlabelestadosolu() {
		return textlabelestadosolu;
	}

	public void setTextlabelestadosolu(String textlabelestadosolu) {
		this.textlabelestadosolu = textlabelestadosolu;
	}

	public String getTextbotonenviar() {
		return textbotonenviar;
	}

	public void setTextbotonenviar(String textbotonenviar) {
		this.textbotonenviar = textbotonenviar;
	}
	
	@PostConstruct
    public void init() {
        estados  = new HashMap<String, String>();
        estados.put("Información Encontrada", "Información Encontrada");
        estados.put("Información Confidencial", "Información Confidencial");
        estados.put("Buscar Información", "Buscar Información");
        estados.put("Derecho de reposición", "Derecho de reposición");
    }
}
