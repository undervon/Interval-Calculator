package com.aaqua.interval.calculator.controller;

import com.aaqua.interval.calculator.model.IntervalDTO;
import com.aaqua.interval.calculator.service.IntervalCalculatorServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/1.0")
public class IntervalCalculatorController {

    private final IntervalCalculatorServiceImpl intervalCalculatorServiceImpl;

    @PostMapping(path = "/addAnInterval",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IntervalDTO> addAnIntervalReq(
            @RequestBody IntervalDTO intervalDTO) {
        log.info("[ {} ] -> [ {} ] -> [ addAnIntervalReq ] intervalDTO: {}",
                this.getClass().getSimpleName(), HttpMethod.POST, intervalDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(intervalCalculatorServiceImpl.addAnIntervalReq(intervalDTO));
    }

    @GetMapping(path = "/listAllIntervals", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<?>> listAllIntervalsReq() {
        log.info("[ {} ] -> [ {} ] -> [ listAllIntervalsReq ]",
                this.getClass().getSimpleName(), HttpMethod.GET);

        return ResponseEntity.status(HttpStatus.OK)
                .body(intervalCalculatorServiceImpl.listAllIntervalsReq());
    }

    @DeleteMapping(path = "/removeAnInterval/{id}")
    public ResponseEntity<?> removeAnIntervalReq(@PathVariable(name = "id") Integer id) {
        log.info("[ {} ] -> [ {} ] -> [ removeAnIntervalReq ] id: {}",
                this.getClass().getSimpleName(), HttpMethod.DELETE, id);

        intervalCalculatorServiceImpl.removeAnIntervalReq(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping(path = "/viewAnInterval/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> viewAnIntervalReq(@PathVariable(name = "id") Integer id) {
        log.info("[ {} ] -> [ {} ] -> [ viewAnIntervalReq ] id: {}",
                this.getClass().getSimpleName(), HttpMethod.GET, id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(intervalCalculatorServiceImpl.viewAnIntervalReq(id));
    }
}
