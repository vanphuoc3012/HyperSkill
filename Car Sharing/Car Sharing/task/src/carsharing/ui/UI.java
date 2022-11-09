package carsharing.ui;

import carsharing.ConnectJDBC;
import carsharing.Model.*;

import java.util.List;
import java.util.Scanner;

public class UI {
    private ConnectJDBC connectJDBC;
    private CompanyDAO companyDAO;
    private Scanner scanner;
    private CarDAO carDAO;
    private CustomerDAO customerDAO;
    private ManagerUI managerUI;
    private CustomerUI customerUI;


    public UI(ConnectJDBC connectJDBC, CompanyDAO companyDAO, CarDAO carDAO, CustomerDAO customerDAO) {
        this.connectJDBC = connectJDBC;
        this.companyDAO = companyDAO;
        this.carDAO = carDAO;
        this.customerDAO = customerDAO;
        this.scanner = new Scanner(System.in);
        this.managerUI = new ManagerUI(customerDAO, companyDAO, carDAO);
        this.customerUI = new CustomerUI(customerDAO, companyDAO, carDAO);
    }

    public void start(){

        while (true){
            System.out.println();
            System.out.println("1. Log in as a manager\n" +
                    "2. Log in as a customer\n" +
                    "3. Create a customer\n" +
                    "0. Exit");
            int index = Integer.valueOf(scanner.nextLine());
            if(index == 1){
                managerUI.start();
            } else if(index == 2){
                customerUI.start();
            } else if (index == 3) {
                addCustomer();
            } else {
//                System.out.println("Exiting.....");
                connectJDBC.shutDown();
                break;
            }
        }

    }

    private void addCustomer() {
        System.out.println();
        System.out.println("Enter the customer name:");
        String customerName = String.valueOf(scanner.nextLine());
        customerDAO.add(new Customer(customerName));
        System.out.println("The customer was added!");
    }
}
