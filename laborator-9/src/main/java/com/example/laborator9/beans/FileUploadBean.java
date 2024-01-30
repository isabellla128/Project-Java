package com.example.laborator9.beans;

import com.example.laborator9.retriever.StudentsRetriever;
import lombok.Getter;
import org.primefaces.model.file.UploadedFile;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
@Named("FileUploadBean")
@RequestScoped
public class FileUploadBean  {
    @Getter
    private Part file;
    private String fileContent;
    private int number=0;

    @Inject
    StudentsRetriever studentsRetriever;

    public void upload() {
        try {
            String type=file.getContentType();
            number++;
            fileContent = new Scanner(file.getInputStream())
                    .useDelimiter("\\A").next();
            if(fileContent==null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "File content is null!", "Cannot upload an empty file!"));
            }
            else if(!type.equals("application/json")){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "File type is not json!", "Incorrect type!"));
            }
            else {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage(null, new FacesMessage("Upload successfully!",file.getName() + " is uploaded."));
                System.out.println(studentsRetriever.getStudentsUsernames());
                List<String> studentsUsernames=studentsRetriever.getStudentsUsernames();
                for(String username :studentsUsernames)
                    System.out.println(username);
                studentsRetriever.postStudents(file.getInputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFile(Part file) {
        this.file = file;
    }
    public void displayMessage() {
        // Add your display message logic here
        System.out.println("Displaying Message");
    }
}