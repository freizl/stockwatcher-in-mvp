package com.google.gwt.sample.stockwatcher.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.sample.stockwatcher.client.event.ShowStockWaterEvent;
import com.google.gwt.sample.stockwatcher.client.service.StockPriceServiceAsync;
import com.google.gwt.sample.stockwatcher.client.shared.StockBuy;
import com.google.gwt.sample.stockwatcher.client.shared.StockPrice;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class BuyStockPresenterImpl extends
		BasePresenter<BuyStockPresenter.Display> implements BuyStockPresenter {

	private StockPriceServiceAsync rpcService;

	private StockPrice stockPrice;

	public BuyStockPresenterImpl(StockPriceServiceAsync rpcService,
			HandlerManager eventBus, Display view, StockPrice stockPrice) {
		super(eventBus, view);
		this.rpcService = rpcService;
		this.stockPrice = stockPrice;
		displayValue(stockPrice);
	}

	private void displayValue(StockPrice stockPrice) {
		display.displaySymbol(stockPrice.getSymbol());
		display.displayPrice(String.valueOf(stockPrice.getPrice()));
	}

	@Override
	public void onCancelButtonClicked() {
		eventBus.fireEvent(new ShowStockWaterEvent());
	}

	@Override
	public void onSaveButtonClicked(Long amount) {
		StockBuy stockBuy = new StockBuy(this.stockPrice.getSymbol(),
				this.stockPrice.getPrice(), amount);

		rpcService.buyStock(stockBuy, new AsyncCallback<StockBuy>() {

			@Override
			public void onSuccess(StockBuy result) {
				eventBus.fireEvent(new ShowStockWaterEvent());
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

	@Override
	protected void onBind() {

	}

}
