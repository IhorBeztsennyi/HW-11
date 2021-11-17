
class FizzAndBuzz {
    private final int l;
    int i = 0;

    public FizzAndBuzz(int n) {
        this.l = n;
    }

    public static void main(String[] args) {

        FizzAndBuzz fizzAndBuzz = new FizzAndBuzz(15);

        Thread threadA = new Thread(fizzAndBuzz::fizz);
        Thread threadB = new Thread(fizzAndBuzz::buzz);
        Thread threadC = new Thread(fizzAndBuzz::fizzbuzz);
        Thread threadD = new Thread(fizzAndBuzz::number);

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
    }

    public synchronized void fizz() {
        while (i <= l) {
            if ((i % 3 == 0) && !(i % 5 == 0)) {
                System.out.println("fizz");
                i++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void buzz() {
        while (i <= l) {
            if (i % 3 != 0 && i % 5 == 0) {
                System.out.println("buzz");
                i++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void fizzbuzz() {
        while (i <= l) {
            if (i % 15 == 0) {
                System.out.println("fizzbuzz");
                i++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void number() {
        while (i <= l) {
            if (i % 3 != 0 && i % 5 != 0) {
                System.out.println(i);
                i++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


