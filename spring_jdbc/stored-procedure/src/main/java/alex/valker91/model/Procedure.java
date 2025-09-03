package alex.valker91.model;

public enum Procedure {
    ADD_STUDENT("addStudent.sql", "addStudent"),
    DELETE_STUDENTS("deleteStudent.sql", "deleteStudent"),
    READ_STUDENT("readStudent.sql", "readStudent"),
    UPDATE_STUDENT_PHONE("updateStudentPhone.sql", "updateStudentPhone");

    private String path;
    private String name;

    private Procedure(String path, String name) {
        this.path = path;
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }
}
