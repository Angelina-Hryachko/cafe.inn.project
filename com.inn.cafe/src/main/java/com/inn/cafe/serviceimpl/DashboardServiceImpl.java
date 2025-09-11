package com.inn.cafe.serviceimpl;

import com.inn.cafe.dao.BillDao;
import com.inn.cafe.dao.CategoryDao;
import com.inn.cafe.dao.ProductDao;
import com.inn.cafe.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {

    private CategoryDao categoryDao;
    private ProductDao productDao;
    private BillDao billDao;

    @Autowired
    public DashboardServiceImpl(CategoryDao categoryDao, ProductDao productDao, BillDao billDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
        this.billDao = billDao;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("category", categoryDao.count());
            map.put("product", productDao.count());
            map.put("bill", billDao.count());
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        return new ResponseEntity<Map<String, Object>>(new HashMap<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
