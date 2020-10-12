package by.gaponenko.thread.creator;

import by.gaponenko.thread.entity.Ship;
import by.gaponenko.thread.exception.ProjectException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ShipCreator {
    static Logger logger = LogManager.getLogger();

    public List<Ship> creatShips(List<String[]> spaceshipLines) throws ProjectException {
        List<Ship> ships = new ArrayList<>();
        for (int i = 0; i < spaceshipLines.size(); i++) {
            try {
                int shipId = Integer.parseInt(spaceshipLines.get(i)[0].strip());
                AtomicInteger currentAmountContainer = new AtomicInteger(Integer.parseInt(spaceshipLines.get(i)[1].strip()));
                AtomicInteger maxAmountContainer = new AtomicInteger(Integer.parseInt(spaceshipLines.get(i)[2].strip()));
                Ship ship = new Ship(shipId, currentAmountContainer, maxAmountContainer);
                ships.add(ship);
            } catch (NumberFormatException e) {
                throw new ProjectException("Value is not integer!", e);
            }
        }
        logger.log(Level.INFO, "List ships created successfully!");
        return ships;
    }
}
