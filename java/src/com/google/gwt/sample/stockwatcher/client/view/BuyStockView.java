package com.google.gwt.sample.stockwatcher.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.sample.stockwatcher.client.presenter.BuyStockPresenter;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class BuyStockView extends Composite implements
		BuyStockPresenter.Display {

	@UiTemplate("BuyStockView.ui.xml")
	interface BuyStockViewUiBinder extends
			UiBinder<DecoratorPanel, BuyStockView> {
	}

	private static BuyStockViewUiBinder uiBinder = GWT
			.create(BuyStockViewUiBinder.class);

	@UiField
	TextBox symbol;
	@UiField
	TextBox price;
	@UiField
	TextBox amount;
	@UiField
	Button saveButton;
	@UiField
	Button cancelButton;

	private BuyStockPresenter presenter;

	public BuyStockView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("saveButton")
	void onSaveButtonClicked(ClickEvent event) {
		if (presenter != null) {
			presenter.onSaveButtonClicked(Long.valueOf(amount.getValue()));
		}
	}

	@UiHandler("cancelButton")
	void onCancelButtonClicked(ClickEvent event) {
		if (presenter != null) {
			presenter.onCancelButtonClicked();
		}
	}

	@Override
	public void setPresenter(BuyStockPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void displaySymbol(String value) {
		symbol.setText(value);
	}

	@Override
	public void displayPrice(String price) {
		this.price.setText(price);
	}

}
