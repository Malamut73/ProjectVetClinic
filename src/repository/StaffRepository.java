package repository;

import moduls.classes.Staff;

import java.util.List;

public interface StaffRepository {

    void saveStaff (Staff staff);
    void removeStaff (Staff staff);
    void editStaff (Staff staff);
    List<Staff> findAll();
    Staff findStaff(Staff staff);
    Staff findStaff(int id);
    void saveLogAndPass(Staff staff);



}
