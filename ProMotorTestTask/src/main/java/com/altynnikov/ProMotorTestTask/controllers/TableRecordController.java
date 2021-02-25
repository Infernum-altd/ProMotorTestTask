package com.altynnikov.ProMotorTestTask.controllers;

import com.altynnikov.ProMotorTestTask.modes.TableRecord;

import com.altynnikov.ProMotorTestTask.services.TableRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/record")
@CrossOrigin
public class TableRecordController {
    private final TableRecordService tableRecordService;

    @Autowired
    public TableRecordController(TableRecordService tableRecordService) {
        this.tableRecordService = tableRecordService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<TableRecord> addRecord(@RequestBody TableRecord tableRecord) {
        TableRecord newRecord = tableRecordService.addRecord(tableRecord);
        if (newRecord != null) {
            return new ResponseEntity<>(newRecord, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = {"/get/", "/get/{substring}"})
    public ResponseEntity<List<TableRecord>> getValueBySubString(@PathVariable(required = false) String substring) {
        List<TableRecord> resultList = tableRecordService.getRecordBySybString(substring);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<TableRecord> updateRecord(@RequestBody TableRecord updatedTableRecord) {
        return new ResponseEntity<>(tableRecordService.updateTableRecord(updatedTableRecord),
                HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public HttpStatus deleteRecord(@RequestBody TableRecord tableRecord) {
        boolean isDelete = tableRecordService.removeTableRecord(tableRecord);

        return isDelete ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
