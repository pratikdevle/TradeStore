package com.tradestore.v1.timer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tradestore.v1.dao.TradeStoreDao;
import com.tradestore.v1.model.Trade;

@Component
public class TradeStoreTimer {

	@Autowired
	private TradeStoreDao tradeStore;

	// Timer to run everyday at midnight
	@Scheduled(cron = "0 0 0 * * ?")
	public void reportCurrentTime() {
		List<Trade> existingTrades = tradeStore.showAllTrades();
		for (Trade trade : existingTrades) {
			if (trade.getMaturityDate().isBefore(LocalDate.now()) && trade.getExpired().equals("N")) {
				trade.setExpired("Y");
			}
		}
	}
}