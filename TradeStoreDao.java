package com.tradestore.v1.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.tradestore.v1.model.Trade;
import com.tradestore.v1.validate.TradeValidator;

@Repository
public class TradeStoreDao {

	@Autowired
	private Trade trade;

	private Map<Integer, Trade> existingTrades = new HashMap<>();

	public boolean validateAndAdd(Trade trade) {
		if (TradeValidator.isvalid(existingTrades, trade)) {
			saveTrade(trade);
			return true;
		}
		return false;
	}

	public boolean validateAndRemove(int tradeId) {
		if (existingTrades.containsKey(tradeId)) {
			existingTrades.remove(tradeId);
			return true;
		}
		return false;
	}

	public Trade showTrade(int tradeId) {
		if (existingTrades.containsKey(tradeId)) {
			trade = existingTrades.get(tradeId);
		}
		return trade;
	}

	public List<Trade> showAllTrades() {
		ArrayList<Trade> listOfTrades = (ArrayList<Trade>) existingTrades.values().stream()
				.collect(Collectors.toList());
		return listOfTrades;
	}

	private void saveTrade(Trade trade) {
		existingTrades.put(trade.getTradeId(), trade);
	}

}
