class Predicate {
    public static final TernaryIntPredicate ALL_DIFFERENT = (a, b, c) -> {
        if(a != b && a != c && b != c){
            return true;
        } else {
            return false;
        }
    };

    @FunctionalInterface
    public interface TernaryIntPredicate {
        // Write a method here
        boolean test(int a, int b, int c);
    }
}