import java.sql.Array;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static int SIZE = 10;

    static int[] generate(int size) {
        Random random = new Random();
        int[] numbers = new int[size];
        for(int i = 0; i < size; i++) {
            numbers[i] = random.nextInt(100);
        }
        return numbers;
    }

    public static void main(String[] args) {
        int sum = 0;
        Long startTime;
        Random random = new Random();
        int[] numbers = generate(SIZE);

        System.out.println("************ 1 THREAD ************** ");
        startTime = System.currentTimeMillis();
        System.out.println("startTime = "+startTime);
        for (int number : numbers) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum += number;
        }
        System.out.println("sum = "+sum);
        System.out.println("endTime = "+System.currentTimeMillis());
        System.out.println("workTime = "+(System.currentTimeMillis()-startTime));

        System.out.println("************ ForkJoinPool ************** ");
        ForkJoinSummer forkJoinSummer = new ForkJoinSummer(numbers);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        startTime = System.currentTimeMillis();
        System.out.println("startTime = "+startTime);
        sum = forkJoinPool.invoke(forkJoinSummer);
        System.out.println("sum = "+sum);
        System.out.println(sum);
        System.out.println("endTime = "+System.currentTimeMillis());
        System.out.println("workTime = "+(System.currentTimeMillis()-startTime));
    }
    /*
10
************ 1 THREAD **************
startTime = 1633257573338
sum = 395
endTime = 1633257583507
workTime = 10185
************ ForkJoinPool **************
startTime = 1633257583538
sum = 395
395
endTime = 1633257584538
workTime = 1000

100
************ 1 THREAD **************
startTime = 1633256052128
sum = 4669
endTime = 1633256153620
workTime = 101507
************ ForkJoinPool **************
startTime = 1633256153651
sum = 4669
4669
endTime = 1633256163789
workTime = 10138

500
************ 1 THREAD **************
startTime = 1633256279816
sum = 23698
endTime = 1633256787102
workTime = 507302
************ ForkJoinPool **************
startTime = 1633256787134
sum = 23698
23698
endTime = 1633256857086
workTime = 69952

    */
}
