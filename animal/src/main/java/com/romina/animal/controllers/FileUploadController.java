package com.romina.animal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileUploadController {

    private static final Path UPLOAD_DIRECTORY = Paths.get("uploads"); // Define el directorio donde guardar los archivos

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Por favor, selecciona un archivo para subir.", HttpStatus.BAD_REQUEST);
        }

        try {
            // Asegúrate de que el directorio de carga exista
            if (!Files.exists(UPLOAD_DIRECTORY)) {
                Files.createDirectories(UPLOAD_DIRECTORY);
            }

            // Construye la ruta completa del archivo a guardar
            Path filePath = UPLOAD_DIRECTORY.resolve(file.getOriginalFilename());

            // Guarda el archivo en el sistema de archivos
            Files.copy(file.getInputStream(), filePath);

            return new ResponseEntity<>("Archivo subido exitosamente: " + file.getOriginalFilename(), HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al subir el archivo.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
