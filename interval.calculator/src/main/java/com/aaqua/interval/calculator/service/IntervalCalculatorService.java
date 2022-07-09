package com.aaqua.interval.calculator.service;

import com.aaqua.interval.calculator.model.IntervalDTO;

import java.util.List;

public interface IntervalCalculatorService {

    IntervalDTO addAnIntervalReq(IntervalDTO intervalDTO);

    List<Object> listAllIntervalsReq();

    void removeAnIntervalReq(Integer id);

    Object viewAnIntervalReq(Integer id);
}
