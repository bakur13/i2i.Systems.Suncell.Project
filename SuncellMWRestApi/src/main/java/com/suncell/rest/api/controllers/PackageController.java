package com.suncell.rest.api.controllers;

import java.sql.SQLException;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.suncell.rest.api.repository.PackageRepository;
import com.suncell.rest.api.resources.PackageList;
import com.suncell.rest.api.resources.Package;

@RestController
@RequestMapping("/api/packages/")
public class PackageController {

    PackageRepository packageRepository = new PackageRepository();
	
	@GetMapping(value = "/getpackages")
    public List<Package> packageList() throws SQLException {
        return packageRepository.packageList();
    }

    @GetMapping(value = "/packageIdName")
    public List<PackageList> packageLists() throws SQLException {
        return packageRepository.packageLists();
    }
	
}
