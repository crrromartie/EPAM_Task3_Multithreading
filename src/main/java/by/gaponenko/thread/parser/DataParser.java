package by.gaponenko.thread.parser;

import by.gaponenko.thread.validator.ParametersValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DataParser {
    static Logger logger = LogManager.getLogger();

    private static final String REGEX_DELIMITER = "\\s+";

    public List<String[]> parseData(List<String> lines) {
        List<String[]> spaceshipLines = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String[] spaceshipLine = lines.get(i).split(REGEX_DELIMITER);
            if (ParametersValidator.isCorrectNumberOfParameters(spaceshipLine)) {
                spaceshipLines.add(spaceshipLine);
            }
        }
        logger.log(Level.INFO, "List parsed successfully!");
        return spaceshipLines;
    }
}
