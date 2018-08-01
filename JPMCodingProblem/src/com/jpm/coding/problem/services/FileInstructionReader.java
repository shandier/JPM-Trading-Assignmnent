package com.jpm.coding.problem.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

import com.jpm.coding.problem.model.TradeInstruction;
import com.jpm.coding.problem.model.TradeType;

public class FileInstructionReader implements TradeInstructionReader {
    @Override
    public List<TradeInstruction> getTradeInstruction(Object source) {
	List<TradeInstruction> instructionslist = new ArrayList<>();
	if (!(source instanceof String)) {
	    System.out.println("Invalid source to Read Instructions");
	    return instructionslist;
	}
	String fileName = (String) source;
	try (FileReader fr = new FileReader(fileName); BufferedReader br = new BufferedReader(fr);) {
	    String line = null;
	    // Skip header
	    br.readLine();
	    while ((line = br.readLine()) != null) {
		if (line.equals("")) {
		    System.out.println("Skipping blank line");
		    continue;
		}
		String[] insrunctionData = line.split(",");
		if (ValidateRecord(insrunctionData)) {
		    try {
			TradeInstruction instruction = parseInstructions(insrunctionData);
			instructionslist.add(instruction);
		    } catch (IllegalArgumentException | DateTimeParseException e) {
			System.out.println("Unable to parse, Skipping invalid record");
			continue;
		    }
		} else {
		    continue;
		}
	    }
	} catch ( IOException ex) {
	    System.out.println("Unable to read souce file "+ fileName);
	}
	return instructionslist;

    }

    private TradeInstruction parseInstructions(String[] instructionData)
	    throws IllegalArgumentException, DateTimeParseException {
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
	TradeInstruction instruction = new TradeInstruction();
	instruction.setTraderEntity(instructionData[0]);
	instruction.setTradeType(instructionData[1].equalsIgnoreCase("b") ? TradeType.BUY : TradeType.SELL);
	instruction.setAgreedFX(Float.valueOf(instructionData[2]));
	instruction.setCurrency(Currency.getInstance(instructionData[3]));
	instruction.setInstructionDate(LocalDate.parse(instructionData[4], dtf));
	instruction.setSettlementDate(LocalDate.parse(instructionData[5], dtf));
	instruction.setTradeUnits(Integer.valueOf(instructionData[6]));
	instruction.setPricePerUnit(Double.valueOf(instructionData[7]));
	return instruction;
    }

    private boolean ValidateRecord(String[] instructionData) {
	if (instructionData.length < 8 || Arrays.asList(instructionData).contains("")) {
	    System.out.println("Skipping invalid record");
	    return false;
	}
	if (!(instructionData[1].equalsIgnoreCase("b") || instructionData[1].equalsIgnoreCase("s"))) {
	    System.out.println("Trade type value should be B or S, Skipping invalid record ");
	    return false;
	}
	return true;
    }
}
