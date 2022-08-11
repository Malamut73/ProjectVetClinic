package moduls.classes;

import java.util.Objects;

public class Folder {

    private String name;
    private String parentFolderName;
    private Folder parentFolder;

    public Folder(String name, String parentFolderName) {
        this.name = name;
        this.parentFolderName = parentFolderName;
    }

    public String getParentFolderName() {
        return parentFolderName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Folder getParentFolder() {
        return parentFolder;
    }
    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Folder folder = (Folder) o;
        return Objects.equals(name, folder.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name + " " + parentFolderName;
    }
}
