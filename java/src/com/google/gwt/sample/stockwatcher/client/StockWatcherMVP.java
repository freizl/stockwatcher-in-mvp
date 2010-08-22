package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.sample.stockwatcher.client.event.BuyStockEvent;
import com.google.gwt.sample.stockwatcher.client.event.BuyStockEventHandler;
import com.google.gwt.sample.stockwatcher.client.event.ShowStockWaterEvent;
import com.google.gwt.sample.stockwatcher.client.event.ShowStockWaterEventHandler;
import com.google.gwt.sample.stockwatcher.client.presenter.BuyStockPresenter;
import com.google.gwt.sample.stockwatcher.client.presenter.BuyStockPresenterImpl;
import com.google.gwt.sample.stockwatcher.client.presenter.Presenter;
import com.google.gwt.sample.stockwatcher.client.presenter.StockWatcherPresenter;
import com.google.gwt.sample.stockwatcher.client.presenter.StockWatcherPresenterImpl;
import com.google.gwt.sample.stockwatcher.client.service.StockPriceService;
import com.google.gwt.sample.stockwatcher.client.service.StockPriceServiceAsync;
import com.google.gwt.sample.stockwatcher.client.view.BuyStockView;
import com.google.gwt.sample.stockwatcher.client.view.Display;
import com.google.gwt.sample.stockwatcher.client.view.StockWatcherView;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;

public class StockWatcherMVP implements EntryPoint {

	private StockPriceServiceAsync stockPriceSvc = GWT
			.create(StockPriceService.class);

	private static HandlerManager eventBus = new HandlerManager(null);

	/**
	 * Entry point method.
	 */
	public void onModuleLoad() {
		bind();
		openStockWater();
	}

	private void openStockWater() {
		StockWatcherView view = new StockWatcherView();
		StockWatcherPresenter presenter = new StockWatcherPresenterImpl(stockPriceSvc, eventBus, view);
		presenter.bind();
		view.setPresenter(presenter);
		displayView(presenter, RootPanel.get("stockList"));
	}

	private void displayView(Presenter<? extends Display> presenter, HasWidgets container) {
		container.clear();
		container.add(presenter.getDisplay().asWidget());
	}

	private void bind() {
		eventBus.addHandler(BuyStockEvent.TYPE, new BuyStockEventHandler() {
			@Override
			public void onBuyStock(BuyStockEvent event) {
				BuyStockView view = new BuyStockView();
				BuyStockPresenter presenter = new BuyStockPresenterImpl(
						stockPriceSvc, eventBus, view, event.getStockPrice());
				presenter.bind();
				view.setPresenter(presenter);
				displayView(presenter, RootPanel.get("stockList"));
			}
		});

		eventBus.addHandler(ShowStockWaterEvent.TYPE,
				new ShowStockWaterEventHandler() {

					@Override
					public void onShowStockWater(ShowStockWaterEvent event) {
						openStockWater();
					}
				});
	}

}
