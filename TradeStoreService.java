package com.tradestore.v1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tradestore.v1.dao.TradeStoreDao;
import com.tradestore.v1.model.Trade;

@Service
public class TradeStoreService {

	@Autowired
	private TradeStoreDao tradeStoreDao;

	public void add(Trade trade) {
		tradeStoreDao.validateAndAdd(trade);
	}
	
	public void remove(int tradeId) {
		tradeStoreDao.validateAndRemove(tradeId);
	}

	public Trade getTrade(int tradeId) {
		return tradeStoreDao.showTrade(tradeId);
	}
	
	public List<Trade> getAllTrades() {
		return tradeStoreDao.showAllTrades();
	}
	
}
