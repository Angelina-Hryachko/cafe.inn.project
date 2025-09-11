package com.inn.cafe.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface DashboardService {

    ResponseEntity<Map<String, Object>> getCount();
}
