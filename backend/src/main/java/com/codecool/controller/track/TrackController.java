package com.codecool.controller.track;

import com.codecool.service.trackPage.TrackPageService;
import com.codecool.dto.GetMonthlyTransactionsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/track")
public class TrackController {
    private final TrackPageService trackPageService;
@Autowired
    public TrackController(TrackPageService trackPageService) {
        this.trackPageService = trackPageService;
    }

    @GetMapping("/{year}/{month}")
    public GetMonthlyTransactionsDTO getTransactionsForMonth(@PathVariable int year, @PathVariable int month){
    return trackPageService.getTransactionForMonth(year,month);
    }
}