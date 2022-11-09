package carsharing.Model;

import carsharing.ConnectJDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO implements DAO<Company>{

    private ConnectJDBC connectJDBC;


    public CompanyDAO(ConnectJDBC connectJDBC) {
        this.connectJDBC = connectJDBC;
    }

    @Override
    public List<Company> getAll() {
        List<Company> companies = new ArrayList<>();
        String sql = "SELECT * FROM COMPANY ORDER BY id;";
        ResultSet rs = connectJDBC.query(sql);
        try{
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Company n = new Company(id, name);
                companies.add(n);
            }
        }catch (SQLException sq){
            sq.printStackTrace();
        }
        return companies;
    }

    @Override
    public Company get() {
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void add(Company company) {
        String name = company.getName();
        String sql = "INSERT INTO COMPANY (NAME) VALUES ('" + name +"');";
        connectJDBC.update(sql);
    }

    public Company getByID(int companyID){
        String sql = "SELECT * FROM COMPANY WHERE id = " +companyID+";" ;
        ResultSet rs = connectJDBC.query(sql);
        Company n = new Company();
        try{
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                n.setId(id);
                n.setName(name);
            }
        }catch (SQLException sq){
            sq.printStackTrace();
        }
        return n;
    }


}
