package carsharing;

import carsharing.Model.CarDAO;
import carsharing.Model.Company;
import carsharing.Model.CompanyDAO;
import carsharing.Model.CustomerDAO;
import carsharing.ui.UI;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        // write your code here
        ConnectJDBC connectJDBC = new ConnectJDBC();
        CompanyDAO companyDAO = new CompanyDAO(connectJDBC);
        CarDAO carDAO = new CarDAO(connectJDBC);
        CustomerDAO customerDAO = new CustomerDAO(connectJDBC);
        UI ui = new UI(connectJDBC, companyDAO, carDAO, customerDAO);


        if(args.length > 0){
            connectJDBC.DB_URL += args[1];
        } else {
            connectJDBC.DB_URL += "dataBaseCar";
        }
        String sql1 = "DROP TABLE COMPANY IF EXISTS;";
        String sql2 = "CREATE TABLE IF NOT EXISTS COMPANY ("+
                "ID INT PRIMARY KEY AUTO_INCREMENT,"+
                "NAME VARCHAR NOT NULL UNIQUE"+
                ");";
        String sql3 = "CREATE TABLE IF NOT EXISTS CAR ("+
                "ID INT PRIMARY KEY AUTO_INCREMENT,"+
                "NAME VARCHAR NOT NULL UNIQUE,"+
                "COMPANY_ID INT NOT NULL,"+
                "CUSTOMER_ID INT DEFAULT NULL," +
                "IS_RENTED BOOLEAN DEFAULT FALSE,"+
                "CONSTRAINT fk_company FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY(ID)" +
                ");";
        String sql4 = "CREATE TABLE IF NOT EXISTS CUSTOMER (\n" +
                "  ID INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "  NAME VARCHAR UNIQUE NOT NULL,\n" +
                "  RENTED_CAR_ID INT DEFAULT NULL,\n" +
                "  CONSTRAINT fk_car FOREIGN KEY (RENTED_CAR_ID) REFERENCES CAR(ID));";
        String sqlDrop = "DROP TABLE CUSTOMER IF EXISTS;\n"+
                "DROP TABLE CAR IF EXISTS;\n";

        String sqlDelete = "DELETE FROM COMPANY";




//        connectJDBC.update(sqlDrop);

//        connectJDBC.update(sql1);
        connectJDBC.update(sql2);
        connectJDBC.update(sql3);
        connectJDBC.update(sql4);
//        connectJDBC.update(sqlDelete);

        ui.start();


    }
}