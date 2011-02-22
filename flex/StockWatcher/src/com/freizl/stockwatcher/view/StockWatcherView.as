package com.freizl.stockwatcher.view
{
	import mx.collections.ArrayCollection;
	import mx.core.IButton;
	import mx.core.IVisualElement;

	public interface StockWatcherView extends IVisualElement
	{
		function getAddButton():IButton;
		function addRecord():void;
		function setRecords(x:ArrayCollection):void;
	}
}