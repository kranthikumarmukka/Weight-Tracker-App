package com.weight.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Component;

import com.weight.MorphiaConfiguration;
import com.weight.model.Alert;

@Component
public class AlertDao {

    Datastore datastore;

    public AlertDao() {
        datastore = MorphiaConfiguration.getInstance().getDatastore();
    }

    public ObjectId create(Alert alert) {
        Datastore datastore = MorphiaConfiguration.getInstance().getDatastore();
        datastore.save(alert);
        return alert.getId();
    }

    public List<Alert> read () {
        Query<Alert> query = datastore.createQuery(Alert.class);

        return query.asList();
    }

    public List<Alert> readByRange(long startTime, long endTime) {
        Query<Alert> query = datastore.createQuery(Alert.class)
                .filter("timeStamp >=", startTime).filter("timeStamp <=", endTime);

        return query.asList();
    }

}
