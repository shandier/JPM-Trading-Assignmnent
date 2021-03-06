package com.jpm.coding.problem.helper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.jpm.coding.problem.model.TradeInstruction;
import com.jpm.coding.problem.model.TradeType;

public class InstructionUtil {
    public static final String OUTGOING = "Outgoing";
    public static final String INCOMING = "Incoming";

    public static List<TradeInstruction> filterInstructionsbySettlementDateAndTradeType(
	    List<TradeInstruction> instructions, LocalDate settlemetDate, TradeType tradeType) {
	if (instructions == null || instructions.isEmpty()) {
	    return instructions;
	} else {
	    return instructions.stream().filter(instruction -> (instruction.getSettlementDate().equals(settlemetDate)
		    && instruction.getTradeType().equals(tradeType))).collect(Collectors.toList());
	}
    }

    public static TradeInstruction calculateInstructionUSD(TradeInstruction instruction) {
	if (instruction != null) {
	    double totalUSD = instruction.getAgreedFX() * instruction.getPricePerUnit() * instruction.getTradeUnits();
	    instruction.setUSD(totalUSD);
	}
	return instruction;
    }
}
