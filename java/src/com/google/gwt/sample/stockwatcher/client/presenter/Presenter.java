package com.google.gwt.sample.stockwatcher.client.presenter;

import com.google.gwt.sample.stockwatcher.client.view.Display;

/**
 * Presenter Interface.
 */
public interface Presenter<D extends Display> {

	void bind();

	D getDisplay();
}
