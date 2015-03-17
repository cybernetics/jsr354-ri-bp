/**
 * Copyright (c) 2012, 2015, Credit Suisse (Anatole Tresch), Werner Keil and others by the @author tag.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.javamoney.moneta.bp.convert.internal;

import static org.javamoney.bp.api.convert.MonetaryConversions.getExchangeRateProvider;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.math.BigDecimal;

import org.javamoney.bp.api.CurrencyUnit;
import org.javamoney.bp.api.MonetaryAmount;
import org.javamoney.bp.api.MonetaryCurrencies;
import org.javamoney.bp.api.convert.CurrencyConversion;
import org.javamoney.bp.api.convert.ExchangeRateProvider;

import org.javamoney.moneta.bp.ExchangeRateType;
import org.javamoney.moneta.bp.Money;
import org.javamoney.moneta.bp.internal.convert.IdentityRateProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class IdentityRateProviderTest {

    private static final CurrencyUnit EURO = MonetaryCurrencies
            .getCurrency("EUR");
    private static final CurrencyUnit DOLLAR = MonetaryCurrencies
            .getCurrency("USD");
    private static final CurrencyUnit BRAZILIAN_REAL = MonetaryCurrencies
            .getCurrency("BRL");

    private ExchangeRateProvider provider;

    @BeforeTest
    public void setup() throws InterruptedException {
        provider = getExchangeRateProvider(ExchangeRateType.IDENTITY);
    }

    @Test
    public void shouldReturnsIdentityRateProvider() {
        assertNotNull(provider);
        assertEquals(provider.getClass(), IdentityRateProvider.class);
    }

    @Test
    public void shouldReturnsSameDollarValue() {
        CurrencyConversion currencyConversion = provider.getCurrencyConversion(DOLLAR);
        assertNotNull(currencyConversion);
        MonetaryAmount money = Money.of(BigDecimal.TEN, DOLLAR);
        MonetaryAmount result = currencyConversion.apply(money);

        assertEquals(result.getCurrency(), DOLLAR);
        assertEquals(result.getNumber().numberValue(BigDecimal.class),
                BigDecimal.TEN);

    }

    @Test
    public void shouldReturnsSameBrazilianValue() {
        CurrencyConversion currencyConversion = provider
                .getCurrencyConversion(BRAZILIAN_REAL);
        assertNotNull(currencyConversion);
        MonetaryAmount money = Money.of(BigDecimal.TEN, BRAZILIAN_REAL);
        MonetaryAmount result = currencyConversion.apply(money);

        assertEquals(result.getCurrency(), BRAZILIAN_REAL);
        assertEquals(result.getNumber().numberValue(BigDecimal.class),
                BigDecimal.TEN);

    }

    @Test
    public void shouldReturnsSameEuroValue() {
        CurrencyConversion currencyConversion = provider
                .getCurrencyConversion(EURO);
        assertNotNull(currencyConversion);
        MonetaryAmount money = Money.of(BigDecimal.TEN, EURO);
        MonetaryAmount result = currencyConversion.apply(money);

        assertEquals(result.getCurrency(), EURO);
        assertEquals(result.getNumber().numberValue(BigDecimal.class),
                BigDecimal.TEN);

    }


}