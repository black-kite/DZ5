
import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    public static Semaphore sem;

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        sem = new Semaphore(MainClass.CARS_COUNT / 2);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                sem.acquire();
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                sem.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}