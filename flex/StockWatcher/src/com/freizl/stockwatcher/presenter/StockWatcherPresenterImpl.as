package com.freizl.stockwatcher.presenter
{
	import com.freizl.stockwatcher.view.StockWatcherView;
	
	import mx.collections.ArrayCollection;
	import mx.core.IVisualElementContainer;
	
	public class StockWatcherPresenterImpl implements Presenter, StockWatcherPresenter
	{
		private var _view:StockWatcherView
		
		public function StockWatcherPresenterImpl(view:StockWatcherView)
		{
			this._view = view;
			this._view.setPresenter(this);
		}
		
		public function go(widget:IVisualElementContainer):void
		{
			widget.removeAllElements();
			widget.addElement(this._view.asWidget());
			fetchInitData();
		}
		
		public function onAddButtonClicked():void
		{
			/* normal process is probably invoke service to do get some info. then invoke a view function */
			this._view.addRecord();
		}
		
		private function fetchInitData():void
		{
			var x:Array = [
				{symbol:"11", price:123, change:0.8},
				{symbol:"22", price:53, change:0.3}];
			this._view.setRecords(new ArrayCollection(x));
		}
		
		// TODO: what's diff between parameter useCapture true|false
		// this.view.getAddButton().addEventListener(MouseEvent.CLICK, addButtonHandler, false, 0);
	}
}