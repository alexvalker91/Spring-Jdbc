package alex.valker91.model;

import java.util.Map;

public class Table {

    private String name;
    private Map<String, Type> columNamesAndType;

    public Table(String name, Map<String, Type> columNamesAndType) {
        this.name = name;
        this.columNamesAndType = columNamesAndType;
    }

    public String getName() {
        return name;
    }

    public Map<String, Type> getColumNamesAndType() {
        return columNamesAndType;
    }
}
