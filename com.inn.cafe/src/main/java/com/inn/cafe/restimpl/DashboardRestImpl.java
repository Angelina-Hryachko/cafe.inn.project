package com.inn.cafe.restimpl;

import com.inn.cafe.constents.CafeConstants;
import com.inn.cafe.rest.DashboardRest;
import com.inn.cafe.service.DashboardService;
import com.inn.cafe.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DashboardRestImpl implements DashboardRest {

    private DashboardService dashboardService;

    @Autowired
    public DashboardRestImpl(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        try {
            return dashboardService.getCount();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        return new ResponseEntity<Map<String, Object>>(new HashMap<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
