
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {

    private static int CARS_COUNT;
    static CyclicBarrier cb;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;
    private Object monitor;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Object monitor, Race race, int speed) {
        this.monitor = monitor;
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        cb = new CyclicBarrier(CARS_COUNT);
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);

        }

        synchronized (monitor) {
            if (MainClass.WINNERS > 0) {
                System.out.println(this.name + " WIN " + " занял " + ++MainClass.WIN + " место ");
                MainClass.WINNERS--;

            }
        }
    }
}