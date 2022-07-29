package repository;

import moduls.Staff;

import java.util.Set;

public interface StaffRepository {

    void saveStaff (Staff staff);
    void removeStaff (Staff staff);
    void editStaff (Staff staff);
    boolean findAll();
    Staff getStaff(Staff staff);



}
