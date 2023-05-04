package com.company.game.objects.players;

import com.company.game.energy.Energy;
import com.company.game.objects.grid.Grid;

public class Player extends Energy {
    // Характеристики игрока
   protected int x;
   protected int y;
   protected int rx;
   protected int ry;
    Grid grid;
    public char icon = 'T';
    // Конструктор
    public Player(Grid grid, int x, int y, int energy) {
        super(energy, energy);
        this.grid = grid;
        this.x = x;
        this.y = y;
    };
public int getX(){
    return this.x;
}

public int getY(){
    return this.y;
}

    // Проверка на границы карты
    protected void borderCheck() {
        if (this.x < 0) {
            this.x = 0;
        }
        if (this.x >= grid.borderX) {
            this.x = grid.borderX - 1;
        }
        if (this.y < 0) {
            this.y = 0;
        }
        if (this.y >= grid.borderY) {
            this.y = grid.borderY - 1;
        }
    }

    // Возможность выполнять действия
    public void action(String direction, int steps) {
        // Максимальный шаг
        if (steps > this.energy / 10) {
            steps = this.energy / 10;
        }
        // Предыдущее положение
        int oldX = this.x;
        int oldY = this.y;

        // Действия

        // Вверх
        if (direction.equals("Вверх")) {
            for (int i = 1; i <= steps; i++) {
                this.y--;
                this.borderCheck();
                if (i == steps) {
                    this.rx = 0;
                    this.ry = oldY - this.y;
                }
            }
        }
        // Вниз
        if (direction.equals("Вниз")) {
            for (int i = 1; i <= steps; i++) {
                this.y++;
                this.borderCheck();
                if (i == steps) {
                    this.rx = 0;
                    this.ry = this.y - oldY;
                }
            }
        }
        // Влево
        if (direction.equals("Влево")) {
            for (int i = 1; i <= steps; i++) {
                this.x--;
                this.borderCheck();
                if (i == steps) {
                    this.ry = 0;
                    this.rx = oldX - this.x;
                }
            }
        }
        // Вправо
        if (direction.equals("Вправо")) {
            for (int i = 1; i <= steps; i++) {
                this.x++;
                this.borderCheck();
                if (i == steps) {
                    this. ry = 0;
                    this.rx = this.x - oldX;
                }
            }
        }

        //Трата энергии
        this.expenditure(this.rx, this.ry);
        this.rx = 0;
        this.ry = 0;
    }
}
