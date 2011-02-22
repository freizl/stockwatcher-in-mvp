package com.freizl.stockwatcher.presenter
{
	import com.freizl.stockwatcher.view.StockWatcherView;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	
	public class StockWatcherPresenterImpl implements StockWatcherPresenter
	{
		private var view:StockWatcherView
		
		public function StockWatcherPresenterImpl(view:StockWatcherView)
		{
			this.view = view;
		}
		
		public function bind():void 
		{
			// TODO: what's diff between parameter useCapture true|false
			this.view.getAddButton().addEventListener(MouseEvent.CLICK, addHandler, false, 0);
			this.view.setRecords(searchData());
		}
		
		private function addHandler(event:Event):void
		{
			//			Alert.show('welcome to Flex World:');
			this.view.addRecord();
		}
		
		private function searchData():ArrayCollection
		{
			var x:Array = [
				{symbol:"11", price:123, change:0.8},
				{symbol:"22", price:53, change:0.3}];
			return new ArrayCollection(x);
		}
		
		public function asComponent():StockWatcherView 
		{
			return this.view;
		}
	}
}