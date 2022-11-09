import java.util.function.*;

class Operator {

    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse) {

        return t -> {
            if(condition.test(t)){
                return ifTrue.apply(t);
            } else {
                return ifFalse.apply(t);
            }
        };

    }
}