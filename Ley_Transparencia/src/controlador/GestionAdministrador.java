package controlador;

import java.math.BigInteger;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.primefaces.model.chart.PieChartModel;

import vista.BeanEstadistica;
import vista.BeanRegistrarSolicitud;
import modelo.Empresa;
import modelo.Estado;
import modelo.Gestionador;
import modelo.Peticion;
import modelo.Usuario;

public class GestionAdministrador {
	private static final EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("LeyTransparencia");
	private static final EntityManager entitymanager = entitymanagerfactory.createEntityManager();
	private static Gestionador gestionador;
	private FacesMessage facesMessage;
	
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
	public void Cambiar_estado_de_la_petición_en_caso_de_recibir_documento(String idpeticion){
		//paso3:valida que exista una peticion con ese número
		try{
			//String Snumero_peticion=String.valueOf(idpeticion);
			Query consulta=entitymanager.createQuery("SELECT p FROM Peticion p WHERE p.idPeticion = "+idpeticion);
			Peticion peticion=(Peticion) consulta.getSingleResult();
		
			//paso4:cambiar el estado de la peticion de Buscando informacion a Info. Encontrada
			Query consulta2=entitymanager.createQuery("SELECT e FROM Estado e WHERE e.idEstado =  1");
			Estado estado=(Estado)consulta2.getSingleResult();
			estado.addPeticion(peticion);
		
			Query consulta3=entitymanager.createQuery("SELECT e FROM Estado e WHERE e.idEstado =  2");
			Estado estado2=(Estado)consulta3.getSingleResult();
			estado2.removePeticion(peticion);
		
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
		System.out.println("el usuario existe");
		return peticion;
		//aun no se
	}
	
	
	/////////////////
	
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
        
		be.pieModel.setTitle("Solicitudes por empresa");
		be.pieModel.setLegendPosition("w");
		be.pieModel.setShowDataLabels(true);
		return be.pieModel;
	}
	
	
}
