package com.jpm.coding.problem.services;

import java.util.List;

import com.jpm.coding.problem.model.TradeInstruction;

public interface InstructionManager {
    
	/** Method to make modification in the instructions as per trade rules.
	 * @param tradeInstructions: list of TradeInstructions received.
	 * @return List<TradeInstruction> Modified list TradeInstructions, as per trsde rules.
	 */
	List<TradeInstruction> settleInstructions(List<TradeInstruction> tradeInstructions);
}
