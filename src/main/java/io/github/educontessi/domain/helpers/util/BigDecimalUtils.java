package io.github.educontessi.domain.helpers.util;

import java.math.BigDecimal;

public class BigDecimalUtils {

	private BigDecimalUtils(){
		throw new IllegalStateException("Utility class");
	}

	public static final BigDecimal CEM = BigDecimal.valueOf(100L);

	public static BigDecimal nullToZero(BigDecimal x) {
		return x == null ? BigDecimal.ZERO : x;
	}

	public static boolean greaterThanZero(BigDecimal x) {
		return nullToZero(x).compareTo(BigDecimal.ZERO) > 0;
	}
}
