package com.google.gwt.sample.stockwatcher.client.presenter;

import com.google.gwt.event.dom.client.HasClickHandlers;

public interface StockWatcherPresenter extends
		Presenter<StockWatcherPresenter.Display> {

	public interface Display extends
			com.google.gwt.sample.stockwatcher.client.view.Display {

		/**
		 * Get the Remove button for <code>symbol<code>.
		 */
		HasClickHandlers getRemoveButton(String symbol);

		HasClickHandlers getBuyButton(String symbol);

		/**
		 * Add new Row that has the <code>symbol<code> into Table
		 */
		void addNewRow(String symbol);

		/**
		 * Remove the Row that has the <code>symbol<code> in the Table
		 */
		void removeRow(String symbol);

		/**
		 * Get price of current row.
		 */
		String getPrice(String symbol);

		/**
		 * TODO: Add a single API to update Price and ChangePercentage.
		 */

		void updateChangePercent(int row, double change, double changePercent);

		void updatePrice(int row, double value);

		void addNewBuyingHistory(String symbol, String price, String count);

		void setPresenter(StockWatcherPresenter presenter);

	}

//	void refreshWatchList();

//	void refreshBuyingHistory();

	void onAddButtonClicked(String value);

	void onInputBoxEnterKeyPressed(String value);

}
