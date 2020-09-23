import models.Staff;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

public class StaffTableModel implements TableModel {
    public List<Staff> listStaff;

    public StaffTableModel(List<Staff> lisfStaff) {
        this.listStaff = lisfStaff;
    }

    public void add(Staff staff){
        listStaff.add(staff);
    }

    public void remove (int index){
        listStaff.remove(index);
    }

    public Staff get(int index){
        return listStaff.get(index);
    }

    public void update(int inputId, Staff newStaff){
        Staff changedStaff = new Staff();
        changedStaff = listStaff.get(inputId);

        changedStaff.setName(newStaff.getName());
        changedStaff.setSurname(newStaff.getSurname());
        changedStaff.setPosition(newStaff.getPosition());
        changedStaff.setPhoneNumber(newStaff.getPhoneNumber());
        changedStaff.setAge(newStaff.getAge());
    }

    @Override
    public int getRowCount() {
        return listStaff.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0: return "ID";
            case 1: return "Name";
            case 2: return "Surname";
            case 3: return "Position";
            case 4: return "PhoneNumber";
            case 5: return "Age";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0: return Integer.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return String.class;
            case 4: return String.class;
            case 5: return Integer.class;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Staff staff = listStaff.get(rowIndex);
        switch (columnIndex){
            case 0: return staff.getId();
            case 1: return staff.getName();
            case 2: return staff.getSurname();
            case 3: return staff.getPosition();
            case 4: return staff.getPhoneNumber();
            case 5: return staff.getAge();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}