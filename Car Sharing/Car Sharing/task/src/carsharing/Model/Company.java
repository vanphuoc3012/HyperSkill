package carsharing.Model;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private int id;
    private String name;

    private List<Car> carList = new ArrayList<>();

    public Company( String name) {
        this.name = name;
    }

    public Company(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Company() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getId() +". "+this.getName();
    }

    public List<Car> getCarList() {
        return carList;
    }

}
