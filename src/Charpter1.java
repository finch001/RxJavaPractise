import rx.Observable;
import rx.Subscription;
import rx.observables.ConnectableObservable;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by livvy on 2016/8/7 0007.
 */
public class Charpter1 {
    public static void main(String [] args){
//        List<String> stringList = new ArrayList<>(Arrays.asList("hello","world","livvy"));
//        Observable<String> stringObservable = Observable.from(stringList);
//        //stringObservable.subscribe(System.out::println);
//       // stringObservable.subscribe(color -> System.out.println(color + "   "));
//
//        // 其实底层还是From 而已 只不过是用了一个数组把所有的数据
//        Observable<String> letterObservable = Observable.just("R","X","J","a","v","a");
//        letterObservable.subscribe(System.out::println);
//        // 其实是间隔的发射数据
//        // 默认是在计算线程处理 第三个参数可以用来指定所在线程
//        // 其实它是不触发OnCompleted的
//        subScribePrint(Observable.interval(500L, TimeUnit.MILLISECONDS),"interval time");
//        // 其实是定时发射数据
//        // 默认是在计算线程  结束后会触发completed 通知
//        subScribePrint(Observable.timer(1l,TimeUnit.SECONDS),"timer Observable");
//
//        subScribePrint(Observable.error(new Exception("test error")),"Error throwable");
//
//        subScribePrint(Observable.range(1,3),"Range Observable");
//        try {
//            Thread.sleep(2000l);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        Observable<Long> interval = Observable.interval(100l,TimeUnit.MILLISECONDS);
        // 创建一个ConnectableObservable的对象
        ConnectableObservable<Long> published = interval.publish();

        Subscription subscription = subscribePrint(published,"first");

        Subscription subscription1 = subscribePrint(published,"second");

        published.connect();


        try {
            Thread.sleep(500l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        subscription.unsubscribe();
        subscription1.unsubscribe();

        Subscription subscription3 = subscribePrint(published,"thrid");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        subscription3.unsubscribe();

    }

    public static void subScribePrint(Observable observable,String name){

        observable.subscribe(
                (next) -> System.out.println(name  +" " + next),
                (e) -> {System.out.println("error from " + name);
                System.err.println(e.toString());
                },
                () -> System.out.println(" " + name + "end !")
        );
    }

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
