package by.gaponenko.thread.entity.state;

import by.gaponenko.thread.entity.Ship;

public interface ShipState {
    void arrive(Ship ship);

    void unload(Ship ship);

    void load(Ship ship);

    void leave(Ship ship);
}
