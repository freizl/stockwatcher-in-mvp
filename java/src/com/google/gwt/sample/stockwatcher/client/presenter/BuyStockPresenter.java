package com.google.gwt.sample.stockwatcher.client.presenter;


public interface BuyStockPresenter extends Presenter<BuyStockPresenter.Display> {

	public interface Display extends
			com.google.gwt.sample.stockwatcher.client.view.Display {

		void displaySymbol(String symbol);

		void displayPrice(String price);

		void setPresenter(BuyStockPresenter presenter);

	}

	void onSaveButtonClicked(Long amount);

	void onCancelButtonClicked();

}
