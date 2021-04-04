package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileProcessor {

    String data;

    FileProcessor(String data) {
        this.data = data;
    }

    public void writeData(String out) {
        File file = new File(out);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(data);
        } catch (IOException e) {
            System.out.print("Error: cannot create file.");
            System.exit(0);
        }
    }

    public String readData(String in) {
        try {
            return readFileAsString(in);
        } catch (IOException e) {
            System.out.println("Error: Cannot read file");
            System.exit(0);
        }
        return null;
    }

    public static String readFileAsString(String fileName) throws IOException {
        File starting = new File(System.getProperty("user.dir"));
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}


