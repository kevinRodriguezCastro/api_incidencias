package api_incidencias.api_incidencias;

import java.util.Base64;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageToBase64 {

    public static String encodeImageToBase64(String imagePath) throws IOException {
        File file = new File(imagePath);
        FileInputStream imageInFile = new FileInputStream(file);
        byte[] imageData = new byte[(int) file.length()];
        imageInFile.read(imageData);

        // Converting Image byte array into Base64 String
        return Base64.getEncoder().encodeToString(imageData);
    }

    public static void main(String[] args) {
        try {
            String base64Image = encodeImageToBase64("./imgUsuarios/logo_DonDigital.png");
            System.out.println(base64Image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

