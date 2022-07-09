package com.aaqua.interval.calculator.service;

import com.aaqua.interval.calculator.exception.IntervalCalculatorNotFoundException;
import com.aaqua.interval.calculator.model.FirstIntervalDurationDTO;
import com.aaqua.interval.calculator.model.IntervalDTO;
import com.aaqua.interval.calculator.model.IntervalDurationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

@Log4j2
@Service
@RequiredArgsConstructor
public class IntervalCalculatorServiceImpl implements IntervalCalculatorService {

    private static final TreeSet<IntervalDTO> INTERVAL_DTO_SET = new TreeSet<>(
            Comparator.comparing(IntervalDTO::getId));

    private String calculateDifferenceBetweenTwoDate(LocalDateTime startDate, LocalDateTime endDate) {
        LocalDateTime newStartDate = startDate;

        long years = newStartDate.until(endDate, ChronoUnit.YEARS);
        newStartDate = newStartDate.plusYears(years);

        long months = newStartDate.until(endDate, ChronoUnit.MONTHS);
        newStartDate = newStartDate.plusMonths(months);

        long days = newStartDate.until(endDate, ChronoUnit.DAYS);
        newStartDate = newStartDate.plusDays(days);

        long hours = newStartDate.until(endDate, ChronoUnit.HOURS);
        newStartDate = newStartDate.plusHours(hours);

        long minutes = newStartDate.until(endDate, ChronoUnit.MINUTES);
        newStartDate = newStartDate.plusMinutes(minutes);

        long seconds = newStartDate.until(endDate, ChronoUnit.SECONDS);

        String dateIntervalInitial = "";

        if (years != 0) {
            if (years == 1) {
                dateIntervalInitial = dateIntervalInitial + years + " year ";
            } else {
                dateIntervalInitial = dateIntervalInitial + years + " years ";
            }
        }

        if (months != 0) {
            if (months == 1) {
                dateIntervalInitial = dateIntervalInitial + months + " month ";
            } else {
                dateIntervalInitial = dateIntervalInitial + months + " months ";
            }
        }

        if (days != 0) {
            if (days == 1) {
                dateIntervalInitial = dateIntervalInitial + days + " day ";
            } else {
                dateIntervalInitial = dateIntervalInitial + days + " days ";
            }
        }

        if (hours != 0) {
            dateIntervalInitial = dateIntervalInitial + hours + "h ";
        }

        if (minutes != 0) {
            dateIntervalInitial = dateIntervalInitial + minutes + "m ";
        }

        if (seconds != 0) {
            dateIntervalInitial = dateIntervalInitial + seconds + "s ";
        }

        StringBuilder dateIntervalBuilder = new StringBuilder(dateIntervalInitial);
        String dateIntervalFinal = "";

        if (dateIntervalInitial.endsWith(" ")) {
            dateIntervalFinal = dateIntervalBuilder.deleteCharAt(dateIntervalInitial.length() - 1).toString();
        }

        return dateIntervalFinal;
    }

    @Override
    public IntervalDTO addAnIntervalReq(IntervalDTO intervalDTO) {
        INTERVAL_DTO_SET.add(intervalDTO);

        return intervalDTO;
    }

    @Override
    public List<Object> listAllIntervalsReq() {
        List<Object> intervalDurationDTOList = new LinkedList<>();

        if (INTERVAL_DTO_SET.isEmpty()) {
            return intervalDurationDTOList;
        }

        IntervalDTO firstIntervalDTO = INTERVAL_DTO_SET.iterator().next();

        // adding the first object in the list
        intervalDurationDTOList.add(FirstIntervalDurationDTO.builder()
                .id(firstIntervalDTO.getId())
                .start(firstIntervalDTO.getStart())
                .end(firstIntervalDTO.getEnd())
                .duration(calculateDifferenceBetweenTwoDate(firstIntervalDTO.getStart(), firstIntervalDTO.getEnd()))
                .build());

        // adding all objects without the first one in the list
        INTERVAL_DTO_SET.stream()
                .skip(1)
                .forEach(intervalDTO -> {
                    IntervalDTO previousIntervalDTO = INTERVAL_DTO_SET.lower(intervalDTO);
                    if (previousIntervalDTO == null) {
                        throw new IntervalCalculatorNotFoundException(intervalDTO.getId().toString());
                    }

                    intervalDurationDTOList.add(IntervalDurationDTO.builder()
                            .id(intervalDTO.getId())
                            .start(intervalDTO.getStart())
                            .end(intervalDTO.getEnd())
                            .duration(calculateDifferenceBetweenTwoDate(intervalDTO.getStart(), intervalDTO.getEnd()))
                            .breakDuration(calculateDifferenceBetweenTwoDate(previousIntervalDTO.getEnd(),
                                    intervalDTO.getStart()))
                            .build());
                });

        return intervalDurationDTOList;
    }

    @Override
    public void removeAnIntervalReq(Integer id) {
        if (!INTERVAL_DTO_SET.removeIf(intervalDTO -> intervalDTO.getId().equals(id))) {
            throw new IntervalCalculatorNotFoundException(id.toString());
        }
    }

    @Override
    public Object viewAnIntervalReq(Integer id) {
        IntervalDTO currentIntervalDTO = INTERVAL_DTO_SET.stream()
                .filter(intervalDTO -> intervalDTO.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IntervalCalculatorNotFoundException(id.toString()));

        // check if it is the first
        if (id.equals(INTERVAL_DTO_SET.iterator().next().getId())) {
            return FirstIntervalDurationDTO.builder()
                    .id(currentIntervalDTO.getId())
                    .start(currentIntervalDTO.getStart())
                    .end(currentIntervalDTO.getEnd())
                    .duration(calculateDifferenceBetweenTwoDate(currentIntervalDTO.getStart(),
                            currentIntervalDTO.getEnd()))
                    .build();
        } else {
            IntervalDTO previousIntervalDTO = INTERVAL_DTO_SET.lower(currentIntervalDTO);
            if (previousIntervalDTO == null) {
                throw new IntervalCalculatorNotFoundException(id.toString());
            }

            return IntervalDurationDTO.builder()
                    .id(currentIntervalDTO.getId())
                    .start(currentIntervalDTO.getStart())
                    .end(currentIntervalDTO.getEnd())
                    .duration(calculateDifferenceBetweenTwoDate(currentIntervalDTO.getStart(),
                            currentIntervalDTO.getEnd()))
                    .breakDuration(calculateDifferenceBetweenTwoDate(previousIntervalDTO.getEnd(),
                            currentIntervalDTO.getStart()))
                    .build();
        }
    }
}
