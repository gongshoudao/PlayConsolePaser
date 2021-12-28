package com.nett.play_console.jank;

import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;

public class Item {
    @SerializedName("1")
    private Range range;
    @SerializedName("2")
    private Ratio ratio;
    private static final DecimalFormat df = new DecimalFormat("#");

    public static class Range {
        @SerializedName("1")
        private String leftRange = "0";
        @SerializedName("2")
        private String rightRange = "0";
    }

    public static class Ratio {
        @SerializedName("2")
        private Double ratio = 0d;
    }

    @Override
    public String toString() {
        if (range.rightRange == null) {
            return range.leftRange + "+" + "\t" + ratio.ratio;
        }
        return range.leftRange + "-" + range.rightRange + "\t" + ratio.ratio;
    }
}
