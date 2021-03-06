package com.jpm.coding.problem.helper;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Currency;

public class TradeUtil {
    public static boolean isWeekend(Currency currency, DayOfWeek dayOfWeek) {
	if (Currency.getInstance("AED").equals(currency) || Currency.getInstance("SAR").equals(currency)) {
	    if (DayOfWeek.FRIDAY.equals(dayOfWeek) || DayOfWeek.SATURDAY.equals(dayOfWeek)) {
		return true;
	    }
	} else if (DayOfWeek.SATURDAY.equals(dayOfWeek) || DayOfWeek.SUNDAY.equals(dayOfWeek)) {
	    return true;
	}
	return false;
    }

    public static LocalDate getWorkingSettlementDate(Currency currency, LocalDate localDate) {
	if (currency == null || localDate == null) {
	    System.out.println("Invalid arguments");
	    localDate = LocalDate.now();
	}
	try {
	    if (isWeekend(currency, localDate.getDayOfWeek())) {
		localDate = getWorkingSettlementDate(currency, localDate.plusDays(1));
	    }
	} catch (IllegalArgumentException e) {
	    System.out.println("Invalid arguments");
	    localDate = LocalDate.now();
	}
	return localDate;
    }
}
