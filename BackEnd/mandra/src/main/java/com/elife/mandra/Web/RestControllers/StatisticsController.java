package com.elife.mandra.Web.RestControllers;

import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elife.mandra.Business.Services.StatisticsService;
import com.elife.mandra.Web.Responses.ErrorResponse;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {



    final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;

    }


 @GetMapping("/counts")
 @PreAuthorize("hasAnyRole('Admin') and hasAuthority('READ_PRIVILEGE')")
 public ResponseEntity<Object> getCounts() {
    try {
     Map<String, Long> counts = statisticsService.getCounts() ;
    return ResponseEntity.status(HttpStatus.OK).body(counts);
    } catch (RuntimeException e) {
          ErrorResponse errorResponse = new ErrorResponse("Error while getting the content of all entities", e.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
}
 }


}
