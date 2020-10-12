package by.gaponenko.thread.entity.state.impl;

import by.gaponenko.thread.entity.Berth;
import by.gaponenko.thread.entity.Ship;
import by.gaponenko.thread.entity.state.ShipState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnloadStateImpl implements ShipState {
    static Logger logger = LogManager.getLogger();

    private static final ShipState nextShipState = new LoadStateImpl();

    @Override
    public void arrive(Ship ship) {
        logger.log(Level.WARN, "Operation is not supported!");
    }

    @Override
    public void unload(Ship ship) {
        Berth berth = ship.getBerth();
        logger.log(Level.INFO, "Ship " + ship.getShipId() + " try to unload at berth " + berth.getBerthId());
        berth.unloadToStorage(ship);
        ship.setCurrentState(nextShipState);
    }

    @Override
    public void load(Ship ship) {
        logger.log(Level.WARN, "Operation is not supported!");
    }

    @Override
    public void leave(Ship ship) {
        logger.log(Level.WARN, "Operation is not supported!");
    }
}
