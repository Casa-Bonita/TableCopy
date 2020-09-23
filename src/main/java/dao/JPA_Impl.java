package dao;

import models.Staff;
import javax.persistence.*;
import java.util.List;

public class JPA_Impl implements DAO <Staff>{

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA_Unit");
    EntityManager em = entityManagerFactory.createEntityManager();

    @Override
    public void save(List<Staff> listStaff) {

        em.getTransaction().begin();

        for(Staff c : listStaff){
            em.persist(c);
        }

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Staff> getAll() {

        em.getTransaction().begin();

        List<Staff> listStaff = em.createQuery("from Staff").getResultList();

        return listStaff;
    }

    @Override
    public Staff getById(int id) {

        em.getTransaction().begin();
        Staff staff = (Staff) em.find(Staff.class, id);

        return staff;
    }

    @Override
    public void update(Staff oldStaff, Staff newStaff) {

    }

    @Override
    public void remove(Staff staff) {

    }
}
