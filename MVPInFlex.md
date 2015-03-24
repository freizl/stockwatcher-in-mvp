# Introduction #
MVP turns out to be less couple as well as more flexible that MVC in my opinion.

MVP now becomes part of GWT.

More information about MVP and MVC could be found via Google.

# Discussion #
The reason why MVP doable in Flex is because that mxml could be said as "class in xml format".

Therefore, mxml could be initialized in Action Script and its functions and UI widgets are accessible as a normal Action Script class.

# Details #
### Implement interface of mxml component ###
```
<?xml version="1.0" encoding="utf-8"?>
<s:Group xmlns:fx="http://ns.adobe.com/mxml/2009" 
		 xmlns:s="library://ns.adobe.com/flex/spark" implements="com.freizl.stockwatcher.view.StockWatcherView"
...

```
And the interface is just a normal Action Script interface.

### Create instance of mxml component ###
```
var view:StockWatcherView = new StockWatcherViewImpl();
var presenter:StockWatcherPresenter = new StockWatcherPresenterImpl(view);
presenter.bind();
```
Also, Presenter interface and Impl are normal Action Scripts interface and class respectively.

# Sample Flex Project #
[Sample project](http://code.google.com/p/stockwatcher-in-mvp/source/browse/#hg%2Fflex%2FStockWatcher)

# Concerns #
  1. How "Event Bus" being implemented?
  1. Any others?

# TODOs #
cool samples
  * [mvp for flex](http://www.mxml.it/index.php/2008/09/09/introduction-to-mvp-for-flex/)
  * [mvp in Swiz Framework](http://www.riaspace.com/2009/09/my-approach-to-mvp-pattern-with-swiz-framework/)