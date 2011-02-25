package com.freizl.stockwatcher.view
{
	import com.freizl.stockwatcher.presenter.StockWatcherPresenter;
	
	import mx.collections.ArrayCollection;
	
	public interface StockWatcherView extends View
	{
		function addRecord():void;
		function setRecords(x:ArrayCollection):void;
	}
}