public class App {

    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("main");
        threadLocal.set("asd");
        System.out.println("当前ThreadLocaMain的值: " + threadLocal.get());
        System.out.println("当前ThreadLocaMain的值: " + threadLocal.get());
        Thread thread1 = new Thread(() -> {
            threadLocal.set("thread1");
            System.out.println("当前ThreadLocal的值: " + threadLocal.get());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadLocal.remove();
            System.out.println("当前ThreadLocal的值: " + threadLocal.get());
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("当前ThreadLoca2的值: " + threadLocal.get());
        });
        thread1.start();
        thread2.start();
    }
}
