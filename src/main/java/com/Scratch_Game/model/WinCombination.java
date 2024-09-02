package com.Scratch_Game.model;

import java.util.List;
import java.util.Objects;

public class WinCombination {

    private Integer reward_multiplier;

    private String when;

    private Integer count;

    private String group;

    private List<List<String>> covered_areas;

    public WinCombination() {
    }

    public WinCombination(Integer reward_multiplier, String when, Integer count, String group, List<List<String>> covered_areas) {
        this.reward_multiplier = reward_multiplier;
        this.when = when;
        this.count = count;
        this.group = group;
        this.covered_areas = covered_areas;
    }

    public Integer getReward_multiplier() {
        return reward_multiplier;
    }

    public void setReward_multiplier(Integer reward_multiplier) {
        this.reward_multiplier = reward_multiplier;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<List<String>> getCovered_areas() {
        return covered_areas;
    }

    public void setCovered_areas(List<List<String>> covered_areas) {
        this.covered_areas = covered_areas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinCombination that = (WinCombination) o;
        return Objects.equals(reward_multiplier, that.reward_multiplier) && Objects.equals(when, that.when) && Objects.equals(count, that.count) && Objects.equals(group, that.group) && Objects.equals(covered_areas, that.covered_areas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reward_multiplier, when, count, group, covered_areas);
    }

    @Override
    public String toString() {
        return "WinCombination{" +
               "reward_multiplier=" + reward_multiplier +
               ", when='" + when + '\'' +
               ", count=" + count +
               ", group='" + group + '\'' +
               ", covered_areas=" + covered_areas +
               '}';
    }
}
