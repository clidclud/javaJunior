package sem1.task2;

public class Fruits implements HealthyFood{

    @Override
    public boolean getProteins(){return false;}

    @Override
    public boolean getFats(){return false;}

    @Override
    public boolean getCarbohydrates(){return true;}

    @Override
    public String getName(){return "Фрукт";}
}
