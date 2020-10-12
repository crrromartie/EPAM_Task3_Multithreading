package by.gaponenko.thread.reader;

import by.gaponenko.thread.exception.ProjectException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DataReader {
    static Logger logger = LogManager.getLogger();

    public List<String> readData(String filePath) throws ProjectException {
        List<String> lines;
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            lines = bufferedReader.lines().collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            logger.log(Level.ERROR, "File not found!", e);
            throw new ProjectException("File not found: " + filePath, e);
        } catch (IOException e) {
            logger.log(Level.ERROR, "File reading error!", e);
            throw new ProjectException("File reading error: " + filePath, e);
        }
        logger.log(Level.INFO, "File read successfully!");
        return lines;
    }
}
