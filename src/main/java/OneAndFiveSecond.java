public class OneAndFiveSecond {

    static int count = 0;
    static boolean flag = true;

    static class ForSynchronize {
    }

    public static void main(String[] args) {

        OneAndFiveSecond oneAndFiveSecond = new OneAndFiveSecond();

        Thread timer = new Thread(oneAndFiveSecond::oneSecond);
        Thread fiveSeconds = new Thread(oneAndFiveSecond::fiveSeconds);

        timer.start();
        fiveSeconds.start();
    }

    public void oneSecond() {
        while (flag) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (ForSynchronize.class) {
                count++;
                System.out.println(count + " seconds passed from the beginning");
                ForSynchronize.class.notifyAll();
            }
        }
    }

    public void fiveSeconds() {
        try {
            while (flag)
                synchronized (ForSynchronize.class) {
                    ForSynchronize.class.wait();
                    if (count % 5 == 0)
                        System.out.println(("Прошло 5 секунд"));
                }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

