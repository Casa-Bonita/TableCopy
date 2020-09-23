package dao;

import java.util.List;

public interface DAO <T> {

    // create
    void save (List<T> t);

    // read all
    List<T> getAll();

    // read by id
    T getById(int id);

    // update
    void update (T oldT, T newT);

    // delete
    void remove (T t);

}
