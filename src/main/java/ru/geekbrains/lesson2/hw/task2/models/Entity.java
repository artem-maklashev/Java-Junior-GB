package ru.geekbrains.lesson2.hw.task2.models;


import ru.geekbrains.lesson2.hw.task2.Column;

import java.util.UUID;

@ru.geekbrains.lesson2.hw.task2.Entity
public class Entity {

    @Column(name = "id", primaryKey = true)
    private UUID id;

}
