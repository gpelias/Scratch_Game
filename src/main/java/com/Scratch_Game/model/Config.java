package com.Scratch_Game.model;

import java.util.Map;
import java.util.Objects;

public class Config {

    private Integer columns;

    private Integer rows;

    private Map<String, Symbol> symbols;

    private Probabilities probabilities;

    private Map<String, WinCombination> win_combinations;

    public Config() {
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<String, Symbol> symbols) {
        this.symbols = symbols;
    }

    public Probabilities getProbabilities() {
        return probabilities;
    }

    public void setProbabilities(Probabilities probabilities) {
        this.probabilities = probabilities;
    }

    public Map<String, WinCombination> getWin_combinations() {
        return win_combinations;
    }

    public void setWin_combinations(Map<String, WinCombination> win_combinations) {
        this.win_combinations = win_combinations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Config config = (Config) o;
        return Objects.equals(columns, config.columns) && Objects.equals(rows, config.rows) && Objects.equals(symbols, config.symbols) && Objects.equals(probabilities, config.probabilities) && Objects.equals(win_combinations, config.win_combinations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(columns, rows, symbols, probabilities, win_combinations);
    }

    @Override
    public String toString() {
        return "Config{" +
               "columns=" + columns +
               ", rows=" + rows +
               ", symbols=" + symbols +
               ", probabilities=" + probabilities +
               ", win_combinations=" + win_combinations +
               '}';
    }
}