package com.techlab.article.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class DateUtil {

    private DateUtil() {
        throw new UnsupportedOperationException("Clase utilitaria, no se debe instanciar.");
    }

    public static long calculateExpirationDays(LocalDate expirationDate) {
        LocalDate today = LocalDate.now();
        return ChronoUnit.DAYS.between(today, expirationDate);
    }
}
