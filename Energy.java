package game.misc;

public class Energy{

    //Поля
    protected int energy;
    protected int maxEnergy;
    
    //Конструктор
    public Energy(int energy, int maxEnergy){
        this.energy = energy;
        this.maxEnergy = maxEnergy;
    }

    //Геттеры для энергии

    public int getEnergy(){
        return this.energy;
    }
    public int getMaxEnergy(){
        return this.maxEnergy;
    }

    // Затрата энергии
    protected void expenditure(int rx, int ry) {
        this.energy -= rx * 10;
        this.energy -= ry * 10;
        if (this.energy < 0) {
            this.energy = 0;
        }
        if (this.energy > this.maxEnergy) {
            this.energy = this.maxEnergy;
        }
    }

}
