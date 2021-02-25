package com.altynnikov.ProMotorTestTask.services;

import com.altynnikov.ProMotorTestTask.modes.TableRecord;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TableRecordService {
    private Map<String, TableRecord> tableRecords;

    @PostConstruct
    public void init() {
        tableRecords = new HashMap<>();
        String[] months = {"January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"};
        for (int i = 0; i < months.length; i++) {
            tableRecords.put(String.valueOf(i+1), new TableRecord(i+1, months[i]));
        }
    }

    public TableRecord addRecord(TableRecord tableRecord) {
        int generatedId = tableRecords.size() + 1;
        tableRecord.setId(generatedId);
        tableRecords.put(String.valueOf(generatedId), tableRecord);
        return tableRecords.get(String.valueOf(generatedId));
    }

    public List<TableRecord> getRecordBySybString(String subString) {
        if (subString == null)
            return new ArrayList<>(tableRecords.values());

        List<TableRecord> resultList = new ArrayList<>();
        for (Map.Entry<String, TableRecord> entry : tableRecords.entrySet()) {
            if (entry.getValue().getValue().toLowerCase().matches(".*" + subString + ".*")){
                resultList.add(entry.getValue());
            }
        }
        return resultList;
    }

    public TableRecord updateTableRecord(TableRecord updatedTableRecord) {
        String key = String.valueOf(updatedTableRecord.getId());
        tableRecords.replace(key, updatedTableRecord);
        return tableRecords.get(key);
    }

    public boolean removeTableRecord(TableRecord tableRecord) {
        return tableRecords.remove(String.valueOf(tableRecord.getId()), tableRecord);
    }
}
