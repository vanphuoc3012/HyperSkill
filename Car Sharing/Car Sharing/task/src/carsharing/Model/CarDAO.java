package carsharing.Model;

import carsharing.ConnectJDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO implements DAO<Car>{
    private ConnectJDBC connectJDBC;
    private List<Car> carList;

    public CarDAO(ConnectJDBC connectJDBC) {
        this.connectJDBC = connectJDBC;
        this.carList = new ArrayList<>();
    }

    @Override
    public List getAll() {
        carList.clear();
        String sql = "SELECT * FROM CAR ORDER BY id;";
        ResultSet rs = connectJDBC.query(sql);
        try{
            while (rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                int company_id = rs.getInt("COMPANY_ID");
                CompanyDAO companyDAO = new CompanyDAO(connectJDBC);
                Company n = companyDAO.getByID(company_id);
                carList.add(new Car(id, name, n));
            }
        }catch (SQLException sq){
            sq.printStackTrace();
        }

        return carList;
    }

    @Override
    public Car get() {
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void add(Car car) {
        String name = car.getName();
        int companyID = car.getCompany().getId();
        String sql = "INSERT INTO CAR (NAME, COMPANY_ID) VALUES ('" + name +"', '"+companyID+"');";
//        System.out.println(sql);
        connectJDBC.update(sql);
    }

    public List<Car> getByCompany(Company company){
        carList.clear();
        String sql = "SELECT * FROM CAR WHERE COMPANY_ID = "+company.getId()+";";
//        System.out.println(sql);
        ResultSet rs = connectJDBC.query(sql);
        try{
            while (rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                int company_id = rs.getInt("COMPANY_ID");
                CompanyDAO companyDAO = new CompanyDAO(connectJDBC);
                Company n = companyDAO.getByID(company_id);
                carList.add(new Car(id, name, n));
            }
        }catch (SQLException sq){
            sq.printStackTrace();
        }

        return carList;
    }

    public List<Car> getByCompanyNotRented(Company company){
        carList.clear();
        String sql = "SELECT * FROM CAR WHERE COMPANY_ID = "+company.getId()+" AND IS_RENTED = FALSE;";
//        System.out.println(sql);
        ResultSet rs = connectJDBC.query(sql);
        try{
            while (rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                int company_id = rs.getInt("COMPANY_ID");
                CompanyDAO companyDAO = new CompanyDAO(connectJDBC);
                Company n = companyDAO.getByID(company_id);
                carList.add(new Car(id, name, n));
            }
        }catch (SQLException sq){
            sq.printStackTrace();
        }

        return carList;
    }

    public Car getCarByID(int carID){
        String sql = "SELECT * FROM CAR WHERE ID = " +carID+";" ;
        ResultSet rs = connectJDBC.query(sql);
        Car car = new Car();
        try{
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int companyID = rs.getInt("COMPANY_ID");

                CompanyDAO companyDAO = new CompanyDAO(connectJDBC);
                Company company = companyDAO.getByID(companyID);

                car.setId(id);
                car.setName(name);
                car.setCompany(company);
            }
        }catch (SQLException sq){
            sq.printStackTrace();
        }
        return car;
    }

    public List<Car> getCarByCustomerID(int customerID){
        carList.clear();
        String sql = "SELECT * FROM CAR WHERE CUSTOMER_ID = "+customerID+";";
        ResultSet rs = connectJDBC.query(sql);
        Car car = new Car();
        try{
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int companyID = rs.getInt("COMPANY_ID");

                CompanyDAO companyDAO = new CompanyDAO(connectJDBC);
                Company company = companyDAO.getByID(companyID);

                car.setId(id);
                car.setName(name);
                car.setCompany(company);
                carList.add(car);
            }
        } catch (SQLException sq){
            sq.printStackTrace();
        }
        return carList;
    }

    public void updateIsRented(Car c, boolean x){
        String a = "FALSE";
        if(x) {
            a = "TRUE";
        }
        String sql = "UPDATE CAR SET IS_RENTED = "+a+" WHERE ID = "+c.getId();
        connectJDBC.update(sql);
    }
}
