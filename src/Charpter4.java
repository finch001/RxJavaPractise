import rx.Observable;

import java.util.List;

/**
 * Created by livvy on 2016/8/20 0020.
 */
public class Charpter4 {
    public static void main(String [] agrs){
        Observable<String> observable = Observable.just("hello","world").map(v-> v+ " finch");

        Observable<?> Arrays = Observable.just(java.util.Arrays.asList(1,23,12), java.util.Arrays.asList(25,111,190)).flatMapIterable(L -> L);


        RxJavaPrint.subscribePrint(observable,"map");
        RxJavaPrint.subscribePrint(Arrays,"array_map");

        List<String> albums = java.util.Arrays.asList(
                "The Piper at the Gates of Dawn",
                "A Saucerful of Secrets",
                "More", "Ummagumma", "Atom Heart Mother",
                "Meddle", "Obscured by Clouds",
                "The Dark Side of the Moon",
                "Wish You Were Here", "Animals", "The Wall"
        );

        Observable.from(albums).groupBy(album ->album.split(" ").length).subscribe(obs -> RxJavaPrint.subscribePrint(obs,obs.getKey() + " words"));

        List<Number> list = java.util.Arrays.asList(2,2,7);
        Observable<Integer> IntegerObs = Observable.from(list).cast(Integer.class);
        RxJavaPrint.subscribePrint(IntegerObs,"Integer");

        Observable<Integer> numbers = Observable
                .just(1, 13, 32, 45, 21, 8, 98, 103, 55,55);
        Observable<String> words = Observable
                .just(
                        "One", "of", "the", "few", "of",
                        "the", "crew", "crew"
                );
        Observable<?> various = Observable
                .from(java.util.Arrays.asList("1", 2, 3.0, 4, 5L));

        RxJavaPrint.subscribePrint(numbers.takeLast(4),"takeLast4");
        //返回的是一个List
        RxJavaPrint.subscribePrint(numbers.takeLastBuffer(4),"takeLastBuffer4");
        RxJavaPrint.subscribePrint(numbers.lastOrDefault(5),"lastOrDefault5");
        RxJavaPrint.subscribePrint(numbers.skipLast(4),"skipLast4");
        RxJavaPrint.subscribePrint(numbers.skip(4),"skip4");
        RxJavaPrint.subscribePrint(numbers.take(4),"take4");
        RxJavaPrint.subscribePrint(numbers.distinct(),"distinct");
        RxJavaPrint.subscribePrint(numbers.elementAt(0),"elementAt 5");
        RxJavaPrint.subscribePrint(various.ofType(Integer.class),"only Integer");






    }
}
