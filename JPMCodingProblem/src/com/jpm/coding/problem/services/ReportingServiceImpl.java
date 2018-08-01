package com.jpm.coding.problem.services;

import java.time.LocalDate;
import java.util.List;

import com.jpm.coding.problem.helper.InstructionUtil;
import com.jpm.coding.problem.model.TradeInstruction;
import com.jpm.coding.problem.model.TradeType;

public class ReportingServiceImpl implements ReportingService {

    private InstructionManagerService instructionManager = new InstructionManagerServiceImpl();

    @Override
    public double generateTradeReport(List<TradeInstruction> instructions, TradeType tradeType,
	    LocalDate settlemetDate) {
	double totalTrade = 0;
	if (instructions == null || instructions.isEmpty()) {
	    System.out.println("No data found");
	} else {
	    instructions = instructionManager.settleInstructions(instructions);
	    List<TradeInstruction> filteredInstrunctions = InstructionUtil
		    .filterInstructionsbySettlementDateAndTradeType(instructions, settlemetDate, tradeType);
	    totalTrade = filteredInstrunctions.stream().map(instruction -> instruction.getUSD()).reduce(0.0,
		    Double::sum);
	    String reportFor = TradeType.BUY.equals(tradeType) ? InstructionUtil.OUTGOING : InstructionUtil.INCOMING;
	    String reportText = String.format("Total %s on %s is %f ", reportFor, settlemetDate.toString(), totalTrade);
	    System.out.println(reportText + "\n");
	}
	return totalTrade;
    }

    @Override
    public void generateDashboard(List<TradeInstruction> instructions, TradeType tradeType, LocalDate settlemetDate) {
	if (instructions == null || instructions.isEmpty()) {
	    System.out.println("No data found");
	} else {
	    instructions = instructionManager.settleInstructions(instructions);
	    String reportFor = TradeType.BUY.equals(tradeType) ? InstructionUtil.OUTGOING : InstructionUtil.INCOMING;
	    instructions = InstructionUtil.filterInstructionsbySettlementDateAndTradeType(instructions, settlemetDate,
		    tradeType);
	    //sort in descending order
	    instructions.sort((inst1,inst2)->inst2.getUSD().compareTo(inst1.getUSD()));
	    System.out.println("Top entites for " + reportFor + "\n");
	    instructions.forEach((instruction) -> {
		String reportText = String.format("Entity %s with total %s is %f ", instruction.getTraderEntity(),
			reportFor, instruction.getUSD());
		System.out.println(reportText);
	    });
	}
    }
}
