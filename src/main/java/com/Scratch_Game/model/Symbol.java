package com.Scratch_Game.model;

import java.util.Objects;

public class Symbol {

    private Double extra;

    private Double reward_multiplier;

    private String type;

    private String impact;

    public Symbol() {
    }

    public Symbol(Double extra, Double reward_multiplier, String type, String impact) {
        this.extra = extra;
        this.reward_multiplier = reward_multiplier;
        this.type = type;
        this.impact = impact;
    }

    public Double getExtra() {
        return extra;
    }

    public void setExtra(Double extra) {
        this.extra = extra;
    }

    public Double getReward_multiplier() {
        return reward_multiplier;
    }

    public void setReward_multiplier(Double reward_multiplier) {
        this.reward_multiplier = reward_multiplier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol = (Symbol) o;
        return Objects.equals(reward_multiplier, symbol.reward_multiplier) && Objects.equals(type, symbol.type) && Objects.equals(impact, symbol.impact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reward_multiplier, type, impact);
    }

    @Override
    public String toString() {
        return "Symbol{" +
               "reward_multiplier='" + reward_multiplier + '\'' +
               ", type='" + type + '\'' +
               ", impact='" + impact + '\'' +
               '}';
    }
}
