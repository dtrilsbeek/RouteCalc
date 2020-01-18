package presentation.handlers;

import presentation.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ResourceHandler {

    public static String getTravelHtml() {
        try {
            return getResourceFileAsString("/static-dev/travel.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getErrorHtml() {
        try {
            return getResourceFileAsString("/static-dev/error.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Reads given resource file as a string.
     *
     * @param fileName path to the resource file
     * @return the file's contents
     * @throws IOException if read fails for any reason
     */
    private static String getResourceFileAsString(String fileName) throws IOException {
        try (InputStream is = Main.class.getResourceAsStream(fileName)) {
            if (is == null) return null;
            try (InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }
}
