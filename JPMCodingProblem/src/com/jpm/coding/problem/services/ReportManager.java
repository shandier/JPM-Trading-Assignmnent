package com.jpm.coding.problem.services;

import java.time.LocalDate;
import java.util.List;

import com.jpm.coding.problem.model.TradeInstruction;
import com.jpm.coding.problem.model.TradeType;

public interface ReportManager {
    
/** Method to generate report for total number of outgoing and incoming instructions on perticular date.
 * @param instructions : instructions for trade
 * @param tradeType: buy or sell
 * @param settlemetDate: date for which data is required
 * @return 
 */
double generateTradeReport(List<TradeInstruction> instructions, TradeType tradeType,LocalDate settlemetDate);

/** Method to identify top ranked entities for incoming and outgoing instructions 
 * @param instructions: instructions for trade
 * @param tradeType: buy or sell
 * @param settlemetDate: date for which data is required
 */
void generateDashboard(List<TradeInstruction> instructions, TradeType tradeType, LocalDate settlemetDate);
}
