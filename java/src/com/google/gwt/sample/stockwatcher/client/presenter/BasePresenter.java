package com.google.gwt.sample.stockwatcher.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.sample.stockwatcher.client.view.Display;

/**
 * 
 */
public abstract class BasePresenter<D extends Display> implements Presenter<D> {

	protected final HandlerManager eventBus;
	protected final D display;

	public BasePresenter(HandlerManager eventBus, D display) {
		this.eventBus = eventBus;
		this.display = display;
	}

	@Override
	public void bind() {
		onBind();
		postBind();
	}

	/**
	 * Do actually event binding.
	 */
	protected abstract void onBind();

	/**
	 * Override in case data init after bind.
	 */
	protected void postBind() {
	}

	public D getDisplay() {
		return this.display;
	}

}
