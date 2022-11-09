package carsharing.ui;

import carsharing.Model.*;

import java.util.List;
import java.util.Scanner;

public class ManagerUI {
    private CustomerDAO customerDAO;
    private CompanyDAO companyDAO;
    private CarDAO carDAO;
    private Scanner scanner;

    public ManagerUI(CustomerDAO customerDAO, CompanyDAO companyDAO, CarDAO carDAO) {
        this.customerDAO = customerDAO;
        this.companyDAO = companyDAO;
        this.carDAO = carDAO;
        this.scanner = new Scanner(System.in);
    }

    public void start(){

        while (true){
            System.out.println();
            System.out.println("1. Company list\n" +
                    "2. Create a company\n" +
                    "0. Back");
            int index = Integer.valueOf(scanner.nextLine());
            if(index == 1){
                companyList();
            } else if (index == 2) {
                addCompany();
            } else if (index == 0) {
                break;
            }
        }
    }

    public void companyList(){
        List<Company> temp = companyDAO.getAll();
        if(temp.isEmpty()) {
            System.out.println("\nThe company list is empty!");
        } else {
            chooseCompany(temp);
        }
    }

    public void chooseCompany(List<Company> companyList){
//        while(true){
            System.out.println();
            System.out.println("Choose a company:");
            for(int i = 0; i < companyList.size(); i++){
                System.out.println(i+1+". "+companyList.get(i).getName());
            }
            System.out.println("0. Back");

            int index = Integer.valueOf(scanner.nextLine());
            if(index == 0){
//
            } else {
                while (true){
                    Company c = companyList.get(index - 1);
                    System.out.println("\n'"+c.getName()+"' company:");
                    System.out.println();
                    System.out.println("1. Car list\n" +
                            "2. Create a car\n" +
                            "0. Back");
                    int i = Integer.valueOf(scanner.nextLine());
                    if(i == 1){
                        showCompanyCar(c);
                    } else if (i == 2) {
                        System.out.println("Enter the car name:");
                        String carName = String.valueOf(scanner.nextLine());
                        Car newCar = new Car();
                        newCar.setName(carName);
                        newCar.setCompany(c);
                        carDAO.add(newCar);
                        System.out.println("The car was added!");

                    } else if (i == 0) {
                        break;
                    }
                }
//            }

        }
    }

    public void addCompany(){
        System.out.println("Enter the company name:");
        String name = String.valueOf(scanner.nextLine());
        companyDAO.add(new Company(name));
        System.out.println("The company was created!");
    }

    public void showCompanyCar(Company company){
        List<Car> carList = carDAO.getByCompany(company);
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
