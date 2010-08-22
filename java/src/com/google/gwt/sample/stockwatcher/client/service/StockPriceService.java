package com.google.gwt.sample.stockwatcher.client.service;

import java.util.List;

import com.google.gwt.sample.stockwatcher.client.shared.StockBuy;
import com.google.gwt.sample.stockwatcher.client.shared.StockPrice;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("stockPrices")
public interface StockPriceService extends RemoteService {

	StockPrice[] getPrices(List<String> symbols);

	StockBuy buyStock(StockBuy stockBuy);

	List<StockBuy> getStockBuyingHistory();
}