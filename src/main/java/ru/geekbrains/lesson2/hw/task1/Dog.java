package ru.geekbrains.lesson2.hw.task1;

public class Dog extends Animal {
    boolean isHunter;
    boolean isService;
    boolean isDecorative;

    public Dog(String name, int age, boolean isHunter, boolean isService, boolean isDecorative) {
        super(name, age);
        this.isHunter = isHunter;
        this.isService = isService;
        this.isDecorative = isDecorative;
    }

    public void makeSound() {
        System.out.println("The dog say 'Wow'");
    }

    public boolean isHunter() {
        return isHunter;
    }

    public boolean isService() {
        return isService;
    }

    public boolean isDecorative() {
        return isDecorative;
    }

    public void setHunter(boolean hunter) {
        if (!isDecorative && !isService)
            isHunter = hunter;
    }

    public void setService(boolean service) {
        if (!isHunter && !isDecorative)
            isService = service;
    }

    public void setDecorative(boolean decorative) {
        if (!isHunter && !isService)
            isDecorative = decorative;
    }
}
