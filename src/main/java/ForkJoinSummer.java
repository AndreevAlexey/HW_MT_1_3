import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSummer extends RecursiveTask<Integer> {
    private int[] array;
    public ForkJoinSummer(int[] array) {
        this.array = array;
    }
    // рекурсивное вычисление суммы
    private int forkAndJoinArray() {
        int size = array.length;
        // задача для первой части
        ForkJoinSummer task1 = new ForkJoinSummer(Arrays.copyOfRange(array, 0, size/2));
        // задача для второй части
        ForkJoinSummer task2 = new ForkJoinSummer(Arrays.copyOfRange(array, size/2, size));
        // запуск задач на выполнение
        invokeAll(task1,task2);
        // результат
        return task1.join() + task2.join();
    }
    @Override
    protected Integer compute() {
        int size = array.length;
        switch(size) {
            case 0: return 0;
            case 1: return array[0];
            case 2:
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return array[0]+array[1];
            default: return forkAndJoinArray();
        }
    }
}
