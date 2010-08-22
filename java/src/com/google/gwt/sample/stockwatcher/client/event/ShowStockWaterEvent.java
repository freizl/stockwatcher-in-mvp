package com.google.gwt.sample.stockwatcher.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ShowStockWaterEvent extends GwtEvent<ShowStockWaterEventHandler> {

	public ShowStockWaterEvent() {
		super();
	}

	public static Type<ShowStockWaterEventHandler> TYPE = new Type<ShowStockWaterEventHandler>();

	@Override
	protected void dispatch(ShowStockWaterEventHandler handler) {
		handler.onShowStockWater(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ShowStockWaterEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

}
