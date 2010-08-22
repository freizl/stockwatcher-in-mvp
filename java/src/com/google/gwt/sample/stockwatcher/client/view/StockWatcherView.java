package com.google.gwt.sample.stockwatcher.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.sample.stockwatcher.client.presenter.StockWatcherPresenter;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 */
public class StockWatcherView extends Composite implements
		StockWatcherPresenter.Display {

	/**
	 * FlexTable is used when you have content added/removed dynamically - which
	 * means you won't be adding stuff via UiBinder templates (that doesn't even
	 * makes sense).
	 */
	@UiField
	FlexTable stocksFlexTable = new FlexTable();
	@UiField
	FlexTable buyingHistory = new FlexTable();
	@UiField
	TextBox newSymbolTextBox;
	@UiField
	Button addButton;
	private StockWatcherPresenter presenter;

	@UiTemplate("StockWatcherView.ui.xml")
	interface StockWatcherViewUiBinder extends
			UiBinder<Widget, StockWatcherView> {
	}

	private static StockWatcherViewUiBinder uiBinder = GWT
			.create(StockWatcherViewUiBinder.class);

	public StockWatcherView() {
		initWidget(uiBinder.createAndBindUi(this));
		buildStocksTable();
		buildBuyingHistoryTable();
		// this.newSymbolTextBox.setFocus(true);
	}

	private void buildBuyingHistoryTable() {
		buyingHistory.setText(0, 0, "Symbol");
		buyingHistory.setText(0, 1, "Price");
		buyingHistory.setText(0, 2, "Count");

		// stocksFlexTable.setCellPadding(6);
		buyingHistory.getRowFormatter().addStyleName(0, "watchListHeader");
		buyingHistory.addStyleName("watchList");
		buyingHistory.getCellFormatter().addStyleName(0, 1,
				"watchListNumericColumn");
		buyingHistory.getCellFormatter().addStyleName(0, 2,
				"watchListNumericColumn");

	}

	private void buildStocksTable() {
		// Create table for stock data.
		stocksFlexTable.setText(0, 0, "Symbol");
		stocksFlexTable.setText(0, 1, "Price");
		stocksFlexTable.setText(0, 2, "Change");
		stocksFlexTable.setText(0, 3, "Remove");
		stocksFlexTable.setText(0, 4, "Buy");

		// Add styles to elements in the stock list table.
		stocksFlexTable.setCellPadding(6);
		stocksFlexTable.getRowFormatter().addStyleName(0, "watchListHeader");
		stocksFlexTable.addStyleName("watchList");
		stocksFlexTable.getCellFormatter().addStyleName(0, 1,
				"watchListNumericColumn");
		stocksFlexTable.getCellFormatter().addStyleName(0, 2,
				"watchListNumericColumn");
		stocksFlexTable.getCellFormatter().addStyleName(0, 3,
				"watchListButtonColumn");
		stocksFlexTable.getCellFormatter().addStyleName(0, 4,
				"watchListButtonColumn");
	}

	@UiHandler("addButton")
	void onAddButtonClicked(ClickEvent event) {
		if (presenter != null) {
			presenter.onAddButtonClicked(newSymbolTextBox.getValue());
		}
	}

	@UiHandler("newSymbolTextBox")
	void onInputBoxEnterKeyPressed(KeyPressEvent event) {
		if (presenter != null && event.getCharCode() == KeyCodes.KEY_ENTER) {
			presenter.onInputBoxEnterKeyPressed(newSymbolTextBox.getValue());
		}
	}

	// ==============================================
	// ========================= Override
	// ==============================================
	
	@Override
	public void setPresenter(StockWatcherPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getRemoveButton(String symbol) {
		return (HasClickHandlers) stocksFlexTable.getWidget(
				findRowNumberOfSymbol(symbol), 3);
	}

	@Override
	public HasClickHandlers getBuyButton(String symbol) {
		return (HasClickHandlers) stocksFlexTable.getWidget(
				findRowNumberOfSymbol(symbol), 4);
	}

	@Override
	public void updatePrice(int row, double value) {
		String priceText = NumberFormat.getFormat("#,##0.00").format(value);
		stocksFlexTable.setText(row, 1, priceText);
	}

	@Override
	public String getPrice(String symbol) {
		return stocksFlexTable.getText(findRowNumberOfSymbol(symbol), 1);
	}

	@Override
	public void updateChangePercent(int row, double change, double changePercent) {
		NumberFormat changeFormat = NumberFormat
				.getFormat("+#,##0.00;-#,##0.00");
		String changeText = changeFormat.format(change);
		String changePercentText = changeFormat.format(changePercent);

		Label changeWidget = (Label) stocksFlexTable.getWidget(row, 2);
		changeWidget.setText(changeText + " (" + changePercentText + "%)");

		String changeStyleName = "noChange";
		if (changePercent < -0.1f) {
			changeStyleName = "negativeChange";
		} else if (changePercent > 0.1f) {
			changeStyleName = "positiveChange";
		}

		changeWidget.setStyleName(changeStyleName);
	}

	@Override
	public void addNewRow(String symbol) {
		int row = stocksFlexTable.getRowCount();
		stocksFlexTable.setText(row, 0, symbol);
		stocksFlexTable.setWidget(row, 2, new Label());
		stocksFlexTable.getCellFormatter().addStyleName(row, 1,
				"watchListNumericColumn");
		stocksFlexTable.getCellFormatter().addStyleName(row, 2,
				"watchListNumericColumn");
		stocksFlexTable.getCellFormatter().addStyleName(row, 3,
				"watchListRemoveColumn");

		Button removeStockButton = new Button("x");
		removeStockButton.addStyleDependentName("remove");
		stocksFlexTable.setWidget(row, 3, removeStockButton);

		Button buyButton = new Button("$");
		buyButton.addStyleDependentName("Buy");
		stocksFlexTable.setWidget(row, 4, buyButton);

		this.newSymbolTextBox.setText("");
	}

	@Override
	public void addNewBuyingHistory(String symbol, String price, String count) {
		buyingHistory.clear();
		int row = buyingHistory.getRowCount();

		buyingHistory.setText(row, 0, symbol);
		buyingHistory.setText(row, 1, price);
		buyingHistory.setText(row, 2, count);
	}

	@Override
	public void removeRow(String symbol) {
		stocksFlexTable.removeRow(findRowNumberOfSymbol(symbol));
	}

	private int findRowNumberOfSymbol(String symbol) {
		for (int row = 1; row < stocksFlexTable.getRowCount(); row++) {
			if (this.stocksFlexTable.getText(row, 0).equals(symbol)) {
				return row;
			}
		}
		return 0;
	}

}
