package com.altynnikov.ProMotorTestTask.modes;

import java.util.Objects;

public class TableRecord {
    private int id;
    private String value;

    public TableRecord() {
    }

    public TableRecord(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public TableRecord(String value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableRecord that = (TableRecord) o;
        return id == that.id && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }
}
