
import java.util.ArrayList;

public class MainClass {
    public static final int CARS_COUNT = 12;
    public static volatile int WINNERS = 3;
    public static volatile int WIN = 0;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        Object monitor = new Object();

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(monitor, race, 20 + (int) (Math.random() * 10));
        }

        ArrayList<Thread> threadsCars = new ArrayList<>();

        for (int i = 0; i < cars.length; i++) {
            threadsCars.add(new Thread(cars[i]));
        }

        for (Thread t : threadsCars) {
            t.start();
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        for (Thread t : threadsCars) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}