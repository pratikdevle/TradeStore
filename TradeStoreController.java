package com.tradestore.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tradestore.v1.model.Trade;
import com.tradestore.v1.service.TradeStoreService;

@RestController
public class TradeStoreController {

	@Autowired
	private TradeStoreService tradeStoreService;

	MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

	@RequestMapping(value = "/addTrade", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addTrade(@RequestBody Trade trade) {
		System.out.println("Entered TradeStore Service");
		tradeStoreService.add(trade);
		headers.add("Content-Type", "application/xml");
		String response = "<Response>\r\n" + "<Msg>Trade Added Successfully</Msg>\r\n" + "</Response>";
		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/removeTrade", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> removeTrade(@RequestBody int tradeId) {

		tradeStoreService.remove(tradeId);
		headers.add("Content-Type", "application/xml");
		String response = "<Response>\r\n" + "<Msg>Trade Deleted Successfully</Msg>\r\n" + "</Response>";
		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/showTrade", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Trade showTrade(@RequestBody int tradeId) {
		return tradeStoreService.getTrade(tradeId);
	}

	@RequestMapping(value = "/showAllTrades", method = RequestMethod.GET)
	public List<Trade> showAllTrade() {
		return tradeStoreService.getAllTrades();
	}
}
