package com.weight.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weight.dao.AlertDao;
import com.weight.model.Alert;

@Component
public class AlertService {


    @Autowired
    private AlertDao alertDAO;

    public AlertService() {
    }

    public ObjectId create (Alert alert) {
        return alertDAO.create(alert);
    }

    public List<Alert> read () {
        return alertDAO.read();
    }

    public List<Alert> readByRange(long startTime, long endTime) {
        return alertDAO.readByRange(startTime, endTime);
    }
}

