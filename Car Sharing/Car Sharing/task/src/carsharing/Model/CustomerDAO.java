package carsharing.Model;

import carsharing.ConnectJDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements DAO<Customer>{
    private List<Customer> customerList;
    private ConnectJDBC connectJDBC;

    public CustomerDAO(ConnectJDBC connectJDBC) {
        this.connectJDBC = connectJDBC;
        this.customerList = new ArrayList<>();
    }

    @Override
    public List<Customer> getAll() {
        customerList.clear();
        String sql = "SELECT * FROM CUSTOMER ORDER BY ID;";
        ResultSet rs = connectJDBC.query(sql);
        try{
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int rented_car_id = rs.getInt("RENTED_CAR_ID");

                CarDAO carDAO =  new CarDAO(connectJDBC);
                Car car = carDAO.getCarByID(rented_car_id);

                customerList.add(new Customer(id, name, car));
            }
        }catch (SQLException sq){
            sq.printStackTrace();
        }
        return customerList;
    }

    @Override
    public Customer get() {
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void add(Customer customer) {
        String name = customer.getName();
//        int rented_car_id = customer.getRentedCar().getId();
        String sql = "INSERT INTO CUSTOMER (NAME) VALUES ('" + name +"');";
//        System.out.println(sql);
        connectJDBC.update(sql);
    }

    public Car getCustomerRentedCar(Customer customer){
        String sql = "SELECT RENTED_CAR_ID FROM CUSTOMER WHERE ID="+customer.getId()+";";
        ResultSet rs = connectJDBC.query(sql);
        CarDAO carDAO =  new CarDAO(connectJDBC);
        Car car = null;
        try{
            rs.next();
            if(rs.wasNull()){

            } else {
                int rentedCarId = rs.getInt("RENTED_CAR_ID");
                car = carDAO.getCarByID(rentedCarId);
            }
        }catch (SQLException sq){
            sq.printStackTrace();
        }
        return car;
    }

    public void rentedCarUpdate(Customer customer,int carID){
        String s = "";

        if(carID == 0){
            s = "NULL";
        } else {
            s = String.valueOf(carID);
        }
        String sql = "UPDATE CUSTOMER SET RENTED_CAR_ID = "+s+" WHERE ID = "+customer.getId();
        connectJDBC.update(sql);
    }
}
