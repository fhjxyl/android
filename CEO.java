package Generic;

public class CEO extends Manager {

    public CEO(String n, double s, int year, int month, int day) {
        super(n, s, year, month, day);
        this.bonus = 0;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public double getSalary() {
        return super.getSalary() + bonus;
    }

    private double bonus;

}
