package com.freizl.stockwatcher.presenter
{
	import com.freizl.stockwatcher.view.StockWatcherView;
	
	public interface StockWatcherPresenter extends Presenter
	{
		function onAddButtonClicked():void;
		function set view(value:StockWatcherView):void;
	}
}