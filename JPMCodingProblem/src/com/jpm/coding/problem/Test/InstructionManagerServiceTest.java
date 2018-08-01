package com.jpm.coding.problem.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jpm.coding.problem.model.TradeInstruction;
import com.jpm.coding.problem.model.TradeType;
import com.jpm.coding.problem.services.ReportingService;

public class InstructionManagerServiceTest {
    private ReportingService reportingService;
    private List<TradeInstruction> tradingInstrunctions = new ArrayList<TradeInstruction>();

    @Before
    public void buildTestData() {
	TradeInstruction inst1 = new TradeInstruction("foo", 0.50F, Currency.getInstance("SGD"),
		LocalDate.parse("2016-01-01"), LocalDate.parse("2016-01-02"), 200, 100.25, TradeType.BUY);

	TradeInstruction inst2 = new TradeInstruction("bar", 0.22F, Currency.getInstance("AED"),
		LocalDate.parse("2016-01-05"), LocalDate.parse("2016-01-07"), 450, 150.5, TradeType.SELL);

	TradeInstruction inst3 = new TradeInstruction("foo", 0.30F, Currency.getInstance("INR"),
		LocalDate.parse("2016-01-01"), LocalDate.parse("2016-01-02"), 100, 130.25, TradeType.SELL);

	TradeInstruction inst4 = new TradeInstruction("bar", 0.42F, Currency.getInstance("INR"),
		LocalDate.parse("2016-01-05"), LocalDate.parse("2016-01-07"), 400, 135.5, TradeType.BUY);

	TradeInstruction inst5 = new TradeInstruction("foo", 0.20F, Currency.getInstance("SGD"),
		LocalDate.parse("2016-01-01"), LocalDate.parse("2016-01-02"), 140, 112.25, TradeType.BUY);
	tradingInstrunctions.add(inst1);
	tradingInstrunctions.add(inst2);
	tradingInstrunctions.add(inst3);
	tradingInstrunctions.add(inst4);
	tradingInstrunctions.add(inst5);

    }

    @Test
    public void GenerateTradeReportTest() {
	reportingService.generateTradeReport(tradingInstrunctions, TradeType.BUY, LocalDate.parse("2016-01-02"));
    }

}
