package by.gaponenko.thread.entity;

import by.gaponenko.thread.entity.state.ShipState;
import by.gaponenko.thread.entity.state.impl.ArriveStateImpl;

import java.util.StringJoiner;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class Ship implements Callable<Ship> {
    private int shipId;
    private AtomicInteger currentAmountContainer;
    private AtomicInteger maxAmountContainer;
    private Berth berth;
    private ShipState currentState;

    public Ship(int shipId, AtomicInteger currentAmountContainer, AtomicInteger maxAmountContainer) {
        this.shipId = shipId;
        this.currentAmountContainer = currentAmountContainer;
        this.maxAmountContainer = maxAmountContainer;
        this.currentState = new ArriveStateImpl();
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public AtomicInteger getCurrentAmountContainer() {
        return currentAmountContainer;
    }

    public void setCurrentAmountContainer(AtomicInteger currentAmountContainer) {
        this.currentAmountContainer = currentAmountContainer;
    }

    public AtomicInteger getMaxAmountContainer() {
        return maxAmountContainer;
    }

    public void setMaxAmountContainer(AtomicInteger maxAmountContainer) {
        this.maxAmountContainer = maxAmountContainer;
    }

    public Berth getBerth() {
        return berth;
    }

    public void setBerth(Berth berth) {
        this.berth = berth;
    }

    public ShipState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ShipState currentState) {
        this.currentState = currentState;
    }

    @Override
    public Ship call() throws Exception {
        currentState.arrive(this);
        currentState.unload(this);
        currentState.load(this);
        currentState.leave(this);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ship ship = (Ship) o;
        if (shipId != ship.shipId) {
            return false;
        }
        if (currentAmountContainer == null) {
            if (ship.currentAmountContainer != null) {
                return false;
            }
        } else if (!currentAmountContainer.equals(ship.currentAmountContainer)) {
            return false;
        }
        if (maxAmountContainer == null) {
            if (ship.currentAmountContainer != null) {
                return false;
            }
        } else if (!maxAmountContainer.equals(ship.maxAmountContainer)) {
            return false;
        }
        if (berth == null) {
            if (ship.berth != null) {
                return false;
            }
        } else if (!berth.equals(ship.berth)) {
            return false;
        }
        if (currentState == null) {
            if (ship.currentState != null) {
                return false;
            }
        } else if (!currentState.equals(ship.currentState)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = shipId;
        result = 31 * result + (currentAmountContainer != null ? currentAmountContainer.hashCode() : 0);
        result = 31 * result + (maxAmountContainer != null ? maxAmountContainer.hashCode() : 0);
        result = 31 * result + (berth != null ? berth.hashCode() : 0);
        result = 31 * result + (currentState != null ? currentState.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Ship.class.getSimpleName() + "[", "]")
                .add("shipId=" + shipId)
                .add("currentAmountContainer=" + currentAmountContainer)
                .add("maxAmountContainer=" + maxAmountContainer)
                .add("berth=" + berth)
                .add("currentState=" + currentState)
                .toString();
    }
}
