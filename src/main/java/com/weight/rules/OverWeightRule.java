package com.weight.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

import com.weight.dao.AlertDao;
import com.weight.model.Alert;
import com.weight.model.Metric;
import com.weight.rules.MetricsRule;

@Rule(name = "Over-Weight")
public class OverWeightRule implements MetricsRule{

    private AlertDao alertDAO = new AlertDao();

    private Metric metric;

    public OverWeightRule(Metric metric) {
        this.metric = metric;
    }

    @Override
    @Condition
    public boolean when() {
        double percent = ((double) metric.getValue()) / baseWeight;

        return percent > 1.1;

    }

    @Override
    @Action
    public void then() {
        Alert alert = new Alert(MetricsRule.RuleType.OVER_WEIGHT.toString(), metric.getTimeStamp(), metric.getValue());

        alertDAO.create(alert);
    }
}

