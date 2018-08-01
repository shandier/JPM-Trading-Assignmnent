package com.jpm.coding.problem.endPoint;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.jpm.coding.problem.model.TradeInstruction;
import com.jpm.coding.problem.model.TradeType;
import com.jpm.coding.problem.services.FileInstructionReader;
import com.jpm.coding.problem.services.ReportingService;
import com.jpm.coding.problem.services.ReportingServiceImpl;
import com.jpm.coding.problem.services.TradeInstructionReader;

public class TradeTest {
    public static void main(String[] args) {
	TradeInstructionReader instructionReader = new FileInstructionReader();
	ReportingService reportingService = new ReportingServiceImpl();
	String source = "";
	TradeType tradeType = TradeType.BUY;
	LocalDate date = LocalDate.now();
	if (args.length >= 1) {
	    source = args[0];
	}
	if (args.length >= 2) {
	    tradeType = args[1].equalsIgnoreCase("b") ? TradeType.BUY
		    : args[1].equalsIgnoreCase("s") ? TradeType.SELL : TradeType.BUY;
	}
	if (args.length >= 3) {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
	    try {
	    date = LocalDate.parse(args[2],dtf);
	    }catch (DateTimeParseException e) {
		date = LocalDate.now();
	    }
	}
	// Read instructions from file
	List<TradeInstruction> tradeInstructions = instructionReader.getTradeInstruction(source);
	// Generate trade type report for given date
	reportingService.generateTradeReport(tradeInstructions, tradeType, date);
	// Generate trade type report for given date
	reportingService.generateDashboard(tradeInstructions, tradeType, date);
    }

}
