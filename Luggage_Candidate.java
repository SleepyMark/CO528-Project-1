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
    //Used when: fitness high(bad) = high mutation && fitness low(good) = low mutation
    private float mutationFactor;
    private Random rd;
    
    public Luggage_Candidate()
    {
           items =  new boolean[100];
           fitnessValue = 999;
           prevFitness = 1000;
           weightValue = 99;
           prevDial = false;
           rd = new Random();
    }
    public Luggage_Candidate generate()
    {
        for(int i=0; i<items.length; i++){
            items[i] = rd.nextBoolean();
        }
        prevIndex = 99;
        return this;
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
        if(operand == true) items[index] = operand;
        if(operand == false) items[index] = operand;
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
        Luggage_Candidate newSolution = new Luggage_Candidate();
        boolean bValues [] = b.getValues();
        for(int dial=0; dial<20; dial++){
            if(dial%2==0) newSolution.setDial(dial, this.items[dial]);
            if(dial%2==1 || dial == 0 ) newSolution.setDial(dial, bValues[dial]);
        }
        
        return newSolution;
    }
    
    public void bigMutation()
    {
        if(Math.random()*100==1){
            mutationFactor = 4;
        }
    }
    
    
}
