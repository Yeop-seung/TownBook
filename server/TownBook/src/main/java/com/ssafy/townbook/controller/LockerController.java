package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.service.LockerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locker")
@RequiredArgsConstructor
public class LockerController {
    
    private LockerService lockerService;
    
    @Autowired
    public LockerController(LockerService lockerService) {
        this.lockerService = lockerService;
    }
    
    @GetMapping("")
    public ResponseEntity<?> lockers() throws Exception {
        return new ResponseEntity<>(lockerService.findAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{lockerNo}")
    public ResponseEntity<?> findLockerByLockerNo(@PathVariable Long lockerNo) {
        return new ResponseEntity<>(lockerService.findLockerByLockerNo(lockerNo), HttpStatus.OK);
    }
    
    @PostMapping("/add/{lockerRegion}")
    public ResponseEntity<?> addLocker(@PathVariable String lockerRegion) {
        return new ResponseEntity<>(lockerService.addLocker(lockerRegion), HttpStatus.OK);
    }
}
