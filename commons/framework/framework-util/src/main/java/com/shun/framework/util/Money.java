package com.shun.framework.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;

/**
 * User: mew <p />
 * Time: 17/11/6 16:40  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Money implements Serializable, Comparable<Object> {

    private static final long serialVersionUID = 2615975856278640587L;

    public static final String DEFAULT_CURRENCY_CODE = "CNY";

    public static final RoundingMode DEFAULT_ROUNDING_MODE;

    private BigDecimal amount;

    private Currency currency;

    static {
        DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;
    }

    public Money() {
        this("0");
    }

    public Money(long yuan, int cent) {
        this(yuan, cent, Currency.getInstance("CNY"));
    }

    public Money(long yuan, int cent, Currency currency) {
        this.currency = currency;
        this.setAmount((new BigDecimal(yuan)).add((new BigDecimal(cent)).divide(this.getCentFactor())));
    }

    public Money(String amount) {
        this(amount, Currency.getInstance("CNY"));
    }

    public Money(String amount, Currency currency) {
        this(new BigDecimal(amount), currency);
    }

    public Money(String amount, Currency currency, RoundingMode roundingMode) {
        this(new BigDecimal(amount), currency, roundingMode);
    }

    /** @deprecated  */
    public Money(double amount) {
        this(amount, Currency.getInstance("CNY"));
    }

    /** @deprecated  */
    public Money(double amount, Currency currency) {
        this.currency = currency;
        this.setAmount(new BigDecimal(amount));
    }

    public Money(BigDecimal amount) {
        this(amount, Currency.getInstance("CNY"));
    }

    public Money(BigDecimal amount, RoundingMode roundingMode) {
        this(amount, Currency.getInstance("CNY"), roundingMode);
    }

    public Money(BigDecimal amount, Currency currency) {
        this(amount, currency, DEFAULT_ROUNDING_MODE);
    }

    public Money(BigDecimal amount, Currency currency, RoundingMode roundingMode) {
        this.currency = currency;
        this.setAmount(amount, roundingMode);
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.setAmount(new BigDecimal(amount), DEFAULT_ROUNDING_MODE);
    }

    public void setAmount(BigDecimal amount) {
        this.setAmount(amount, DEFAULT_ROUNDING_MODE);
    }

    public void setAmount(String amount, RoundingMode roundingMode) {
        this.setAmount(new BigDecimal(amount), roundingMode);
    }

    public void setAmount(BigDecimal amount, RoundingMode roundingMode) {
        if (amount != null) {
            this.amount = amount.setScale(this.currency.getDefaultFractionDigits(), roundingMode);
        }

    }

    public long getCent() {
        return this.amount.multiply(this.getCentFactor()).longValue();
    }

    public void setCent(long cent) {
        this.setAmount((new BigDecimal(cent)).divide(this.getCentFactor()));
    }

    /** @deprecated  */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getCentFactor() {
        return (new BigDecimal(10)).pow(this.currency.getDefaultFractionDigits());
    }

    public boolean equals(Object other) {
        return other instanceof Money && this.equals((Money) other);
    }

    public boolean equals(Money other) {
        return this.currency.equals(other.currency) && this.amount.equals(other.amount);
    }

    public int hashCode() {
        long cent = this.amount.multiply(this.getCentFactor()).longValue();
        return (int) (cent ^ cent >>> 32);
    }

    public int compareTo(Object other) {
        return this.compareTo((Money) other);
    }

    public int compareTo(Money other) {
        this.assertSameCurrencyAs(other);
        return this.amount.compareTo(other.amount);
    }

    public boolean greaterThan(Money other) {
        return this.compareTo(other) > 0;
    }

    public Money add(Money other) {
        this.assertSameCurrencyAs(other);
        return this.newMoneyWithSameCurrency(this.amount.add(other.amount));
    }

    public Money addTo(Money other) {
        this.assertSameCurrencyAs(other);
        this.amount = this.amount.add(other.amount);
        return this;
    }

    public Money subtract(Money other) {
        this.assertSameCurrencyAs(other);
        return this.newMoneyWithSameCurrency(this.amount.subtract(other.amount));
    }

    public Money subtractFrom(Money other) {
        this.assertSameCurrencyAs(other);
        this.amount = this.amount.subtract(other.amount);
        return this;
    }

    public Money multiply(long val) {
        return this.newMoneyWithSameCurrency(this.amount.multiply(new BigDecimal(val)));
    }

    public Money multiplyBy(long val) {
        this.amount = this.amount.multiply(new BigDecimal(val));
        return this;
    }

    public Money multiply(double val) {
        return this.newMoneyWithSameCurrency(this.amount.multiply(new BigDecimal(val)));
    }

    public Money multiplyBy(double val) {
        this.setAmount(this.amount.multiply(new BigDecimal(val)));
        return this;
    }

    public Money multiply(BigDecimal val) {
        return this.multiply(val, DEFAULT_ROUNDING_MODE);
    }

    public Money multiplyBy(BigDecimal val) {
        return this.multiplyBy(val, DEFAULT_ROUNDING_MODE);
    }

    public Money multiply(BigDecimal val, RoundingMode roundingMode) {
        BigDecimal newCent = this.amount.multiply(val);
        return this.newMoneyWithSameCurrency(newCent, roundingMode);
    }

    public Money multiplyBy(BigDecimal val, RoundingMode roundingMode) {
        this.setAmount(this.amount.multiply(val), roundingMode);
        return this;
    }

    public Money divide(double val) {
        return this.newMoneyWithSameCurrency(this.amount.divide(new BigDecimal(val)));
    }

    public Money divideBy(double val) {
        this.amount = this.amount.divide(new BigDecimal(val));
        return this;
    }

    public Money divide(BigDecimal val) {
        return this.divide(val, DEFAULT_ROUNDING_MODE);
    }

    public Money divide(BigDecimal val, RoundingMode roundingMode) {
        BigDecimal newAmount = this.amount.divide(val);
        return this.newMoneyWithSameCurrency(newAmount, roundingMode);
    }

    public Money divideBy(BigDecimal val) {
        return this.divideBy(val, DEFAULT_ROUNDING_MODE);
    }

    public Money divideBy(BigDecimal val, RoundingMode roundingMode) {
        this.setAmount(this.amount.divide(val), roundingMode);
        return this;
    }

    public Money[] allocate(int targets) {
        long cent = this.getCent();
        Money[] results = new Money[targets];
        long lowResult = cent / (long) targets;
        long highResult = lowResult + 1L;
        int remainder = (int) cent % targets;
        int i;
        for (i = 0; i < remainder; ++i) {
            results[i] = this.newMoneyWithSameCurrency((new BigDecimal(highResult)).divide(this.getCentFactor()));
        }
        for (i = remainder; i < targets; ++i) {
            results[i] = this.newMoneyWithSameCurrency((new BigDecimal(lowResult)).divide(this.getCentFactor()));
        }
        return results;
    }

    public Money[] allocate(long[] ratios) {
        long[] results = new long[ratios.length];
        long total = 0L;
        for (int i = 0; i < ratios.length; ++i) {
            total += ratios[i];
        }
        long remainder = this.getCent();
        for (int i = 0; i < results.length; ++i) {
            results[i] = this.getCent() * ratios[i] / total;
            remainder -= results[i];
        }
        for (int i = 0; (long) i < remainder; ++i) {
            ++results[i];
        }
        Money[] ret = new Money[ratios.length];
        for (int i = 0; i < ratios.length; ++i) {
            ret[i] = this.newMoneyWithSameCurrency((new BigDecimal(results[i])).divide(this.getCentFactor()));
        }
        return ret;
    }

    public String toString() {
        return this.getAmount().toString();
    }

    public String formatWithCode() {
        NumberFormat nf = new DecimalFormat(this.getNumberFormatPattern());
        StringBuilder sb = new StringBuilder();
        sb.append(this.currency.getCurrencyCode()).append(nf.format(this.amount));
        return sb.toString();
    }

    public String format() {
        NumberFormat nf = new DecimalFormat(this.getNumberFormatPattern());
        return nf.format(this.amount);
    }

    public String formatWithSymbols() {
        NumberFormat nf = new DecimalFormat("¤" + this.getNumberFormatPattern());
        nf.setCurrency(this.currency);
        return nf.format(this.amount);
    }

    private String getNumberFormatPattern() {
        int digits = this.currency.getDefaultFractionDigits();
        StringBuilder format = new StringBuilder();
        format.insert(0, '0');
        int i;
        for (i = 1; i < 19 - digits; ++i) {
            if (i % 3 == 0) {
                format.insert(0, ',');
            }
            format.insert(0, '#');
        }
        if (digits > 0) {
            format.append('.');
            for (i = 0; i < digits; ++i) {
                format.append("0");
            }
        }
        return format.toString();
    }

    protected void assertSameCurrencyAs(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Money math currency mismatch.");
        }
    }

    protected Money newMoneyWithSameCurrency(BigDecimal amount) {
        return this.newMoneyWithSameCurrency(amount, DEFAULT_ROUNDING_MODE);
    }

    protected Money newMoneyWithSameCurrency(BigDecimal amount, RoundingMode roundingMode) {
        Money money = new Money();
        money.currency = this.currency;
        money.setAmount(amount, roundingMode);
        return money;
    }

    public String dump() {
        String lineSeparator = System.getProperty("line.separator");
        StringBuffer sb = new StringBuffer();
        sb.append("amount = ").append(this.amount).append(lineSeparator);
        sb.append("currency = ").append(this.currency);
        return sb.toString();
    }

}
