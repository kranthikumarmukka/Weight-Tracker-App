package com.weight.service;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

import java.util.List;

import org.bson.types.ObjectId;
import org.easyrules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weight.dao.MetricDao;
import com.weight.model.Metric;
import com.weight.rules.MetricsRule;
import com.weight.rules.RulesFactory;

@Component
public class MetricService {

    private RulesEngine rulesEngine;
    private MetricsRule rule;

    @Autowired
    private MetricDao metricDAO;

    MetricService() {
        rulesEngine = aNewRulesEngine().build();
    }

    public ObjectId createMetric(Metric metric) {
        rule = RulesFactory.getRule(MetricsRule.RuleType.UNDER_WEIGHT, metric);
        rulesEngine.registerRule(rule);
        rule = RulesFactory.getRule(MetricsRule.RuleType.OVER_WEIGHT, metric);
        rulesEngine.registerRule(rule);

        rulesEngine.fireRules();
        rulesEngine.clearRules();

        return metricDAO.createMetric(metric);
    }

    public List<Metric> read () {
        return metricDAO.read();
    }

    public List<Metric> readByRange(long startTime, long endTime) {
        return metricDAO.readByRange(startTime, endTime);
    }
}
