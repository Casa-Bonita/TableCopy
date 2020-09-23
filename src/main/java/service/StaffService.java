package service;

import dao.DAO;
import dao.File_Impl;
import dao.JPA_Impl;
import models.Staff;

import java.util.List;

public class StaffService {

    String pathFile = "src/main/java/data.txt";

    //DAO<Staff> dao = new File_Impl(pathFile);
    DAO<Staff> dao = new JPA_Impl();

    public void save (List<Staff> listSTaff){
        dao.save(listSTaff);
    }

    public List<Staff> getAll(){
        List<Staff> listSTaff = dao.getAll();
        return listSTaff;
    }

    public Staff getById(int id){
        return dao.getById(id);
    }

    public void update(Staff oldStaff, Staff newStaff){
        List<Staff> listSTaff = dao.getAll();
        int index = 0;
        for (int i = 0; i < listSTaff.size(); i++) {
            if(listSTaff.get(i).getId() == oldStaff.getId()){
                index = i;
            }
        }
        Staff staff = dao.getById(oldStaff.getId());
        staff.setName(newStaff.getName());
        staff.setSurname(newStaff.getSurname());
        staff.setPosition(newStaff.getPosition());
        staff.setPhoneNumber(newStaff.getPhoneNumber());
        listSTaff.remove(index);
        listSTaff.add(staff);
        dao.save(listSTaff);
    }

    public void delete(Staff staff){
        List<Staff> listSTaff = dao.getAll();
        for (int i = 0; i < listSTaff.size(); i++) {
            if(listSTaff.get(i).getId() == staff.getId()){
                listSTaff.remove(i);
            }
        }
        dao.save(listSTaff);
    }

}
