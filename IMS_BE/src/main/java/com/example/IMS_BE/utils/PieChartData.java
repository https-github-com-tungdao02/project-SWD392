package com.example.IMS_BE.utils;

import java.util.List;

public class PieChartData{
        private List<String> labels;
        private List<Float> data;

    public PieChartData(List<String> labels, List<Float> data) {
        this.labels = labels;
        this.data = data;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<Float> getData() {
        return data;
    }

    public void setData(List<Float> data) {
        this.data = data;
    }
}
