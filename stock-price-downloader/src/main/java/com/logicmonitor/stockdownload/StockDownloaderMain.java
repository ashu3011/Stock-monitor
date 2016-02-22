package com.logicmonitor.stockdownload;

/**
 * Class to run the Stand alone stock downloader program. 
 * @author Ashutosh
 *
 */
public class StockDownloaderMain {
	public static void main(String args[]){
		StockDownloader stockDownloader = new StockDownloader();
		stockDownloader.downloadStockInfo("INTC"); //Insert the stock symbol here to download
	}
}
