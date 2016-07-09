package vista;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.activation.MimetypesFileTypeMap;
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
	private String opcion1="¿Quienes somos?";
	private String opcion2="Realizar petición";
	private String opcion3="Consulta ciudadana";
	private String opcion4="Reposición";
	private String opcion5="Cerrar Sesion";
	private String emailsolicitante="";
	private String direccionsolicitante="";
	private BigInteger celularsolicitante;
	List<Peticion> lista;
	public static String email;
	public static String direccion;
	public static BigInteger celular;
	public static String nombre;
	public static String apellido;
	public static int cedula;
	String[] arrglos={"zip","rar","tgz","png","jpg","gif","xls","dot","pdf"};
	
	private String nombresolicitante;
	private String apellidosolicitante;
	private String bienvenida="Bienvenido";
	private String condicion="Buscando informacion";
	
	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public String getNombresolicitante() {
		this.nombresolicitante=nombre;
		return nombresolicitante;
	}

	public void setNombresolicitante(String nombresolicitante) {		
		this.nombresolicitante = nombresolicitante;
		this.nombresolicitante=nombre;
	}

	public String getApellidosolicitante() {
		this.apellidosolicitante=apellido;
		return apellidosolicitante;
	}

	public void setApellidosolicitante(String apellidosolicitante) {
		this.apellidosolicitante = apellidosolicitante;
		this.apellidosolicitante=apellido;
	}

	public String getBienvenida() {
		return bienvenida;
	}

	public void setBienvenida(String bienvenida) {
		this.bienvenida = bienvenida;
	}
	
	
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
	
	private StreamedContent file, file1;
    private String contentType;
    
    public  void FileDownloadView(String idformulario) {        
        int a=0;
        InputStream stream1 = null;
    	// Aquí la carpeta donde queremos buscar    	
    	String nombre;
    	for(int i=0;i<=8;i++){    		
			nombre=idformulario+"peticion."+arrglos[i];
			String Fichero = "D:\\tmp\\"+nombre;
	    	File fichero = new File(Fichero);
    		if (fichero.exists()){
    			try {
    				stream1 = new FileInputStream(fichero);    				    				
					String pathName="D:\\tmp\\"+nombre;
		        	content = new DefaultStreamedContent(stream1,"application/jpg","descarga.jpg");
					try {
						file = new DefaultStreamedContent(stream1, Files.probeContentType(Paths.get(pathName)), nombre);
					} catch (IOException e) {
						e.printStackTrace();
					}					
					a=1;
					break;
    			} catch (FileNotFoundException e){
    				e.printStackTrace();    				
    				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","El archivo no se encuentra."));
    			}    	        	
    		}
    		else{
    		}
        }
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
    
    private StreamedContent content;
    
    public void FileDownloadViewPrueba() {        
        /*InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("C:/Users/JuanJose/Downloads/footer.png");        
        file = new DefaultStreamedContent(stream, "image/png", "downloaded_optimus.png");
        return file;*/
    	try {       	
            //content = new DefaultStreamedContent(new FileInputStream(new File("C:\\Users\\JuanJose\\Downloads\\wallpaper.jpg")),Files.probeContentType(Paths.get("C:\\Users\\JuanJose\\Downloads\\wallpaper.jpg")),"primefaces_5.jpg");
        	InputStream inputstream = new FileInputStream("C:\\Users\\JuanJose\\Downloads\\wallpaper.jpg");
        	content = new DefaultStreamedContent(inputstream,"application/jpg","primefaces_5.jpg");
        }
        catch(Exception e){
            // Nothing
        	//content=null;
        }
    }
    
    public StreamedContent getContent() {
        return content;
    }
 
    public void setContent(StreamedContent content) {
        this.content = content;
    }
 
    public StreamedContent getFile() {
        return file;
    }
}
