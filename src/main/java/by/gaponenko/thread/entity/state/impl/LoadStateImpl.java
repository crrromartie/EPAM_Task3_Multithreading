package by.gaponenko.thread.entity.state.impl;

import by.gaponenko.thread.entity.Berth;
import by.gaponenko.thread.entity.Ship;
import by.gaponenko.thread.entity.state.ShipState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoadStateImpl implements ShipState {
    static Logger logger = LogManager.getLogger();

    private static final ShipState nextShipState = new LeaveStateImpl();

    @Override
    public void arrive(Ship ship) {
        logger.log(Level.WARN, "Operation is not supported!");
    }

    @Override
    public void unload(Ship ship) {
        logger.log(Level.WARN, "Operation is not supported!");
    }

    @Override
    public void load(Ship ship) {
        Berth berth = ship.getBerth();
        logger.log(Level.INFO, "Ship " + ship.getShipId() + " try to load at berth " + berth.getBerthId());
        berth.loadFromStorage(ship);
        ship.setCurrentState(nextShipState);
    }

    @Override
    public void leave(Ship ship) {
        logger.log(Level.WARN, "Operation is not supported!");
    }
}
