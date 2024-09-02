package com.Scratch_Game.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Output {

    private String[][] matrix;

    private Double reward;

    private Map<String, List<String>> applied_winning_combinations;

    private String applied_bonus_symbol;

    public Output() {
    }

    public Output(String[][] matrix, Double reward, Map<String, List<String>> applied_winning_combinations, String applied_bonus_symbol) {
        this.matrix = matrix;
        this.reward = reward;
        this.applied_winning_combinations = applied_winning_combinations;
        this.applied_bonus_symbol = applied_bonus_symbol;
    }

    public String[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(String[][] matrix) {
        this.matrix = matrix;
    }

    public Double getReward() {
        return reward;
    }

    public void setReward(Double reward) {
        this.reward = reward;
    }

    public Map<String, List<String>> getApplied_winning_combinations() {
        return applied_winning_combinations;
    }

    public void setApplied_winning_combinations(Map<String, List<String>> applied_winning_combinations) {
        this.applied_winning_combinations = applied_winning_combinations;
    }

    public String getApplied_bonus_symbol() {
        return applied_bonus_symbol;
    }

    public void setApplied_bonus_symbol(String applied_bonus_symbol) {
        this.applied_bonus_symbol = applied_bonus_symbol;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Output output = (Output) object;
        return Objects.deepEquals(matrix, output.matrix) && Objects.equals(reward, output.reward) && Objects.equals(applied_winning_combinations, output.applied_winning_combinations) && Objects.equals(applied_bonus_symbol, output.applied_bonus_symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.deepHashCode(matrix), reward, applied_winning_combinations, applied_bonus_symbol);
    }

    @Override
    public String toString() {
        return "Output{" +
               "matrix=" + Arrays.toString(matrix) +
               ", reward=" + reward +
               ", applied_winning_combinations=" + applied_winning_combinations +
               ", applied_bonus_symbol='" + applied_bonus_symbol + '\'' +
               '}';
    }
}
