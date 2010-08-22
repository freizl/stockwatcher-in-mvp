package com.google.gwt.sample.stockwatcher.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface BuyStockEventHandler extends EventHandler {
  void onBuyStock(BuyStockEvent event);
}
