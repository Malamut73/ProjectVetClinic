package moduls.classes;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Note {

    private int idNote;
    private String parentFolderName;
    private Folder parentFolder;
    private String name;
    private String text;
    private Date creationDate;
    private Date updateDate;
    private int userId;
    private User staff;

    public Note(int idNote, String parentFolder, String name, String text, Date creationDate, Date updateDate, User staff) {
        this.idNote = idNote;
        this.parentFolderName = parentFolder;
        this.name = name;
        this.text = text;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.staff = staff;
    }
    public Note(String name, String text, int staffId, String parentFolder) {
        this.parentFolderName = parentFolder;
        this.name = name;
        this.text = text;
        this.userId = staffId;
    }

    public String description(){
        return "Name of note: " + name +
                "\n text of note: " + text +
                "\n user: " + staff.getFullName() +
                "\n date: " + updateDate;
    }

    @Override
    public String toString() {
        return "Note{" +
                "idNote=" + idNote +
                ", parentFolderName='" + parentFolderName + '\'' +
                ", parentFolder=" + parentFolder +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
