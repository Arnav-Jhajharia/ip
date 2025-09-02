package com.arnavjhajharia.penguin.ui;

public interface Ui {
    void showIntro();
    void showDivider();
    void showText(String text);
    void showError(String errorText);

    String readLine();
    void showExit();
}
