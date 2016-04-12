package controlador;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 
import java.net.*;
import java.io.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/*import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.dom4j.DocumentException;*/






import vista.BeanConsultaCiudadana;
import vista.BeanIndex;
import vista.BeanMenu;
import vista.BeanRegistrarSolicitud;
import vista.BeanSolicitud;
import modelo.Ciudad;
import modelo.Departamento;
import modelo.Empresa;
import modelo.Estado;
import modelo.Gestionador;
import modelo.Peticion;
import modelo.Tipoinformacion;
import modelo.Usuario;

public class GestionUsuario {
	private static final EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("LeyTransparencia");
	private static final EntityManager entitymanager = entitymanagerfactory.createEntityManager();
	private static final String buscarusuario="SELECT u FROM Usuario u";
	private static final String buscarpeticion="SELECT p FROM Peticion p";
	private static Usuario usuario;
	private static Gestionador gestionador;
	private static Peticion peticion;
	private static Empresa empresa;
	private static Tipoinformacion tipoinformacion;
	int idtipo=0, idempresa=0,idempresaciudad=0,idempresadepartamento=0, idpeticion=0;
	String nombre, apellido;
	StreamedContent file;
	
	BigInteger celularsolicitante=new BigInteger("3102132231", 10);
	
	public Usuario RegistrarSolicitante (int cedula, String nombre, String apellido, BigInteger celular, String email, String direccion, String contrasena){
		String cedulaso=Integer.toString(cedula);
		usuario = new Usuario();
		try{	
			Query consulta =entitymanager.createQuery("Select u From Usuario u Where u.cedulaUsuario="+cedula);
			usuario = (Usuario) consulta.getSingleResult();
			System.out.println(consulta);
		}catch(NoResultException e){			
			BeanRegistrarSolicitud bs = new BeanRegistrarSolicitud();
			
			usuario.setCedulaUsuario(cedula);
			usuario.setNombreUsuario(nombre);
			usuario.setApellidoUsuario(apellido);
			usuario.setCelularUsuario(celular);
			bs.celular=usuario.getCelularUsuario();
			usuario.setEmailUsuario(email);			
			bs.email =usuario.getEmailUsuario();
			usuario.setDireccionUsuario(direccion);
			bs.direccion=usuario.getDireccionUsuario();
			usuario.setContrasenaUsuario(contrasena);
			usuario.setEstadoUsuario(true);
			bs.nombre=usuario.getNombreUsuario();
			bs.apellido=usuario.getApellidoUsuario();
			entitymanager.getTransaction().begin();
			entitymanager.persist(usuario);
			entitymanager.getTransaction().commit();			
			return usuario;
		}				
		return null;
	}

	public Usuario AutenticarSolicitante(String cedula, String contrasena){
		int login=12; String password="abc";
		Query queryautenticar=entitymanager.createQuery(buscarusuario);
		List<Usuario> listausuarios = queryautenticar.getResultList();
		usuario = new Usuario();
		
		for (int i=0;i<listausuarios.size();i++){			
			usuario=listausuarios.get(i);
			login=usuario.getCedulaUsuario();
			password=usuario.getContrasenaUsuario();
			
			usuario=listausuarios.get(i);
			
			if (login==Integer.parseInt(cedula) && password.equals(contrasena)){												
				BeanMenu bm = new BeanMenu();
				bm.a=Integer.toString(usuario.getCedulaUsuario());
				
				BeanRegistrarSolicitud bs = new BeanRegistrarSolicitud();
				bs.direccion = usuario.getDireccionUsuario();
				bs.email = usuario.getEmailUsuario();
				bs.celular = usuario.getCelularUsuario();
				bs.nombre = usuario.getNombreUsuario();
				bs.apellido = usuario.getApellidoUsuario();
				bs.cedula = usuario.getCedulaUsuario();
				
				BeanConsultaCiudadana bcc= new BeanConsultaCiudadana();
				bcc.cedula=usuario.getCedulaUsuario();
				bcc.email = usuario.getEmailUsuario();
				bcc.direccion = usuario.getDireccionUsuario();
				bcc.celular = usuario.getCelularUsuario();				
				return usuario;
			} 	
			else{
				try{
					Query consultaugestionador = entitymanager.createQuery("SELECT g FROM Gestionador g WHERE g.cedulaGestionador ="+cedula+" and g.contrasenaGestionador="+"\""+contrasena+"\"");
					gestionador = (Gestionador) consultaugestionador.getSingleResult();
					BeanSolicitud bs = new BeanSolicitud();
					bs.cedulagestionador=gestionador.getCedulaGestionador();
					return null; 
				}catch(NoResultException e){
					
				}				
			}
		}	
		return null;
	}
	
	public void registrarpeticion(Date fecha, String observaciones, String area, String entidad, String tipoinfo, String a�o, int cedula){
		Query querypeticion=entitymanager.createQuery(buscarpeticion);
		List<Peticion> listapeticiones = querypeticion.getResultList();
		idpeticion=listapeticiones.size()+1;
		System.out.println(listapeticiones.size());
		System.out.println(idpeticion);
		try{
			Query consultapeticion = entitymanager.createQuery("SELECT p FROM Peticion p WHERE p.idPeticion ="+Integer.toString(idpeticion));
			List<Peticion> listaconpe=consultapeticion.getResultList();
			peticion=(Peticion) consultapeticion.getSingleResult();
			System.out.println("Ya existe el id. /n Vuelva intentarlo.");
		}catch(NoResultException e){
			try{
				Query consultaestado=entitymanager.createQuery("SELECT e FROM Estado e WHERE e.tipoEstado = :tipoEstado");
				consultaestado.setParameter("tipoEstado", "Buscando informacion");
				Estado listaestados=(Estado) consultaestado.getSingleResult();
				System.out.println("Estado encontrado.");
				try{
					Query consultausuario=entitymanager.createQuery("SELECT u FROM Usuario u WHERE u.cedulaUsuario = "+cedula);
					System.out.println(cedula);
					//consultausuario.setParameter("cedulaUsuario", cedula);
					Usuario listausuario=(Usuario) consultausuario.getSingleResult();
					System.out.println("Usuario encontrado.");
					try{
						Query consultatipo=entitymanager.createQuery("SELECT t FROM Tipoinformacion t WHERE t.nombreTipoInformacion = :nombreTipoInformacion");
						consultatipo.setParameter("nombreTipoInformacion", tipoinfo);
						Tipoinformacion listatipo=(Tipoinformacion) consultatipo.getSingleResult();
						System.out.println("Tipo enconrado.");
						
						Query q=entitymanager.createQuery("SELECT t FROM Tipoinformacion t");
						List<Tipoinformacion> resultados=q.getResultList();
						tipoinformacion = new Tipoinformacion();
						for(int i=0;i<resultados.size();i++){
							tipoinformacion=resultados.get(i);
				        	
				        	String tipo=tipoinformacion.getNombreTipoInformacion();
				        	if(tipo.equals(tipoinfo)){
				        		idtipo=tipoinformacion.getIdTipoInformacion();				        		
				        	}
						}
						
						try{
							Query consultaempresa=entitymanager.createQuery("SELECT e FROM Empresa e WHERE e.nombreEmpresa = :nombreEmpresa");
							consultaempresa.setParameter("nombreEmpresa", entidad);
							Empresa listaempresa=(Empresa) consultaempresa.getSingleResult();
							
							System.out.println("Empresa enconrada.");							
			        		idempresa=listaempresa.getIdEmpresa();
			        		idempresaciudad=listaempresa.getCiudad_idCiudad();
			        		idempresadepartamento=listaempresa.getCiudad_Departamento_idDepartamento();       

							peticion = new Peticion();
							peticion.setIdPeticion(idpeticion);
							peticion.setFechaPeticion(fecha);
							peticion.setObservacionesPeticion(observaciones);
							peticion.setEstadoPeticion(true);
							peticion.setEstado(listaestados);
							peticion.setEmpresa(listaempresa);
							peticion.setUsuario(listausuario);
							peticion.setTipoinformacion(listatipo);			
							usuario.addPeticion(peticion);
							entitymanager.getTransaction().begin();
							entitymanager.persist(peticion);
							entitymanager.getTransaction().commit();
						}catch(NoResultException e4){
							System.out.println("Empresa no enconrada.");
						}
					}catch(NoResultException e3){
						System.out.println("No existe este tipo de informacion. /n Vuelva intentarlo.");
					}
				}catch(NoResultException e2){
					System.out.println("Usuario no encontrado.");
				}
			}catch(NoResultException e1){
				System.out.println("Ya estado no existe. /n Vuelva intentarlo.");
			}
		}
	}
	
	public Object previsual(Date fecha, String entidad, String ciudad, String observacion, String correo, String direccion, int cedula, String nombre, String apellido) throws FileNotFoundException{
		Date date = new Date();
		DateFormat fechas = new SimpleDateFormat("yyyy-MM-dd");
		String convertido = fechas.format(date);
		String encabezado="Bogota D.C., "+convertido;
		String referencia="Solicitud de informaci�n";
		String dirigido="Respetado se�or/a:";
		String parrafo1="En ejercicio del derecho fundamental de petici�n, consagrado en el art�culo 23 de la Consituci�n Nacional, y del derecho de acceso a la infomaci�n p�blica, consagrados en los art�culos 20 y 74 de la misma, desarrollado por la ley 1712 de 2014; de manera respetuosa le solicito la siguiente informaci�n:";		
		String parrafo2=observacion;		
		String parrafo3="La respuesta a la presente solicitud la recibir� en la direcci�n fisica "+direccion+"o direcci�n de correo electronico "+correo+".";
		String cordialmente="Cordialmente.";
		String nombres=nombre+" "+apellido;
		try{
			FileOutputStream archivo = new FileOutputStream("C:/Users/JuanJose/Documents/Peticiones/"+cedula+".pdf");
			Document documento = new Document();
		    PdfWriter.getInstance(documento, archivo);
		    documento.open();
		    documento.add(new Paragraph(encabezado));
		    documento.add(new Paragraph("\n\n\n\n"));
		    documento.add(new Paragraph(referencia));
		    documento.add(new Paragraph("\n\n\n\n"));
		    documento.add(new Paragraph(dirigido));
		    documento.add(new Paragraph("\n\n"));
		    documento.add(new Paragraph(parrafo1));
		    documento.add(new Paragraph("\n\n"));
		    documento.add(new Paragraph(parrafo2));
		    documento.add(new Paragraph("\n\n\n\n"));
		    documento.add(new Paragraph(parrafo3));
		    documento.add(new Paragraph("\n\n\n\n\n\n"));
		    documento.add(new Paragraph(cordialmente));
		    documento.add(new Paragraph("\n\n\n\n"));
		    documento.add(new Paragraph(nombres));
		    documento.add(new Paragraph("\n"));
		    documento.add(new Paragraph(Integer.toString(cedula)));
		    documento.close();
		    
		    File path = new File ("C:/Users/JuanJose/Documents/Peticiones/"+cedula+".pdf");
		    Desktop.getDesktop().open(path);
		    
		    Process p = Runtime.getRuntime().exec ("rundll32 SHELL32.DLL,ShellExec_RunDLL "+"C:/Users/JuanJose/Documents/Peticiones/"+cedula+".pdf");
		    
		    Runtime.getRuntime().exec("cmd /c start "+"C:/Users/JuanJose/Documents/Peticiones/"+cedula+".pdf");
		    
		    InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("C:/Users/JuanJose/Documents/Peticiones/"+cedula+".pdf");
	        file = new DefaultStreamedContent(stream, "image/pdf", "solicitud.pdf");
		    
		}
		catch (Exception e){
				e.printStackTrace();
		}
		return file;
	}
	
	public List<Peticion> Mostrar_las_peticiones_que_ha_realizado_un_solicitante(int cedula){
		Query consultausuariopeticion=entitymanager.createQuery("SELECT u FROM Usuario u WHERE u.cedulaUsuario = "+cedula);
		Usuario usuario=(Usuario) consultausuariopeticion.getSingleResult();

		List<Peticion> listausuariopeticion=usuario.getPeticions();
		System.out.println("el usuario existe");
		return listausuariopeticion;
	}
	
}