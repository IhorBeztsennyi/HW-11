public class OneAndFiveSecond {
    static int time = 0;
    static boolean flag = true;

    public static void main(String[] args) {
        Thread fiveSeconds = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                while (flag) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Прошло 5 секунд");
                }
            }

        });

        fiveSeconds.start();

        while (flag) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time++;
            System.out.println(time + " seconds passed from the beginning");
        }
    }
}

