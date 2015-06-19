package org.javamoney.moneta.spi;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Locale;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.FastMoney;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FastMoneyProducerTest {

	private MonetaryAmountProducer producer;

	private CurrencyUnit currency;

	@BeforeMethod
	public void setup() {
		producer = new FastMoneyProducer();
		currency = Monetary.getCurrency(Locale.getDefault());
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void shouldReturnErrorWhenCurrencyIsNull() {
		producer.create(null, 10);
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void shouldReturnErrorWhenNumberIsNull() {
		producer.create(currency, null);
	}

	@Test
	public void shouldCreateMonetaryAmount() {
		Long value = 10L;
		MonetaryAmount amount = producer.create(currency, value);
		assertEquals(amount.getCurrency(), currency);
		assertEquals(Long.valueOf(amount.getNumber().longValue()), value);
	}

	@Test
	public void shouldCreateUsingFastMoneyImplementation() {
		Long value = 10L;
		MonetaryAmount amount = producer.create(currency, value);
		assertTrue(FastMoney.class.isInstance(amount));
	}

}
