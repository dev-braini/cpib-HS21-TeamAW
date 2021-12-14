package ch.fhnw.cpib.Helper;

import java.io.*;

public class IMLReader {
    public static StringBuilder read(String filename) throws FileNotFoundException {
        String filePath = "src/main/resources/IML_samples/" + filename;
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filePath));
        BufferedReader bufferedReader = null;
        String line;
        StringBuilder s = new StringBuilder();

        try {
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                s.append(line).append('\n');
            }
        } catch (IOException e) {
            System.out.println("Error while reading iml code sample");
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }

        return s;
    }
}
