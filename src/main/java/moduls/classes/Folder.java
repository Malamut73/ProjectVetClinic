package moduls.classes;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Folder {

    private String name;
    private String parentFolderName;
    private Folder parentFolder;

    public Folder(String name, String parentFolderName) {
        this.name = name;
        this.parentFolderName = parentFolderName;
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
