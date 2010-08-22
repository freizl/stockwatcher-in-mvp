package com.google.gwt.sample.stockwatcher.client;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * HtmlUnit Test Cases.
 */
public class StockWatcherHtmlUnitTest {

	private static final String URL = "http://127.0.0.1:8888/StockWatcherMVP.html";

	@Test
	public void testAddNone() throws Exception {
		final WebClient webClient = new WebClient();
		final HtmlPage page = webClient.getPage(URL);

		// TODO: Waiting for Async Call finish. TODO: existing HtmlUnit API?
		Thread.sleep(2000);

		List<?> flexTableRows = page
				.getByXPath("//body//div[@id='stockList']//table[@title='stockFlexTable']//tr");
		assertEquals(1, flexTableRows.size());

		List<?> inputs = page
				.getByXPath("//body//div[@id='stockList']//input[@title='newSymbolText']");
		assertEquals(1, inputs.size());

		final List<String> collectedAlerts = new ArrayList<String>();
		webClient.setAlertHandler(new CollectingAlertHandler(collectedAlerts));

		List<?> buttons = page
				.getByXPath("//body//div[@id='stockList']//button[@title='addStockButton']");
		assertEquals(1, buttons.size());
		HtmlButton addButton = (HtmlButton) buttons.get(0);
		addButton.click();

		final List<String> expectedAlerts = Collections
				.singletonList("'' is not a valid symbol.");
		assertEquals(expectedAlerts, collectedAlerts);

	}

}
