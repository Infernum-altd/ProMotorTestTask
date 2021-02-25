package com.altynnikov.ProMotorTestTask;

import com.altynnikov.ProMotorTestTask.modes.TableRecord;
import com.altynnikov.ProMotorTestTask.services.TableRecordService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TableRecordServiceTest {
    @Mock
    TableRecordService tableRecordService;

    @Test
    public void addRecordTest() {
        TableRecord tableRecord = new TableRecord("new");

        when(tableRecordService.addRecord(tableRecord)).thenReturn(new TableRecord(13, "new"));
        tableRecord.setId(13);
        assertEquals(tableRecord, tableRecordService.addRecord(tableRecord));
    }

    @Test
    public void getRecordBySubstringTest() {
        String substring = "october";
        TableRecord tableRecord = new TableRecord(10, "October");
        List<TableRecord> expected = new ArrayList<>();
        expected.add(tableRecord);
        when(tableRecordService.getRecordBySybString(substring)).thenReturn(expected);

        assertEquals(expected.get(0), tableRecordService.getRecordBySybString(substring).get(0));
    }

    @Test
    public void getAllRecordBySubstringTest() {
        List<TableRecord> expected = new ArrayList<>();
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        for (int i = 0; i < months.length; i++) {
            expected.add(new TableRecord(i+1, months[i]));
        }

        when(tableRecordService.getRecordBySybString(null)).thenReturn(expected);

        assertEquals(expected.size(), tableRecordService.getRecordBySybString(null).size());
    }

    @Test
    public void getRecordBySubstringNoMatchTest() {
        String substring = "none";
        when(tableRecordService.getRecordBySybString(substring)).thenReturn(new ArrayList<>());
        assertEquals(0, tableRecordService.getRecordBySybString(substring).size());
    }

    @Test
    public void updateRecordTest() {
        TableRecord tableRecord = new TableRecord(1, "NewJanuary");
        when(tableRecordService.updateTableRecord(tableRecord)).thenReturn(tableRecord);
        assertEquals(tableRecord, tableRecordService.updateTableRecord(tableRecord));
    }

    @Test
    public void deleteRecordTest() {
        TableRecord tableRecord = new TableRecord(1, "NewJanuary");
        when(tableRecordService.removeTableRecord(tableRecord)).thenReturn(true);
        assertTrue(tableRecordService.removeTableRecord(tableRecord));
    }

    @Test
    public void deleteRecordFalseTest() {
        TableRecord tableRecord = new TableRecord();
        when(tableRecordService.removeTableRecord(tableRecord)).thenReturn(false);
        assertFalse(tableRecordService.removeTableRecord(tableRecord));
    }
}
