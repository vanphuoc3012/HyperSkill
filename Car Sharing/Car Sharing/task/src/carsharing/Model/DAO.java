package carsharing.Model;

import java.util.List;

public interface DAO<T> {
    List<T> getAll();
    T get();
    void update();
    void delete();
    void add(T t);
}
