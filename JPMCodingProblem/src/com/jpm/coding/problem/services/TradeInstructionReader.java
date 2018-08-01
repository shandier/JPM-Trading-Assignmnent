package com.jpm.coding.problem.services;

import java.util.List;

import com.jpm.coding.problem.model.TradeInstruction;

public interface TradeInstructionReader {
    List<TradeInstruction> getTradeInstruction(Object source);
}
