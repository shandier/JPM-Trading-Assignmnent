package com.jpm.coding.problem.model;

import java.time.LocalDate;
import java.util.Currency;

public class TradeInstruction {
    private String traderEntity;
    private float agreedFX;
    private Currency currency;
    private LocalDate instructionDate;
    private LocalDate settlementDate;
    private int tradeUnits;
    private double pricePerUnit;
    private TradeType tradeType;
    private Double USD;

    public TradeInstruction() {
	super();
    }

    public TradeInstruction(String traderEntity, float agreedFX, Currency currency, LocalDate instructionDate,
	    LocalDate settlementDate, int tradeUnits, double pricePerUnit, TradeType tradeType) {
	super();
	this.traderEntity = traderEntity;
	this.agreedFX = agreedFX;
	this.currency = currency;
	this.instructionDate = instructionDate;
	this.settlementDate = settlementDate;
	this.tradeUnits = tradeUnits;
	this.pricePerUnit = pricePerUnit;
	this.tradeType = tradeType;
    }

    public String getTraderEntity() {
	return traderEntity;
    }

    public void setTraderEntity(String traderEntity) {
	this.traderEntity = traderEntity;
    }

    public float getAgreedFX() {
	return agreedFX;
    }

    public void setAgreedFX(float agreedFX) {
	this.agreedFX = agreedFX;
    }

    public Currency getCurrency() {
	return currency;
    }

    public void setCurrency(Currency currency) {
	this.currency = currency;
    }

    public LocalDate getInstructionDate() {
	return instructionDate;
    }

    public void setInstructionDate(LocalDate instructionDate) {
	this.instructionDate = instructionDate;
    }

    public LocalDate getSettlementDate() {
	return settlementDate;
    }

    public void setSettlementDate(LocalDate settlementDate) {
	this.settlementDate = settlementDate;
    }

    public int getTradeUnits() {
	return tradeUnits;
    }

    public void setTradeUnits(int tradeUnits) {
	this.tradeUnits = tradeUnits;
    }

    public double getPricePerUnit() {
	return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
	this.pricePerUnit = pricePerUnit;
    }

    public TradeType getTradeType() {
	return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
	this.tradeType = tradeType;
    }

    public Double getUSD() {
	return USD;
    }

    public void setUSD(Double USD) {
	this.USD = USD;
    }
}
