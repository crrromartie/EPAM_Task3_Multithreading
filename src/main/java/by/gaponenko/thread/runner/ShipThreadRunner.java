package by.gaponenko.thread.runner;

import by.gaponenko.thread.creator.ShipCreator;
import by.gaponenko.thread.entity.Ship;
import by.gaponenko.thread.entity.Storage;
import by.gaponenko.thread.exception.ProjectException;
import by.gaponenko.thread.parser.DataParser;
import by.gaponenko.thread.reader.DataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.*;


public class ShipThreadRunner {
    static Logger logger = LogManager.getLogger();

    private final static String filePath = "src/main/resources/data/data.txt";

    public static void main(String[] args) {
        List<String> data = null;
        try {
            data = new DataReader().readData(filePath);
        } catch (ProjectException e) {
            e.printStackTrace();
        }
        List<String[]> dataForCreator = new DataParser().parseData(data);
        List<Ship> ships = null;
        try {
            ships = new ShipCreator().creatShips(dataForCreator);
        } catch (ProjectException e) {
            e.printStackTrace();
        }
        logger.info("Start storage: " + Storage.getInstance().getCurrentCapacity().toString());
        ExecutorService executor = Executors.newFixedThreadPool(ships.size());
        for (Ship ship : ships) {
            executor.submit(ship);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Final storage: " + Storage.getInstance().getCurrentCapacity().toString());
        executor.shutdown();
    }
}
