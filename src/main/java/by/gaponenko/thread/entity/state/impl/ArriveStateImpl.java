package by.gaponenko.thread.entity.state.impl;

import by.gaponenko.thread.entity.Berth;
import by.gaponenko.thread.entity.Port;
import by.gaponenko.thread.entity.Ship;
import by.gaponenko.thread.entity.state.ShipState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArriveStateImpl implements ShipState {
    static Logger logger = LogManager.getLogger();

    private static final ShipState nextState = new UnloadStateImpl();
    private static final ShipState reserveShipState = new LeaveStateImpl();

    @Override
    public void arrive(Ship ship) {
        Port port = Port.getInstance();
        Berth berth = port.visitPort();
        if (berth != null) {
            ship.setBerth(berth);
            logger.log(Level.INFO, "Ship " + ship.getShipId() + " arrive at berth " + berth.getBerthId());
            ship.setCurrentState(nextState);
        } else {
            logger.log(Level.INFO, "Ship " + ship.getShipId() + " left port!");
            ship.setCurrentState(reserveShipState);
        }
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
        logger.log(Level.WARN, "Operation is not supported!");
    }
}
