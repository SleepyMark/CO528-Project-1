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
    //Stores the items containd
    private boolean items[];
    //Stores the candidates fitness value
    private double fitnessValue;
    //Stores the candidates previous fitness value
    private double prevFitness;
    //Stores the weight of the items all together
    private double weightValue;
    //Stores the previous item state
    private boolean prevDial;
    //Stores the previous item index
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
        //These methods caused overpacking
        //for(int i=0; i<items.length; i++)items[i] = rd.nextBoolean();
        //for(int i=0; i<2; i++) items[(int)(Math.random() * items.length)] = true;
        prevIndex = 99;
    }

    public void mutate()
    {
        int index = (int)(Math.random() * items.length);
        boolean operand =  rd.nextBoolean();

        //If the fitness has moved away from convergence ro has gone over the limit, then it will revert back to previous values
        if(fitnessValue < prevFitness || weightValue > 500) items[prevIndex] = prevDial;

        //Stores the current values & index to the 'previous value'
        prevIndex = index;
        prevDial = items[index];

        //Chooses whether to set the item to true of false
        items[index] = operand;
    }
    /**
     * @return The 100 boolean inputs of the candidate
     */
    public boolean[] getValues()
    {
        return items;
    }
    /**
     * Sets the fitness value of the solution. Previous fitness is a stored
     */
    public void setFitness(double fit)
    {
        prevFitness = fitnessValue;
        fitnessValue = fit;
    }
    /**
     * Returns the fitness of the solution
     * @return (double) fitnessValue
     */
    public double getFitness()
    {
        return this.fitnessValue;
    }
    /**
     * Sets the weight of the current solution. Previous weight is not stored
     */
    public void setWeight(double weight)
    {
        weightValue = weight;
    }
    /**
     * Reverts the changes done, when mutated
     */
    public void revert()
    {
        items[prevIndex] = prevDial;
    }
    /**
     * Returns the weight of the solution
     * @return Weight of the solution
     */
    public double getWeight()
    {
        return this.weightValue;
    }

    /**
     * Sets the dial value at the given index
     * @index The dial which is changed (0-20)'
     * @value Boolean value to be written-in
     */
    protected void setDial(int index, boolean value)
    {
        items[index] = value;
    }
    /**
     * This solution crosses over the current & a given solution, in an alternating pattern
     * @return Crossover of the two solutions
     */
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
