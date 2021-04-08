package oop.focus.statistics.controller;

import oop.focus.finance.model.Account;
import org.joda.time.LocalDate;

import java.util.Objects;
import java.util.Set;

public class FinanceInputBuilderImpl implements FinanceInputBuilder {

    private Set<Account> account;
    private LocalDate startDate;
    private LocalDate endDate;

    @Override
    public final FinanceInputBuilder accounts(final Set<Account> account) {
        this.account = account;
        return this;
    }

    @Override
    public final FinanceInputBuilder from(final LocalDate startDate) {
        this.startDate = Objects.requireNonNull(startDate);
        return this;
    }

    @Override
    public final FinanceInputBuilder to(final LocalDate endDate) {
        this.endDate = Objects.requireNonNull(endDate);
        return this;
    }

    @Override
    public final FinanceInput save() throws IllegalStateException {
        if (this.startDate == null || this.endDate == null || this.account == null) {
            throw new IllegalStateException();
        }
        return new FinanceInputImpl(this.account, this.startDate, this.endDate);
    }
}