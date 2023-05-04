package game.objects.grid;
import game.objects.players.*;
public class Grid{
    
    // Характеристики поля
    public int borderX;
    public int borderY;
    public int multiplier;
    public int ratio;
    public boolean[][] stonesMap = new boolean[8191][8191]; // максимальная длина строки консоли windows
    public char[][] graphicMap = new char[8191][8191]; // максимальная длина строки консоли windows

    //Конструктор
    public Grid (int borderX, int borderY) {
        this.borderX = borderX;
        this.borderY = borderY;
    }

    // Массив камней
    public void generateStones(int multiplier, int ratio) {
        this.ratio = ratio;
        this.multiplier = multiplier;
        for (int i = 0; i < this.borderY; i++) {
            for (int j = 0; j < this.borderX; j++) {
                if ((int)Math.round(Math.random() * this.multiplier % this.ratio) == 0) {
                    stonesMap[i][j] = true;
                } else {
                    stonesMap[i][j] = false;
                }
            }
        }
    }

    // Графический вывод сетки с игроками на ней
    public void draw(WanderingTraveler wanderingTraveler, Terminator terminator) {
        for (int i = 0; i < this.borderY; i++) {
            for (int j = 0; j < this.borderX; j++) {
                if (this.stonesMap[i][j]){
                    this.graphicMap[i][j] = '\u2588';
                } else {
                    this.graphicMap[i][j] = '.';
                }
            }
        }
        this.graphicMap[wanderingTraveler.getY()][wanderingTraveler.getX()] = wanderingTraveler.icon;
        this.graphicMap[terminator.getY()][terminator.getX()] = terminator.icon;
    }

}
