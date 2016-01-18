package com.mastering.selenium;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;

/**
 * Created by tcbinh on 1/18/2016.
 */
public class CalculateOffsetPosition {
    public enum CursorPosition {
        TOP_LEFT,
        CENTER
    }

    final WebElement parentElement;
    final WebElement childElement;
    final CursorPosition cursorPosition;
    private int xOffset = 0;
    private int yOffset = 0;

    public CalculateOffsetPosition(WebElement parentElement, WebElement childElement, CursorPosition cursorPosition) {
        this.parentElement = parentElement;
        this.childElement = childElement;
        this.cursorPosition = cursorPosition;
        calculateOffset();
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }

    private void calculateOffset() throws ElementNotVisibleException {
        int elementOneHeight = parentElement.getSize().getHeight();
        int elementOneWidth = parentElement.getSize().getWidth();
        int elementTwoHeight = childElement.getSize().getHeight();
        int elementTwoWidth = childElement.getSize().getWidth();

        if (elementTwoHeight >= elementOneHeight && elementTwoWidth >= elementOneWidth) {
            throw new ElementNotVisibleException("The child element is totally covering the parent element");
        }

        if (cursorPosition.equals(CursorPosition.TOP_LEFT)) {
            xOffset = 1;
            yOffset = 1;
        }

        if (cursorPosition.equals(CursorPosition.CENTER)) {
            if (elementTwoWidth < elementOneWidth) {
                xOffset = (elementTwoWidth / 2) + 1;
            }
            if (elementTwoHeight < elementOneHeight) {
                yOffset = (elementTwoHeight / 2) + 1;
            }
        }
    }
}
