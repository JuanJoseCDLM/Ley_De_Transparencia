package vista;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import controlador.GestionUsuario;
import modelo.Peticion;

@ManagedBean
@ViewScoped
public class BeanConsultaCiudadana implements Serializable{
	
	transient BeanConsultaCiudadana BeanConsultaCiudadan;
	private static final long serialVersionUID = 1L;
	private String opcion1="�Quienes somos?";
	private String opcion2="Realizar petici�n";
	private String opcion3="Consulta ciudadana";
	private String opcion4="Reposici�n";
	private String opcion5="Cerrar Sesion";
	private String emailsolicitante="";
	private String direccionsolicitante="";
	private BigInteger celularsolicitante;
	List<Peticion> lista;
	public static String email;
	public static String direccion;
	public static BigInteger celular;
	public static int cedula;
	String[] arrglos={"zip","rar","tgz","png","jpg","gif","xls","dot","pdf"};
	
	
	public String getOpcion5() {
		return opcion5;
	}

	public void setOpcion5(String opcion5) {
		this.opcion5 = opcion5;
	}

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
	
	private StreamedContent file;
    
    public Object FileDownloadView(String idformulario) throws IOException{        
        System.out.println(idformulario);
        InputStream stream1 = null;
    	// Aqu� la carpeta donde queremos buscar    	
    	String nombre;
    	for(int i=0;i<=8;i++){    		
			nombre=idformulario+"peticion."+arrglos[i];
			String Fichero = "D:\\tmp\\"+nombre;
	    	File fichero = new File(Fichero);
	    	//stream1 = new FileInputStream(fichero);
    		if (fichero.exists()){
    			System.out.println("El fichero " + Fichero + " existe");
    			try {
    				stream1 = new FileInputStream(fichero);
					file = new DefaultStreamedContent(stream1, "image/"+arrglos[i], nombre);
					System.out.println("El fichero " + Fichero + " se descargo");
					//
					return file;
    			} catch (FileNotFoundException e){
    				System.out.println("Frfregtrgtrt");
    				// TODO Auto-generated catch block
    				e.printStackTrace();    				
    				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","El archivo no se encuentra."));
    				return null;
    			}
    	        	
    		}
    		else{
    			//stream1.close();
    			System.out.println("El fichero " + Fichero + " no existe");
    		}
        }
    	//stream1.close();
    	return null;
    }
 
    public StreamedContent getFile() {
        return file;
    }
    
    public String cerrarsesion(){
    	BeanMenu bm = new BeanMenu();
		bm.email=null;
		bm.direccion=null;

		BeanRegistrarSolicitud bs = new BeanRegistrarSolicitud();
		bs.direccion = null;
		bs.email = null;
		bs.nombre = null;
		bs.apellido = null;
		bs.cedula = 0;
		
		BeanConsultaCiudadana bcc= new BeanConsultaCiudadana();
		bcc.cedula = 0;
		bcc.email = null;
		bcc.direccion = null;
		
		BeanRegistrarReposicion brr = new BeanRegistrarReposicion();
		brr.cedula=0;
		brr.email = null;
		brr.direccion = null;
		brr.celular = null;
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate(); //Cierre de sesion
        }
        return "index.xhtml";    	
    }
}
