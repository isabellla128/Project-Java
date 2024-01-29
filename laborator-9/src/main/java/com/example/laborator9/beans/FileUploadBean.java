package com.example.laborator9.beans;

import lombok.Getter;
import org.primefaces.model.file.UploadedFile;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Getter
@Named("FileUploadBean")
@RequestScoped
public class FileUploadBean {
    private UploadedFile file;

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        // Add your file upload logic here
        // You can access the uploaded file using 'file' variable
        // Example: InputStream inputStream = file.getInputstream();
        System.out.println("Upload Message");
    }

    public void displayMessage() {
        // Add your display message logic here
        System.out.println("Displaying Message");
    }
}