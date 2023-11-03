package br.com.fiap.namastreta.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fiap.namastreta.DTO.Image;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Value("${image.upload.directory}")
    private String imageUploadDirectory;

    @Autowired
    private Environment environment;

    @PostMapping("/upload")
    public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request)
            throws IOException {

        File uploadDir = new File(imageUploadDirectory);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        if ((file.getSize() / (1024 * 1024)) > 1) {
            throw new FileSizeLimitExceededException("Arquivo com o limite maior que o permitido",
                    file.getSize() / (1024 * 1024),
                    1);
        }

        String imageName =  file.getOriginalFilename();

        // Salve a imagem no diretório de upload.
        Path imagePath = Path.of(imageUploadDirectory, imageName);
        Files.copy(file.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

        String host = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();

        // Construa a URL completa para a imagem.
        String imageUrl = host + "/api/image/" + imageName;

        return ResponseEntity.status(HttpStatus.CREATED).body(new Image(imageUrl));
    }

    @GetMapping("/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {

        String imageUploadDirectory = environment.getProperty("image.upload.directory");
        Path imagePath = Path.of(imageUploadDirectory, imageName);

        if (Files.exists(imagePath)) {
            byte[] imageBytes = Files.readAllBytes(imagePath);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
