package by.gaponenko.thread.entity.state.impl;

import by.gaponenko.thread.entity.Berth;
import by.gaponenko.thread.entity.Port;
import by.gaponenko.thread.entity.Ship;
import by.gaponenko.thread.entity.state.ShipState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LeaveStateImpl implements ShipState {
    static Logger logger = LogManager.getLogger();

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
        logger.log(Level.WARN, "Operation is not supported!");
    }

    @Override
    public void leave(Ship ship) {
        Port port = Port.getInstance();
        Berth berth = ship.getBerth();
        port.leavePort(berth);
        logger.log(Level.INFO, "Ship " + ship.getShipId() + " left port!");
    }
}
