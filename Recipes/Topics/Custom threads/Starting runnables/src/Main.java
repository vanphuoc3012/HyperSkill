class Starter {

    public static void startRunnables(Runnable[] runnables) {
        // implement the method
        for(Runnable r : runnables) {
            Thread k = new Thread(r);
            k.start();
        }
    }
}