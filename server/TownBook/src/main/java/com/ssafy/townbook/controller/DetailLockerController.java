package com.ssafy.townbook.controller;

import com.ssafy.townbook.model.service.DetailLockerService;
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
@RequestMapping("/detailLocker")
@RequiredArgsConstructor
public class DetailLockerController {
    
    private DetailLockerService detailLockerService;
    
    @Autowired
    public DetailLockerController(DetailLockerService detailLockerService) {
        this.detailLockerService = detailLockerService;
    }
    
    @GetMapping("")
    public ResponseEntity<?> detailLockers() throws Exception {
        return new ResponseEntity<>(detailLockerService.findAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{detailLockerNo}")
    public ResponseEntity<?> findDetailLockerByDetailLockerNo(@PathVariable Long detailLockerNo) {
        return new ResponseEntity<>(detailLockerService.findDetailLockerByDetailLockerNo(detailLockerNo),
                HttpStatus.OK);
    }
    
    @PostMapping("/add/{lockerNo}")
    public ResponseEntity<?> addDetailLocker(@PathVariable Long lockerNo) {
        return new ResponseEntity<>(detailLockerService.addDetailLocker(lockerNo), HttpStatus.OK);
    }
}
