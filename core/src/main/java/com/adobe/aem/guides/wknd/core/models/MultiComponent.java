package com.adobe.aem.guides.wknd.core.models;

import java.util.List;

public interface MultiComponent {
    String getAlignChildren();

    String getFruitType();

    boolean getColumnLast();

    boolean getHideMismatches();

    String getBackgroundColor();

    List<String> getHeadlines();

    int getColumnWidth();
}
