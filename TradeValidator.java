package com.tradestore.v1.validate;

import java.time.LocalDate;
import java.util.Map;

import com.tradestore.v1.model.Trade;


public class TradeValidator {

	public static boolean isvalid(Map<Integer, Trade> existingTrades, Trade trade) {
		if (null != trade.getMaturityDate() && trade.getMaturityDate().isAfter(LocalDate.now())) {
			if (existingTrades.containsKey(trade.getTradeId())) {
				Trade existingTrade = existingTrades.get(trade.getTradeId());
				if (trade.getVersion() == existingTrade.getVersion()) {
					return true;
				} else if (existingTrade.getVersion() > trade.getVersion()) {
					return false;
				}
			} else {
				return true;
			}
		}
		return false;

	}

	public static void checkIfExpired(){
		
	}
}
