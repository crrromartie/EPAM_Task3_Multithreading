package by.gaponenko.thread.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    static Logger logger = LogManager.getLogger();

    private static final Port instance = new Port();
    private static final int DEFAULT_BERTHS_AMOUNT = 3;
    private Queue<Berth> freeBerths;
    private Queue<Berth> occupiedBerths;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private Port() {
        this.freeBerths = new LinkedList<>();
        this.occupiedBerths = new LinkedList<>();
        createPort();
    }

    public static Port getInstance() {
        return instance;
    }

    public Berth visitPort() {
        try {
            lock.lock();
            while (freeBerths.isEmpty()) {
                logger.log(Level.INFO, "All berths are occupied!");
                condition.await();
            }
            Berth berth = freeBerths.poll();
            occupiedBerths.offer(berth);
            return berth;
        } catch (InterruptedException e) {
            logger.log(Level.INFO, e.getMessage());
            return null;
        } finally {
            lock.unlock();
        }
    }

    public void leavePort(Berth berth) {
        try {
            lock.lock();
            freeBerths.offer(berth);
            occupiedBerths.remove();
        } finally {
            condition.signal();
            lock.unlock();
        }
    }

    private void createPort() {
        for (int i = 1; i < DEFAULT_BERTHS_AMOUNT; i++) {
            freeBerths.add(new Berth(i));
        }
        logger.log(Level.INFO, "Port created!");
    }
}
