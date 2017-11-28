package com.unit.test;

import java.util.Arrays;
import java.util.List;

public class IndentFormatter implements PrintFormatter {
    @Override
    public String format(int layerIndex, String statisticData) {
        StringBuilder indentBuilder = new StringBuilder("\n");
        int nextLayerIndex = layerIndex + 1;
        while(layerIndex > 0) {
            indentBuilder.append("\t");
            layerIndex--;
        }

        String[] managerDataArray = statisticData.split(SplitSignalConstant.MANAGER_INNER_SPLIT);
        if (managerDataArray.length > 1) {
            return indentBuilder.toString() + managerDataArray[0] + format(nextLayerIndex, managerDataArray[1]);
        }
        List<String> directAnchorDataList = Arrays.asList(statisticData.split(SplitSignalConstant.PARKING_LOT_SPLIT));
        if (statisticData.contains(SplitSignalConstant.PARKING_LOT_SPLIT)) {
            return indentBuilder.toString() + directAnchorDataList.stream().filter(s -> !"".equals(s)).map(s -> format(nextLayerIndex, s)).reduce((s1, s2) -> (s1 + indentBuilder.toString() + s2)).orElse("");
        }
        List<String> secondaryAnchorDataList = Arrays.asList(statisticData.split(SplitSignalConstant.PARKING_BOY_SPLIT));
        if (statisticData.contains(SplitSignalConstant.PARKING_BOY_SPLIT)) {
            return secondaryAnchorDataList.stream().filter( s -> !"".equals(s)).reduce((s1, s2) -> (s1 + indentBuilder.toString() + s2)).orElse("");
        }
        return statisticData;
    }
}
