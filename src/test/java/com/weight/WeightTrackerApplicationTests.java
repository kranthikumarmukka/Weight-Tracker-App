package com.weight;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.weight.model.Alert;
import com.weight.model.Metric;
import com.weight.rules.MetricsRule;
import com.weight.service.AlertService;
import com.weight.service.MetricService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeightTrackerApplicationTests {

	@Autowired
	private MetricService metricService;

	@Autowired
	private AlertService alertService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void check_alert_Overweight() {

		long timeStamp = 1503780768253l;

		Metric metric = new Metric();
		metric.setValue((int) (1.2 * MetricsRule.baseWeight));
		metric.setTimeStamp(timeStamp);

		metricService.createMetric(metric);

		List<Alert> alertList = alertService.read();
		for (Alert alert : alertList) {
			if (alert.getTimeStamp() == timeStamp && alert.getType().equals("OVER_WEIGHT")) {
				assert (true);
				return;
			}
		}

		assert (false);
	}

	@Test
	public void check_alert_Underweight() {

		long timeStamp = 1503780768253l;

		Metric metric = new Metric();
		metric.setValue((int) (0.8 * MetricsRule.baseWeight));

		metric.setTimeStamp(timeStamp);

		metricService.createMetric(metric);

		List<Alert> alertList = alertService.read();
		for (Alert alert : alertList) {
			if (alert.getTimeStamp() == timeStamp && alert.getType().equals("UNDER_WEIGHT")) {
				assert (true);
				return;
			}
		}

		assert (false);
	}

	@Test
	public void contextLoads() {
	}

}
