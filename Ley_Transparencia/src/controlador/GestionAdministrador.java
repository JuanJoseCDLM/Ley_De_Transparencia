package controlador;

import java.math.BigInteger;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.primefaces.model.chart.PieChartModel;

import javax.mail.*;
import javax.mail.internet.*;


import vista.BeanEstadistica;
import vista.BeanGestionarSoli;
import modelo.Empresa;
import modelo.Estado;
import modelo.Gestionador;
import modelo.Peticion;
import modelo.Tipoinformacion;
import modelo.Usuario;

public class GestionAdministrador{
	private static final EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("LeyTransparencia");
	private static final EntityManager entitymanager = entitymanagerfactory.createEntityManager();
	private static final String String = null;
	private static Gestionador gestionador;
	private FacesMessage facesMessage;
	
	final String miCorreo = "respuestadesolicitudes@gmail.com";
    final String miContraseña = "respuestadesolicitudes&&";
    final String servidorSMTP = "smtp.gmail.com";
    final String puertoEnvio = "465";
    String mailReceptor = null;
    String asunto = "Respuesta de solicitud";
    String cuerpo = "Este es el cuerpo";
    public String ruta2;
	
	///////////CU6 Cambiar estado de la petición en caso de rechazo
	//paso2:Ingresa el número de la peticion
	//paso5:Ingresar la cedula del usuario solicitante
	public void Cambiar_estado_de_la_petición_en_caso_de_rechazo(String idpeticion){
	//paso3:valida que exista una peticion con ese número
		try{
			//String Snumero_peticion=String.valueOf(idpeticion);
			Query consulta=entitymanager.createQuery("SELECT p FROM Peticion p WHERE p.idPeticion = "+idpeticion);
			Peticion peticion=(Peticion) consulta.getSingleResult();
		
			//paso4:cambiar el estado de la peticion de Buscando informacion a Info.Confidencial
			Query consulta2=entitymanager.createQuery("SELECT e FROM Estado e WHERE e.idEstado =  1");
			Estado estado=(Estado)consulta2.getSingleResult();
			estado.removePeticion(peticion);
		
			Query consulta3=entitymanager.createQuery("SELECT e FROM Estado e WHERE e.idEstado =  2");
			Estado estado2=(Estado)consulta3.getSingleResult();
			estado2.addPeticion(peticion);
		
			peticion.setEstado(estado2);
		
			entitymanager.getTransaction().begin();
			entitymanager.persist(peticion);
			entitymanager.getTransaction().commit();
			//paso6:valida que el usuario solicitante con esa cedula exista
			try{		
			
			}catch(NoResultException a){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ese usuario solicitante no existe"));
			}
		}catch(NoResultException a){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La petición no existe"));
		}
	}
	
	///////////CU7 Cambiar estado de la petición en caso de recibir documento()
	//paso2:Ingresa el número de la peticion
	//paso5:Ingresar la cedula del usuario solicitante
	public void Cambiar_estado_de_la_petición_en_caso_de_recibir_documento(String idpeticion, String nombre){
		//paso3:valida que exista una peticion con ese número
		try{
			//String Snumero_peticion=String.valueOf(idpeticion);
			Query consulta=entitymanager.createQuery("SELECT p FROM Peticion p WHERE p.idPeticion = "+idpeticion);
			Peticion peticion=(Peticion) consulta.getSingleResult();
		
			//paso4:cambiar el estado de la peticion de Buscando informacion a Info. Encontrada
			Query consulta2=entitymanager.createQuery("SELECT e FROM Estado e WHERE e.idEstado =  2");
			Estado estado=(Estado)consulta2.getSingleResult();
			estado.addPeticion(peticion);
		
			Query consulta3=entitymanager.createQuery("SELECT e FROM Estado e WHERE e.idEstado =  1");
			Estado estado2=(Estado)consulta3.getSingleResult();
			estado2.removePeticion(peticion);
		
			mailReceptor=peticion.getUsuario().getEmailUsuario();
	        Properties props = new Properties();
	        props.put("mail.smtp.user", miCorreo);
	        props.put("mail.smtp.host", servidorSMTP);
	        props.put("mail.smtp.port", puertoEnvio);
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.socketFactory.port", puertoEnvio);
	        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.socketFactory.fallback", "false");
	        props.put("mail.smtp.starttls.enable","false" );

	        SecurityManager security = System.getSecurityManager();
	        try {
	            Authenticator auth = new autentificadorSMTP();
	            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(miCorreo, miContraseña);
	                }
	            });
	            // session.setDebug(true);
				//Store store = session.getStore("smtp");
				//store.connect(servidorSMTP, miCorreo, miContraseña);
	            MimeMessage msg = new MimeMessage(session);
	            msg.setText(cuerpo);
	            BodyPart adjunto = new MimeBodyPart();
	            
	            adjunto.setDataHandler(new DataHandler(new FileDataSource("D:\\tmp\\"+nombre)));
	            adjunto.setFileName(nombre);
	            
	            msg.setSubject(asunto);
	            msg.setFrom(new InternetAddress(miCorreo));
	            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mailReceptor));
	            
	            MimeMultipart multiParte = new MimeMultipart();
	            //multiParte.addBodyPart(adjunto);
	            multiParte.addBodyPart(adjunto);
	            msg.setContent(multiParte);
	            
	            Transport.send(msg);
	            //Transport.send(adjunto);
	        }catch (Exception mex) {
	            mex.printStackTrace();
	        }
			//javax.mail.SendFailedException: Send failure (javax.mail.MessagingException: Connection error (java.io.IOException: Error connecting to smtp.gmail.com, 465))
			
			
			peticion.setEstado(estado);
		
			entitymanager.getTransaction().begin();
			entitymanager.persist(peticion);
			entitymanager.getTransaction().commit();
			//paso6:valida que el usuario solicitante con esa cedula exista
			try{
				//paso7:Enviar al usuario solicitante un mensaje que indique el rechazo de la peticion
				//no tengo ni idea como se hace
			}catch(NoResultException a){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ese usuario solicitante no existe"));
				System.out.println("Ese usuario solicitante no existe");
			}
		}catch(NoResultException a){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La petición no existe"));
			System.out.println("La petición no existe");
		}
	}

	/////////CU8 Mostrar_las_peticiones_que_han_llegado_al_Usuario_Administrador()
	//paso2: obtene la cedula del usuario solicitante
	public List<Peticion> Mostrar_las_peticiones_que_han_llegado_al_Usuario_Administrador(int cedula){
		//paso3: Obtener el listado de peticiones realizadas por el usuario
		Query consulta4=entitymanager.createQuery("SELECT g FROM Gestionador g WHERE g.cedulaGestionador ="+cedula);
		Gestionador gestionador=(Gestionador) consulta4.getSingleResult();
		//paso4: Obtener por peticion la id, fecha y el estado
		List<Peticion> peticion=gestionador.getEmpresa().getPeticions();
		//paso5: Mostrar un listado de peticiones observando el id, la fecha de realizacion de la peticion, hora de realizacion de la peticion, y el estado actual de la peticion
		return peticion;
		//aun no se
	}
	/////////////////	
	private class autentificadorSMTP extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(miCorreo, miContraseña);
        }
    }
	
	public Object crearmodelo(){
		BeanEstadistica be = new BeanEstadistica();
		be.pieModel = new PieChartModel();
		
		Query buscarempresa = entitymanager.createQuery("SELECT e FROM Empresa e");
        
        List<Empresa> listaempresa = buscarempresa.getResultList();
        Peticion peticion = new Peticion();
        Empresa empresa = new Empresa();
        
        int idempresa=0;
        int c=0;
        
        for(int i=0;i<listaempresa.size();i++){
        	empresa=listaempresa.get(i);
        	idempresa=empresa.getIdEmpresa();        	
        	List<Peticion> listapeticion=empresa.getPeticions();
            be.pieModel.set(empresa.getNombreEmpresa(), listapeticion.size());
        }       
        
		be.pieModel.setTitle("Solicitudes por entidad");
		be.pieModel.setLegendPosition("w");
		be.pieModel.setShowDataLabels(true);
		return be.pieModel;
	}
	
	public Object crearmodelo2(){
		BeanEstadistica be = new BeanEstadistica();
		be.pieModel22 = new PieChartModel();
		
		Query buscarusuario = entitymanager.createQuery("SELECT u FROM Usuario u");
        
        List<Usuario> listausuario = buscarusuario.getResultList();
        Peticion peticion = new Peticion();
        Usuario usuario = new Usuario();
        
        int idusuario=0;
        int c=0;
        
        for(int i=0;i<listausuario.size();i++){
        	usuario=listausuario.get(i);
        	idusuario=usuario.getCedulaUsuario();        	
        	List<Peticion> listapeticion=usuario.getPeticions();
            be.pieModel22.set(Integer.toString(usuario.getCedulaUsuario()), listapeticion.size());
        }       
        
		be.pieModel22.setTitle("Solicitudes por usuario");
		be.pieModel22.setLegendPosition("w");
		be.pieModel22.setShowDataLabels(true);
		return be.pieModel22;
	}
	
	public Object crearmodelo3(){
		BeanEstadistica be = new BeanEstadistica();
		be.pieModel33 = new PieChartModel();
		
		Query buscarestado = entitymanager.createQuery("SELECT e FROM Estado e");
        
        List<Estado> listaestado = buscarestado.getResultList();
        Peticion peticion = new Peticion();
        Estado estado = new Estado();
        
        int idestado=0;
        int c=0;
        
        for(int i=0;i<listaestado.size();i++){
        	estado=listaestado.get(i);
        	idestado=estado.getIdEstado();        	
        	List<Peticion> listapeticion=estado.getPeticions();
            be.pieModel33.set(estado.getTipoEstado(), listapeticion.size());
        }       
        
		be.pieModel33.setTitle("Solicitudes por estado");
		be.pieModel33.setLegendPosition("w");
		be.pieModel33.setShowDataLabels(true);
		return be.pieModel33;
	}	
}
