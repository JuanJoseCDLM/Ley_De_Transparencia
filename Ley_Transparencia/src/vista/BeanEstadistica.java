package vista;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.PieChartModel;

import controlador.GestionAdministrador;

@ManagedBean
public class BeanEstadistica implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private PieChartModel pieModel1;	
	public PieChartModel pieModel;
	private String opcion1="¿Quienes somos?";
	private String opcion2="Solicitudes";
	private String opcion3="Gestionar solicitudes";
	private String opcion4="Reportes";
	private String opcion5="Cerrar Sesion";

	private int i=3;
	
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
	
	public String getOpcion5() {
		return opcion5;
	}

	public void setOpcion5(String opcion5) {
		this.opcion5 = opcion5;
	}

	public PieChartModel getPieModel1() {
		pieModel1=pieModel;
        return pieModel1;
    }
    
    public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
		this.pieModel1 = pieModel;
	}
    
    @PostConstruct
    public void cambio() {    	
		GestionAdministrador ga = new GestionAdministrador();
		pieModel=(PieChartModel) ga.crearmodelo();
    }
}