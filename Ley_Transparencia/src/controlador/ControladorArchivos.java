package controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

public class ControladorArchivos{
	public boolean tamanoArchivo(UploadedFile file){
		long tamano=file.getSize();
		System.out.println(tamano);
		//10 megabyte
		if(tamano<=10485760){
			return true;
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "sobrepaso los 10 MB el tamaño permitido"));
			return false;
		}
	}
	public boolean Reconocer_formato(UploadedFile file){
		String nombre_del_archivo=file.getFileName();
		int largo_del_nombre_del_archivo=nombre_del_archivo.length();
		String analizar= (String) nombre_del_archivo.subSequence(largo_del_nombre_del_archivo-4, largo_del_nombre_del_archivo);
		System.out.println(analizar);
		if(analizar.equals(".zip")||analizar.equals(".rar")||analizar.equals(".tgz")||analizar.equals(".png")||analizar.equals(".jpg")||analizar.equals(".gif")
				||analizar.equals(".xls")||analizar.equals(".dot")||analizar.equals(".pdf")){
			return true;
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El formato no es permitido"));
			return false;
		}
	}
}
