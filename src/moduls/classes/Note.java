package moduls.classes;

import java.util.Date;

public class Note {

    private int idNote;
    private String parentFolderName;
    private Folder parentFolder;
    private String name;
    private String text;
//    private String authorEmail;
    private Date creationDate;
    private Date updateDate;
    private int staffId;

    public Note(int idNote, String parentFolder, String name, String text, Date creationDate, Date updateDate, int staffId) {
        this.idNote = idNote;
        this.parentFolderName = parentFolder;
        this.name = name;
        this.text = text;
//        this.authorEmail = authorEmail;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.staffId = staffId;
    }

    public Note(int idNote, String name, String text, String parentFolder, String authorEmail) {
        this.idNote = idNote;
        this.parentFolderName = parentFolder;
        this.name = name;
        this.text = text;
//        this.authorEmail = authorEmail;
    }

    public Note(String name, String text, int staffId, String parentFolder) {
        this.parentFolderName = parentFolder;
        this.name = name;
        this.text = text;
        this.staffId = staffId;
    }
    public void setText(String text){
        this.text = text;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public int getUserId(){
        return staffId;
    }

    public void setDay(Date date){
        this.creationDate = date;
    }

    public String getParentFolderName() {
        return parentFolderName;
    }

    public void setParentFolderName(String parentFolderName) {
        this.parentFolderName = parentFolderName;
    }

    public Folder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

//    public String getAuthorEmail() {
//        return authorEmail;
//    }
//
//    public void setAuthorEmail(String authorEmail) {
//        this.authorEmail = authorEmail;
//    }

    @Override
    public String toString() {
        return "Note{" +
                "idNote=" + idNote +
                ", parentFolderName='" + parentFolderName + '\'' +
                ", parentFolder=" + parentFolder +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
//                ", authorEmail='" + authorEmail + '\'' +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
