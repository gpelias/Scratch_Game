package com.Scratch_Game.model;

import java.util.Map;
import java.util.Objects;

public class BonusSymbol {

    private Map<String, Integer> symbols;

    public BonusSymbol() {
    }

    public BonusSymbol(Map<String, Integer> symbols) {
        this.symbols = symbols;
    }

    public Map<String, Integer> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<String, Integer> symbols) {
        this.symbols = symbols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BonusSymbol that = (BonusSymbol) o;
        return Objects.equals(symbols, that.symbols);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(symbols);
    }

    @Override
    public String toString() {
        return "BonusSymbol{" +
               "symbols=" + symbols +
               '}';
    }
}
