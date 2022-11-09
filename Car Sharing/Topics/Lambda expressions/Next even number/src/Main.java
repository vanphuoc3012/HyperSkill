import java.util.function.LongUnaryOperator;

class Operator {

    public static LongUnaryOperator unaryOperator = a -> {
        if(a % 2 == 0){
            a += 2;
        } else {
            a += 1;
        }
        return a;
    };
}