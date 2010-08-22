package com.google.gwt.sample.stockwatcher.client.presenter;

import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.getCurrentArguments;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.easymock.classextension.EasyMock.createNiceMock;
import static org.junit.Assert.assertTrue;

import org.easymock.IAnswer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.sample.stockwatcher.client.event.ShowStockWaterEvent;
import com.google.gwt.sample.stockwatcher.client.event.ShowStockWaterEventHandler;
import com.google.gwt.sample.stockwatcher.client.service.StockPriceServiceAsync;
import com.google.gwt.sample.stockwatcher.client.shared.StockBuy;
import com.google.gwt.sample.stockwatcher.client.shared.StockPrice;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class BuyStockPresenterImplTest {

	private static final double PRICE = 28.67;
	private static final String SYMBOL = "BuyStockPresenterImplTest";
	private static final Long AMOUNT = 200l;

	private BuyStockPresenter target;
	private StockPriceServiceAsync rpcService;
	private BuyStockPresenter.Display display;
	private StockPrice stockPrice;
	private HandlerManager eventBus;

	private boolean isShowStockWatchEventInvoked = false;

	@Before
	public void setUp() throws Exception {
		rpcService = createNiceMock(StockPriceServiceAsync.class);
		display = createNiceMock(BuyStockPresenter.Display.class);
		eventBus = new HandlerManager(null);
		stockPrice = getStockPrice();
	}

	@After
	public void tearDown() throws Exception {
		rpcService = null;
		display = null;
		stockPrice = null;
		isShowStockWatchEventInvoked = false;
	}

	private StockPrice getStockPrice() {
		StockPrice sp = new StockPrice();
		sp.setSymbol(SYMBOL);
		sp.setPrice(PRICE);
		return sp;
	}

	@Test
	public void testBuyStockPresenterImpl() {
		expectDisplayStockPrice();

		replay(display);
		target = new BuyStockPresenterImpl(rpcService, eventBus, display,
				stockPrice);
		verify(display);

	}

	private void expectDisplayStockPrice() {
		display.displaySymbol(SYMBOL);
		expectLastCall().once();
		display.displayPrice(String.valueOf(PRICE));
		expectLastCall().once();
	}

	@Test
	public void testSave() {

		expectDisplayStockPrice();
		expectRpcServiceBuyStock();
		replay(display, rpcService);

		addShowStockWaterEventHandler();

		target = new BuyStockPresenterImpl(rpcService, eventBus, display,
				stockPrice);
		target.onSaveButtonClicked(AMOUNT);

		assertTrue("ShowStockWaterEvent is not being invoked.",
				isShowStockWatchEventInvoked);

		verify(display, rpcService);

	}

	@SuppressWarnings("unchecked")
	private void expectRpcServiceBuyStock() {
		final StockBuy result = new StockBuy();
		// TODO: eq StockBuy
		rpcService.buyStock(isA(StockBuy.class), isA(AsyncCallback.class));
		expectLastCall().andAnswer(new IAnswer<Object>() {
			@Override
			public Object answer() throws Throwable {
				final Object[] args = getCurrentArguments();
				AsyncCallback callback = (AsyncCallback) args[args.length - 1];
				callback.onSuccess(result);
				return null;
			}
		});
	}

	/** TODO: */
	public void testSaveFailure() {
	}

	// private void expectRpcServiceBuyStockFailure() {
	// rpcService.buyStock(isA(StockBuy.class), isA(AsyncCallback.class));
	// expectLastCall().andAnswer(new IAnswer<Object>() {
	// @Override
	// public Object answer() throws Throwable {
	// final Object[] args = getCurrentArguments();
	// AsyncCallback callback = (AsyncCallback) args[args.length - 1];
	// callback.onFailure(null);
	// return null;
	// }
	// });
	// }

	@Test
	public void testCancel() {

		expectDisplayStockPrice();
		replay(display);

		addShowStockWaterEventHandler();

		target = new BuyStockPresenterImpl(rpcService, eventBus, display,
				stockPrice);
		target.onCancelButtonClicked();

		assertTrue("ShowStockWaterEvent is not being invoked.",
				isShowStockWatchEventInvoked);

		verify(display);

	}

	private void addShowStockWaterEventHandler() {
		eventBus.addHandler(ShowStockWaterEvent.TYPE,
				new ShowStockWaterEventHandler() {

					@Override
					public void onShowStockWater(ShowStockWaterEvent event) {
						isShowStockWatchEventInvoked = true;
					}
				});
	}

}
