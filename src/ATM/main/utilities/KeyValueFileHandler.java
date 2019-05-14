package main.utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * A utility class for handling txt files
 */
public class KeyValueFileHandler implements Serializable {


    /**
     * Generates a map of given filename with key and value separated by ':' for each line in the file
     *
     * @return Map of information for each line in the text file stored with String as key and String as value
     */
    private Map<String, String> generateFileMap(String filename) {
        Map<String, String> fileInfo = new TreeMap<>();
        try {
            Path filePath = Paths.get(filename);
            if (!filePath.toFile().exists()) {
                filePath.toFile().createNewFile();
            }
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (String line : lines) {
                String[] data = line.split(":");
                String fileKey = data[0];
                String fileValue = data[1];
                fileInfo.put(fileKey, fileValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileInfo;
    }

    /**
     * Write to the given file at key with value
     *
     * @param filename the file to write
     * @param key      the key of a line in the file
     * @param value    the value of a line in the file
     */
    public void setFileValue(String filename, String key, Object value) {

        Map<String, String> fileInfo = generateFileMap(filename);

        fileInfo.put(key, value.toString());
        try (PrintWriter printWriter = new PrintWriter(filename)) {
            for (Map.Entry<String, String> item : fileInfo.entrySet()) {
                printWriter.println(item.getKey() + ":" + item.getValue());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the String value of a line at key in the provided file
     *
     * @param filename the name of the file to read
     * @param key      the key of which the value should be returned for from the text file map
     * @return a String containing the info specified at the key
     */
    public String getFileValue(String filename, String key) {
        Map<String, String> fileInfo = generateFileMap(filename);
        return fileInfo.get(key);
    }

    /**
     * Get the int value of a line at key in the provided file
     *
     * @param filename the name of the file to read
     * @param key      the key of which the value should be returned for from the text file map
     * @return an int containing the info specified at the key
     */
    public int getFileIntValue(String filename, String key) {
        return Integer.valueOf(getFileValue(filename, key));
    }
}
