package com.google.gwt.sample.stockwatcher.client.service;

import java.util.List;

import com.google.gwt.sample.stockwatcher.client.shared.StockBuy;
import com.google.gwt.sample.stockwatcher.client.shared.StockPrice;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface StockPriceServiceAsync {

	void getPrices(List<String> symbol, AsyncCallback<StockPrice[]> callback);

	void buyStock(StockBuy stockBuy, AsyncCallback<StockBuy> callback);

	void getStockBuyingHistory(AsyncCallback<List<StockBuy>> callback);
}
