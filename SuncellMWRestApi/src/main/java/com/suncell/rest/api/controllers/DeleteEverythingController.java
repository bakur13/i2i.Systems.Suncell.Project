package com.suncell.rest.api.controllers;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.suncell.rest.api.repository.DeleteEverything;

import io.swagger.annotations.ApiOperation;

public class DeleteEverythingController {

	
	DeleteEverything deleteEverything = new DeleteEverything();
    @GetMapping
    @ApiOperation("Delete everything from VoltDb,HazelCast,OracleDb")
    public ResponseEntity deleteEverything() throws SQLException {
        deleteEverything.deleteMap();
        deleteEverything.deleteVoltDb();
        deleteEverything.deleteOracleDb();
        return new ResponseEntity<>("All Datas are deleted", HttpStatus.OK);
    }
}
