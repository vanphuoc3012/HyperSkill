package carsharing.ui;

import carsharing.Model.*;

import java.util.List;
import java.util.Scanner;

public class CustomerUI {
    private CustomerDAO customerDAO;
    private CompanyDAO companyDAO;
    private CarDAO carDAO;
    private Scanner scanner;

    public CustomerUI(CustomerDAO customerDAO, CompanyDAO companyDAO, CarDAO carDAO) {
        this.customerDAO = customerDAO;
        this.companyDAO = companyDAO;
        this.carDAO = carDAO;
        this.scanner = new Scanner(System.in);
    }

    public void start(){
        System.out.println();
        List<Customer> customerList = customerDAO.getAll();
        if(customerList.isEmpty()){
            System.out.println("The customer list is empty!");
        } else {
            System.out.println("Customer list:");
            customerList.forEach(System.out::println);
            System.out.println("0. Back");
            showCustomerOption(customerList);

        }
    }

    private void showCustomerOption(List<Customer> customerList) {
        int index = Integer.valueOf(scanner.nextLine());

        while (true){
            if (index == 0){
                break;
            } else {
                Customer customer = customerList.get(index-1);
                System.out.println("1. Rent a car\n" +
                        "2. Return a rented car\n" +
                        "3. My rented car\n" +
                        "0. Back");
                int option = Integer.valueOf(scanner.nextLine());
                if(option == 0){
                    break;
                } else if(option == 2){
                    returnRentedCar(customer);
                    System.out.println();
                } else if(option == 3){
                    showCustomerRentedCar(customer);
                    System.out.println();
                } else if(option == 1){
                    rentCar(customer);
                    System.out.println();
                }
            }
        }
    }

    private void rentCar(Customer customer) {
        Car car = customerDAO.getCustomerRentedCar(customer);
        if(car.getName() == null){
            List<Company> companyList = companyDAO.getAll();
            if(companyList.isEmpty()) {
                System.out.println("\nThe company list is empty!");
            } else {
                System.out.println();
                System.out.println("Choose a company:");
                for(int i = 0; i < companyList.size(); i++){
                    System.out.println(i+1+". "+companyList.get(i).getName());
                }
                System.out.println("0. Back");
                int index = Integer.valueOf(scanner.nextLine());
                if(index == 0){

                } else {
                    Company c = companyList.get(index - 1);
                    System.out.println("Choose a car: ");
                    showCompanyCar(c);
                    System.out.println("0. Back");
                    int i = Integer.valueOf(scanner.nextLine());
                    if(i == 0){

                    } else {
                        List<Car> carList = carDAO.getByCompany(c);
                        Car rentCar = carList.get(i - 1);
                        customerDAO.rentedCarUpdate(customer, rentCar.getId());
                        carDAO.updateIsRented(rentCar, true);
                        System.out.println("You rented '"+rentCar.getName()+"'");
                    }

                }
            }
        } else {
            System.out.println("You've already rented a car!");
        }
    }

    private void returnRentedCar(Customer customer) {
        Car car = customerDAO.getCustomerRentedCar(customer);
        if(car.getName() != null){
            customerDAO.rentedCarUpdate(customer, 0);
            carDAO.updateIsRented(car, false);
            System.out.println("You've returned a rented car!");
        } else {
            System.out.println("You didn't rent a car!");
        }
    }

    private void showCustomerRentedCar(Customer customer) {
        Car car = customerDAO.getCustomerRentedCar(customer);
        if(car.getName() != null){
            System.out.println();
            System.out.println("Your rented car:");
            System.out.println(car.getName());
            System.out.println("Company:");
            System.out.println(car.getCompany().getName());

        } else {
            System.out.println("You didn't rent a car!");
        }

    }
    public void showCompanyCar(Company company){
        List<Car> carList = carDAO.getByCompanyNotRented(company);
        if (carList.isEmpty()) {
            System.out.println();
            System.out.println("The car list is empty!");
        } else {
            System.out.println();
            System.out.println("Car list: ");
            for(int i = 0; i < carList.size(); i++){
                System.out.println(i+1 + ". "+carList.get(i).getName());
            }
            System.out.println();

        }
    }
}
