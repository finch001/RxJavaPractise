import rx.Observable;
import rx.Subscription;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by livvy on 2016/8/20 0020.
 */
public class RxJavaPrint {
    public static <T> Subscription subscribePrint(Observable<T> observable,
                                                  String name) {
        return observable.subscribe(
                (v) -> System.out.println(Thread.currentThread().getName()
                        + "|" + name + " : " + v), (e) -> {
                    System.err.println("Error from " + name + ":");
                    System.err.println(e);
                    System.err.println(Arrays
                            .stream(e.getStackTrace())
                            .limit(5L)
                            .map(stackEl -> "  " + stackEl)
                            .collect(Collectors.joining("\n"))
                    );
                }, () -> System.out.println(name + " ended!"));
    }



}
