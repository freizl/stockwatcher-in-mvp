package com.google.gwt.sample.stockwatcher.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.sample.stockwatcher.client.event.BuyStockEvent;
import com.google.gwt.sample.stockwatcher.client.service.StockPriceServiceAsync;
import com.google.gwt.sample.stockwatcher.client.shared.StockBuy;
import com.google.gwt.sample.stockwatcher.client.shared.StockPrice;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class StockWatcherPresenterImpl extends
		BasePresenter<StockWatcherPresenter.Display> implements
		StockWatcherPresenter {

	private List<String> symbolList = new ArrayList<String>();
	private StockPriceServiceAsync rpcService;

	public StockWatcherPresenterImpl(StockPriceServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
		super(eventBus, view);
		this.rpcService = rpcService;
	}

	@Override
	protected void onBind() {

	}

	private void addStock(final String symbol) {

		if (!symbol.matches("^[0-9a-zA-Z\\.]{1,10}$")) {
			Window.alert("'" + symbol + "' is not a valid symbol.");
			return;
		}

		if (symbolList.contains(symbol)) {
			Window.alert("Unique constraint of symbol.");
			return;
		}

		symbolList.add(symbol);

		display.addNewRow(symbol);

		refreshWatchList();

		display.getRemoveButton(symbol).addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				display.removeRow(symbol);
				symbolList.remove(symbol);
			}
		});

		display.getBuyButton(symbol).addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final double price = Double.parseDouble(display
						.getPrice(symbol));
				StockPrice stockPrice = new StockPrice(symbol, price);
				eventBus.fireEvent(new BuyStockEvent(stockPrice));
			}
		});
	}

	private void refreshWatchList() {
		this.rpcService.getPrices(this.symbolList,
				new AsyncCallback<StockPrice[]>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("refreshWatchList error");
					}

					@Override
					public void onSuccess(StockPrice[] result) {
						for (int i = 0; i < result.length; i++) {
							int row = i + 1;
							display.updatePrice(row, result[i].getPrice());
							display.updateChangePercent(row, result[i]
									.getChange(), result[i].getChangePercent());
						}
					}
				});

	}

	/**
	 * TODO: duplicated row.
	 */
	private void refreshBuyingHistory() {
		this.rpcService
				.getStockBuyingHistory(new AsyncCallback<List<StockBuy>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("refreshBuyingHistory error"
								+ caught.getMessage());
					}

					@Override
					public void onSuccess(List<StockBuy> result) {

						for (StockBuy stockBuy : result) {
							display.addNewBuyingHistory(stockBuy.getSymbol(),
									String.valueOf(stockBuy.getPrice()), String
											.valueOf(stockBuy.getCount()));
						}

					}
				});

	}

	@Override
	protected void postBind() {
		refreshWatchList();
		refreshBuyingHistory();
	}

	@Override
	public void onAddButtonClicked(String value) {
		addStock(value);
	}

	@Override
	public void onInputBoxEnterKeyPressed(String value) {
		addStock(value);
	}

	// private void scheduleTimeRefresh() {
	// // Setup timer to refresh list automatically.
	// Timer refreshTimer = new Timer() {
	// @Override
	// public void run() {
	// refreshWatchList();
	// }
	// };
	// refreshTimer.scheduleRepeating(REFRESH_INTERVAL);
	// }
	// private static final int REFRESH_INTERVAL = 2000;

}
