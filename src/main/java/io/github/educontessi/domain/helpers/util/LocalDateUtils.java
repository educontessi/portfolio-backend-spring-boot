package io.github.educontessi.domain.helpers.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class LocalDateUtils {

	private LocalDateUtils(){
		throw new IllegalStateException("Utility class");
	}

	public static LocalDate competencia(LocalDate data) {
		return data.with(TemporalAdjusters.firstDayOfMonth());
	}

	public static LocalDate competenciaMesAnterior(LocalDate data) {
		return data.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
	}

	public static LocalDate competenciaMesPosterior(LocalDate data) {
		return data.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
	}

	public static LocalDate converterParaLocalDate(LocalDateTime localDateTime) {
		return localDateTime != null
				? LocalDate.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth())
				: null;
	}

}
