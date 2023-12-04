package ru.geekbrains.lesson2.hw.task1;

public class Cat extends Animal{
    boolean isMouseHunter;

    public Cat(String name, int age, boolean isMouseHunter) {
        super(name, age);
        this.isMouseHunter = isMouseHunter;
    }

    public boolean isMouseHunter() {
        return isMouseHunter;
    }

    public void setMouseHunter(boolean mouseHunter) {
        isMouseHunter = mouseHunter;
    }
}
