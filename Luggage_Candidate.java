import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
/**
 * Write a description of class Luggage_Candidates here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Luggage_Candidate
{
    private boolean items[];
    private double fitnessValue;
    private double prevFitness;
    private double weightValue;
    private boolean prevDial;
    private int prevIndex;

    private Random rd;

    public Luggage_Candidate()
    {
        items =  new boolean[100];
        fitnessValue = 1;
        prevFitness = 0;
        weightValue = 0;
        prevDial = false;
        rd = new Random();
        /*for(int i=0; i<items.length; i++){
        items[i] = rd.nextBoolean();
        }*/
        //for(int i=0; i<2; i++) items[(int)(Math.random() * items.length)] = true;
        prevIndex = 99;
    }

    public void mutate()
    {
        int index = (int)(Math.random() * items.length);
        boolean operand =  rd.nextBoolean();

        //If the fitness has moved away from convergence, then it will revert back to previous values
        if(fitnessValue < prevFitness || weightValue > 500) items[prevIndex] = prevDial;

        //Stores the current values & index to the 'previous value'
        prevIndex = index;
        prevDial = items[index];

        //Chooses whether to set the item to true of false
        items[index] = operand;
    }

    public boolean[] getValues()
    {
        return items;
    }

    public void setFitness(double fit)
    {
        prevFitness = fitnessValue;
        fitnessValue = fit;
    }

    public double getFitness()
    {
        return this.fitnessValue;
    }

    public void setWeight(double weight)
    {
        weightValue = weight;
    }
    
    public void revert()
    {
        items[prevIndex] = prevDial;
    }

    public double getWeight()
    {
        return this.weightValue;
    }

    protected void setDial(int index, boolean value)
    {
        items[index] = value;
    }

    public Luggage_Candidate crossOver(Luggage_Candidate b)
    {
        Luggage_Candidate newItem = new Luggage_Candidate();
        boolean bValues [] = b.getValues();
        for(int dial=0; dial<items.length; dial++){
            if(dial%2==0) newItem.setDial(dial, this.items[dial]);
            if(dial%2==1 || dial == 0 ) newItem.setDial(dial, bValues[dial]);
        }

        return newItem;
    }

    
}
