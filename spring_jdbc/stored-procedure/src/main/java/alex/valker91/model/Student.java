package alex.valker91.model;

import java.util.Date;

public class Student {
    private String name;
    private String surname;
    private Date date_of_birth;
    private String phone_numbers;
    private String primary_skill;
    private Date created_datetime;
    private Date updated_datetime;

    public Student(String name, String surname, Date date_of_birth, String phone_numbers, String primary_skill, Date created_datetime, Date updated_datetime) {
        this.name = name;
        this.surname = surname;
        this.date_of_birth = date_of_birth;
        this.phone_numbers = phone_numbers;
        this.primary_skill = primary_skill;
        this.created_datetime = created_datetime;
        this.updated_datetime = updated_datetime;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public String getPhone_numbers() {
        return phone_numbers;
    }

    public String getPrimary_skill() {
        return primary_skill;
    }

    public Date getCreated_datetime() {
        return created_datetime;
    }

    public Date getUpdated_datetime() {
        return updated_datetime;
    }
}
