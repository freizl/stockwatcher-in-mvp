package com.google.gwt.sample.stockwatcher.server;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.google.gwt.sample.stockwatcher.client.service.StockPriceService;
import com.google.gwt.sample.stockwatcher.client.shared.StockBuy;
import com.google.gwt.sample.stockwatcher.client.shared.StockPrice;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class StockPriceServiceImpl extends RemoteServiceServlet implements
		StockPriceService {

	/** */
	private static final long serialVersionUID = 155669477848585224L;
	private static final double MAX_PRICE = 100.0; // $100.00
	private static final double MAX_PRICE_CHANGE = 0.02; // +/- 2%

	private static List<StockBuy> buyingHistory = new LinkedList<StockBuy>();

	@Override
	public StockPrice[] getPrices(List<String> symbols) {
		Random rnd = new Random();

		StockPrice[] prices = new StockPrice[symbols.size()];
		for (int i = 0; i < symbols.size(); i++) {
			double price = rnd.nextDouble() * MAX_PRICE;
			double change = price * MAX_PRICE_CHANGE
					* (rnd.nextDouble() * 2f - 1f);

			prices[i] = new StockPrice(symbols.get(i), price, change);
		}

		return prices;

	}

	// =====================================================
	
	@Override
	public StockBuy buyStock(StockBuy stockBuy) {
		buyingHistory.add(stockBuy);
		return stockBuy;
	}

	@Override
	public List<StockBuy> getStockBuyingHistory() {
		return buyingHistory;
	}

}
