package com.logicmonitor.stockdownload;

public class StockDownloaderMain {
	public static void main(String args[]){
		StockDownloader stockDownloader = new StockDownloader();
		stockDownloader.downloadStockInfo("INTC");
	}
}
