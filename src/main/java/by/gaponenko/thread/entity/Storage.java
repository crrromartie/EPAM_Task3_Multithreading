package by.gaponenko.thread.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage {
    static Logger logger = LogManager.getLogger();

    private final static Storage instance = new Storage();
    private final static int STORAGE_CAPACITY = 55;
    private static final int STARTING_CAPACITY = 10;
    private AtomicInteger currentCapacity;

    private Storage() {
        currentCapacity = new AtomicInteger(STARTING_CAPACITY);
    }

    public static Storage getInstance() {
        return instance;
    }

    public AtomicInteger getCurrentCapacity() {
        return currentCapacity;
    }

    AtomicInteger unloadContainers(Ship ship) {
        AtomicInteger amountUnloaded = new AtomicInteger();
        while (currentCapacity.get() <= STORAGE_CAPACITY && removeContainer(ship)) {
            currentCapacity.incrementAndGet();
            amountUnloaded.incrementAndGet();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                logger.log(Level.INFO, e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        logger.log(Level.INFO, "Ship " + ship.getShipId() + " was unloaded!");
        return amountUnloaded;
    }

    AtomicInteger loadContainers(Ship ship) {
        AtomicInteger amountLoaded = new AtomicInteger();
        while (currentCapacity.get() > 0 && addContainer(ship)) {
            currentCapacity.decrementAndGet();
            amountLoaded.incrementAndGet();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                logger.log(Level.INFO, e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        logger.log(Level.INFO, "Ship " + ship.getShipId() + " was loaded!");
        return amountLoaded;
    }

    private boolean removeContainer(Ship ship) {
        boolean isRemove = true;
        if (ship.getCurrentAmountContainer().get() > 0) {
            ship.getCurrentAmountContainer().decrementAndGet();
            logger.log(Level.INFO, "Container was remove from the ship " + ship.getShipId());
        } else {
            isRemove = false;
            logger.log(Level.INFO, "Ship " + ship.getShipId() + " is empty!");
        }
        return isRemove;
    }

    private boolean addContainer(Ship ship) {
        boolean isAdd = true;
        if (ship.getCurrentAmountContainer().get() < ship.getMaxAmountContainer().get()) {
            ship.getCurrentAmountContainer().incrementAndGet();
            logger.log(Level.INFO, "Container was added to the ship " + ship.getShipId());
        } else {
            isAdd = false;
            logger.log(Level.INFO, "Ship " + ship.getShipId() + "  is full!");
        }
        return isAdd;
    }
}
