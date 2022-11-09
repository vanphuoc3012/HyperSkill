class EnjoyVehicle {

    public static void startVehicle() {
        // start your vehicle
        Vehicle vehicle = new Vehicle();
        Vehicle.Engine engine = vehicle.new Engine();
        engine.start();
    }
}

//class Vehicle {
//
//    // vehicle of your dream
//
//    class Engine {
//
//        void start() {
//            System.out.println("RRRrrrrrrr....");
//        }
//    }
//}