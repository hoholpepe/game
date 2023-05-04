package game;
import game.objects.grid.*;
import game.objects.players.*;
import game.objects.players.Terminator;

import java.util.Scanner;
public class Main {
    public static void main(String args[]) {

        //Здесь UI, в других классах - backend

        //Создание объектов созданных классов
        Grid grid = new Grid(100, 100);
        WanderingTraveler wanderingTraveler = new WanderingTraveler(grid, 50, 95, 100, false);
        Terminator terminator = new Terminator(grid, 0, 95, 100000);
        //Генерируем камни
        //выбираем кратные второму параметру из случайных целых чисел в промежутке от 0 до первого параметра
        grid.generateStones(10, 5);
        //убираем камень с места появления путешественника и терминатора
        grid.stonesMap[wanderingTraveler.getY()][wanderingTraveler.getX()] = false;
        grid.stonesMap[terminator.getY()][terminator.getX()] = false;
        //Основной цикл программы
        while(true) {
            //Рисуем поле
            grid.draw(wanderingTraveler, terminator);
            for (int i = 0; i < grid.borderY; i++) {
                for (int j = 0; j < grid.borderX; j++) {
                    System.out.print(grid.graphicMap[i][j]);       
                }
                System.out.println();
            }
            //Пишем характеристики путешественника и терминатора
            System.out.println("X: "+wanderingTraveler.getX()+" Y: "+wanderingTraveler.getY()+" TRAVELER ENERGY: "+wanderingTraveler.getEnergy());
            System.out.println("X: "+terminator.getX()+" Y: "+terminator.getY()+" TERMINATOR ENERGY: "+terminator.getEnergy());
            //Считываем комманды путешественнику от пользователя
            Scanner in = new Scanner(System.in);
            //Первое - слово
            String direction = in.next();
            if (direction.equals("stop")){
                in.close();
                break;
            }
            //Второе - цифра
            int steps = 0;
            if (!(direction.equals("sit")||direction.equals("stand"))){
                steps = in.nextInt();
            }
            //Терминатор выполняет команды когда путешественник сидит
            if (wanderingTraveler.getSitting()){
                terminator.action(direction, steps);
            }
            //Выполняем команды
            wanderingTraveler.action(grid.stonesMap, direction, steps);
        }
    }
}