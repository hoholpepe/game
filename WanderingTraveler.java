package game.objects.players;
import game.objects.grid.*;

public class WanderingTraveler extends Terminator {
    
    // Характеристики путешественника
    protected boolean sitting;
    public char icon = '|';

    //Конструктор
    public WanderingTraveler(Grid grid, int x, int y, int energy, boolean sitting) {
        super(grid, x, y, energy);
        this.sitting = sitting;
    };

    //Геттер состояния сидения

    public boolean getSitting(){
        return this.sitting;
    }

    // Проверка на камни
    protected boolean stoneCheck(boolean[][] stonesMap, String direction) {
        if (stonesMap[this.y][this.x]) {
            switch (direction) {
                case "up":
                    this.y += 1;
                    break;
                case "down":
                    this.y -= 1;
                    break;
                case "left":
                    this.x += 1;
                    break;
                case "right":
                    this.x -= 1;
                    break;
            }
            return true;
        } else {
            return false;
        }
    }
    
    // Возможность выполнять действия - перегружаем метод из родительского класса!
    public void action(boolean[][] stonesMap, String direction, int steps) {
        // Максимальный шаг
        if (steps > this.energy / 10) {
            steps = this.energy / 10;
        }
        // Предыдущее положение
        int oldX = this.x;
        int oldY = this.y;

        // Действия

        switch (direction) {

            // Встать
            case "stand":
                this.sitting = false;
                this.rx = 0;
                this.ry = 0;
                this.icon = '|';
                break;
            
            // Сесть
            case "sit":
                this.sitting = true;
                this.energy = this.maxEnergy;
                this.rx = 0;
                this.ry = 0;
                this.icon = 'L';
                break;
            
        }
        if (!this.sitting && this.energy != 0) {

            // Вверх
            if (direction.equals("up")) {
                for (int i = 1; i <= steps; i++) {
                    this.y--;
                    this.borderCheck();
                    if (this.stoneCheck(stonesMap, direction)) {
                        this.rx = 0;
                        this.ry = oldY - this.y;
                        break;
                    }
                    if (i == steps) {
                        this.rx = 0;
                        this.ry = oldY - this.y;
                    }
                }
            }

            // Вниз
            if (direction.equals("down")) {
                for (int i = 1; i <= steps; i++) {
                    this.y++;
                    this.borderCheck();
                    if (this.stoneCheck(stonesMap, direction)) {
                        this.rx = 0;
                        this.ry = this.y - oldY;
                        break;
                    }
                    if (i == steps) {
                        this.rx = 0;
                        this.ry = this.y - oldY;
                    }
                }
            }

            // Влево
            if (direction.equals("left")) {
                for (int i = 1; i <= steps; i++) {
                    this.x--;
                    this.borderCheck();
                    if (this.stoneCheck(stonesMap, direction)) {
                        this.ry = 0;
                        this.rx = oldX - this.x;
                        break;
                    }
                    if (i == steps) {
                        this.ry = 0;
                        this.rx = oldX - this.x;
                    }
                }
            }
            
            // Вправо
            if (direction.equals("right")) {
                for (int i = 1; i <= steps; i++) {
                    this.x++;
                    this.borderCheck();
                    if (this.stoneCheck(stonesMap, direction)) {
                        this.ry = 0;
                        this.rx = this.x - oldX;
                        break;
                    }
                    if (i == steps) {
                        this.ry = 0;
                        this.rx = this.x - oldX;
                    }
                }
            } 
        }

        //Затрачиваем энергию
        this.expenditure(this.rx, this.ry);
        if (this.energy <= 0) {
            this.icon = '-';
        }
        this.rx = 0;
        this.ry = 0;
    }

}
