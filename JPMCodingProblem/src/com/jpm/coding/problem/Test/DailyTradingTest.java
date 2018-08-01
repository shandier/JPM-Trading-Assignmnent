package com.jpm.coding.problem.Test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jpm.coding.problem.helper.InstructionUtil;
import com.jpm.coding.problem.helper.TradeUtil;
import com.jpm.coding.problem.model.TradeInstruction;
import com.jpm.coding.problem.model.TradeType;
import com.jpm.coding.problem.services.InstructionManager;
import com.jpm.coding.problem.services.InstructionManagerImpl;

public class DailyTradingTest {
    private InstructionManager instructionManager = new InstructionManagerImpl();
    private List<TradeInstruction> tradingInstrunctions = new ArrayList<TradeInstruction>();

    @Before
    public void buildTestData() {
	TradeInstruction inst1 = new TradeInstruction("foo", 0.50F, Currency.getInstance("SGD"),
		LocalDate.parse("2018-08-01"), LocalDate.parse("2018-08-04"), 200, 100.25, TradeType.BUY);

	TradeInstruction inst2 = new TradeInstruction("bar", 0.22F, Currency.getInstance("AED"),
		LocalDate.parse("2018-02-02"), LocalDate.parse("2018-08-03"), 450, 150.5, TradeType.SELL);

	TradeInstruction inst3 = new TradeInstruction("foo", 0.30F, Currency.getInstance("INR"),
		LocalDate.parse("2018-08-01"), LocalDate.parse("2018-08-04"), 100, 130.25, TradeType.SELL);

	TradeInstruction inst4 = new TradeInstruction("bar", 0.42F, Currency.getInstance("INR"),
		LocalDate.parse("2016-01-05"), LocalDate.parse("2016-01-07"), 400, 135.5, TradeType.BUY);

	TradeInstruction inst5 = new TradeInstruction("foo", 0.20F, Currency.getInstance("SGD"),
		LocalDate.parse("2016-01-01"), LocalDate.parse("2018-08-04"), 140, 112.25, TradeType.BUY);
	tradingInstrunctions.add(inst1);
	tradingInstrunctions.add(inst2);
	tradingInstrunctions.add(inst3);
	tradingInstrunctions.add(inst4);
	tradingInstrunctions.add(inst5);

    }

    @Test
    public void testsettleInstructionsWithNullAndEmptyList() {
	assertEquals(null, instructionManager.settleInstructions(null));
	assertEquals(true, instructionManager.settleInstructions(new ArrayList<TradeInstruction>()).isEmpty());
    }

    @Test
    public void testGetWorkingSettlementDate() {
	// Currency with weekend as Friday and Saturday
	Currency currency = Currency.getInstance("AED");
	// Currency with weekend as Saturday and Sunday
	Currency regularWeekendCurrency = Currency.getInstance("SGD");
	LocalDate weekDayFriday = LocalDate.parse("2018-08-03");
	LocalDate weekDaySunday = LocalDate.parse("2018-08-05");

	LocalDate nextWorkingDate = TradeUtil.getWorkingSettlementDate(currency, weekDayFriday);
	assertEquals(nextWorkingDate, weekDayFriday.plusDays(2));

	LocalDate nextWorkingDate2 = TradeUtil.getWorkingSettlementDate(regularWeekendCurrency, weekDayFriday);
	assertEquals(nextWorkingDate2, weekDayFriday);

	LocalDate nextWorkingDate3 = TradeUtil.getWorkingSettlementDate(regularWeekendCurrency, weekDaySunday);
	assertEquals(nextWorkingDate3, weekDaySunday.plusDays(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeGetWorkingSettlementDate() {
	LocalDate nextWorkingDate = TradeUtil.getWorkingSettlementDate(null, null);
	assertEquals(nextWorkingDate, LocalDate.now());

	LocalDate nextWorkingDate1 = TradeUtil.getWorkingSettlementDate(Currency.getInstance("fgd"),
		LocalDate.parse("2018-08-05"));
	assertEquals(nextWorkingDate1, LocalDate.now());
    }

    @Test
    public void TestFilterInstructionsbySettlementDateAndTradeType() {
	List<TradeInstruction> fillteredInstructionForBuy = InstructionUtil
		.filterInstructionsbySettlementDateAndTradeType(tradingInstrunctions, LocalDate.parse("2018-08-04"),
			TradeType.BUY);
	List<TradeInstruction> fillteredInstructionsForSell = InstructionUtil
		.filterInstructionsbySettlementDateAndTradeType(tradingInstrunctions, LocalDate.parse("2018-08-04"),
			TradeType.SELL);
	assertEquals(fillteredInstructionForBuy.size(), 2);
	assertEquals(fillteredInstructionsForSell.size(), 1);
    }
}
