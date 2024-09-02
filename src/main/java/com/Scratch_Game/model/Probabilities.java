package com.Scratch_Game.model;

import java.util.List;
import java.util.Objects;

public class Probabilities {

    private List<StandardSymbol> standard_symbols;

    private BonusSymbol bonus_symbols;

    public Probabilities() {
    }

    public Probabilities(List<StandardSymbol> standard_symbols, BonusSymbol bonus_symbols) {
        this.standard_symbols = standard_symbols;
        this.bonus_symbols = bonus_symbols;
    }

    public List<StandardSymbol> getStandard_symbols() {
        return standard_symbols;
    }

    public void setStandard_symbols(List<StandardSymbol> standard_symbols) {
        this.standard_symbols = standard_symbols;
    }

    public BonusSymbol getBonus_symbols() {
        return bonus_symbols;
    }

    public void setBonus_symbols(BonusSymbol bonus_symbols) {
        this.bonus_symbols = bonus_symbols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Probabilities that = (Probabilities) o;
        return Objects.equals(standard_symbols, that.standard_symbols) && Objects.equals(bonus_symbols, that.bonus_symbols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(standard_symbols, bonus_symbols);
    }

    @Override
    public String toString() {
        return "Probabilities{" +
               "standard_symbols=" + standard_symbols +
               ", bonus_symbols=" + bonus_symbols +
               '}';
    }
}
