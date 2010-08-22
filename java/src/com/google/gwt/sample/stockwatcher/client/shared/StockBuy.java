package com.google.gwt.sample.stockwatcher.client.shared;

import java.io.Serializable;

public class StockBuy implements Serializable {

	/** */
	private static final long serialVersionUID = -5423954446375892903L;
	private String symbol;
	private Double price;
	private Long count;

	public StockBuy() {
	}

	public StockBuy(String symbol, Double price, Long count) {
		this.symbol = symbol;
		this.price = price;
		this.count = count;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public double getPrice() {
		return this.price;
	}

	public long getCount() {
		return count;
	}

}
