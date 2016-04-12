package controlador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.*;

public class pruebarfunidad {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LeyTransparencia");
	private static final EntityManager em = emf.createEntityManager();
	private static int i=0;
	
	public static void departamento(){
		Departamento dto = new Departamento();
		dto.setIdDepartamento(i+1);
		dto.setNombreDto("Cundinamarca");
		dto.setEstadoDepartamento(true);
		
		em.getTransaction().begin();
		em.persist(dto);
		em.getTransaction().commit();
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		departamento();
	}

}
