package com.jpm.coding.problem.services;

import java.util.List;

import com.jpm.coding.problem.helper.InstructionUtil;
import com.jpm.coding.problem.helper.TradeUtil;
import com.jpm.coding.problem.model.TradeInstruction;

public class InstructionManagerImpl implements InstructionManager {

    @Override
    public List<TradeInstruction> settleInstructions(List<TradeInstruction> tradeInstructions) {
	if (tradeInstructions == null || tradeInstructions.isEmpty()) {
	    System.out.println("No data found");
	} else {
	    tradeInstructions.forEach(instruction -> {
		if (instruction != null) {
		    instruction.setSettlementDate(TradeUtil.getWorkingSettlementDate(instruction.getCurrency(),
			    instruction.getSettlementDate()));
		    InstructionUtil.calculateInstructionUSD(instruction);
		}
	    });
	}
	return tradeInstructions;
    }
}
