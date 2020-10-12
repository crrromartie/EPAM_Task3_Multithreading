package by.gaponenko.thread.entity;

import java.util.StringJoiner;

public class Berth {
    private int berthId;

    public Berth(int berthId) {
        this.berthId = berthId;
    }

    public int getBerthId() {
        return berthId;
    }

    public void setBerthId(int berthId) {
        this.berthId = berthId;
    }

    public void unloadToStorage(Ship ship) {
        Storage.getInstance().unloadContainers(ship);
    }

    public void loadFromStorage(Ship ship) {
        Storage.getInstance().loadContainers(ship);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Berth berth = (Berth) o;
        return berthId == berth.berthId;
    }

    @Override
    public int hashCode() {
        return berthId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Berth.class.getSimpleName() + "[", "]")
                .add("berthId=" + berthId)
                .toString();
    }
}
