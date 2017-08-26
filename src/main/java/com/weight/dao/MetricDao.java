package com.weight.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Component;

import com.weight.MorphiaConfiguration;
import com.weight.model.Metric;

@Component
public class MetricDao {

    Datastore datastore;

    MetricDao() {
        datastore = MorphiaConfiguration.getInstance().getDatastore();
    }

    public ObjectId createMetric(Metric metric) {
        datastore.save(metric);
        return metric.getId();
    }

    public List<Metric> read () {
        Query<Metric> query = datastore.createQuery(Metric.class);
        return query.asList();
    }

    public List<Metric> readByRange(long startTime, long endTime) {
        Query<Metric> query = datastore.createQuery(Metric.class)
                .filter("timeStamp >=", startTime).filter("timeStamp <=", endTime);
        return query.asList();
    }
}

