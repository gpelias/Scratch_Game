package com.Scratch_Game.model;

import java.util.Map;
import java.util.Objects;

public class StandardSymbol {

    private Integer column;

    private Integer row;

    private Map<String, Integer> symbols;

    public StandardSymbol() {
    }

    public StandardSymbol(Integer column, Integer row, Map<String, Integer> symbols) {
        this.column = column;
        this.row = row;
        this.symbols = symbols;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Map<String, Integer> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<String, Integer> symbols) {
        this.symbols = symbols;
    }

    public boolean isPosition(Integer row, Integer column) {
        return this.row.equals(row) && this.column.equals(column);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StandardSymbol that = (StandardSymbol) o;
        return Objects.equals(column, that.column) && Objects.equals(row, that.row) && Objects.equals(symbols, that.symbols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row, symbols);
    }

    @Override
    public String toString() {
        return "StandardSymbol{" +
               "column=" + column +
               ", row=" + row +
               ", symbols=" + symbols +
               '}';
    }
}
