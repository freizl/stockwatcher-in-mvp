package com.google.gwt.sample.stockwatcher.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.sample.stockwatcher.client.shared.StockPrice;

public class BuyStockEvent extends GwtEvent<BuyStockEventHandler> {

	private StockPrice stockPrice;

	public BuyStockEvent(StockPrice stockPrice) {
		super();
		this.stockPrice = stockPrice;
	}

	public static Type<BuyStockEventHandler> TYPE = new Type<BuyStockEventHandler>();

	@Override
	protected void dispatch(BuyStockEventHandler handler) {
		handler.onBuyStock(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<BuyStockEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	public StockPrice getStockPrice() {
		return stockPrice;
	}

}
