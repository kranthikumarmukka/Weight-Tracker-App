package com.weight.rules;

import com.weight.model.Metric;
import com.weight.rules.MetricsRule;
import com.weight.rules.OverWeightRule;
import com.weight.rules.UnderWeightRule;
import com.weight.rules.MetricsRule.RuleType;

public class RulesFactory {

    public static MetricsRule getRule (RuleType type, Metric metric) {

        if (type == RuleType.OVER_WEIGHT)
            return new OverWeightRule(metric);
        else if (type == RuleType.UNDER_WEIGHT)
            return new UnderWeightRule(metric);

        return null;
    }
}
