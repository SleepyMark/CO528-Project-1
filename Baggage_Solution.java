import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
/**
 * Write a description of class Baggage_Solution here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Baggage_Solution implements Candidates
{
    // instance variables - replace the example below with your own
    private Boolean dials[];
    private double fitnessValue;
    private double prevFitness;
    private Boolean prevDial;
    private int prevIndex;
    //Used when: fitness high(bad) = high mutation && fitness low(good) = low mutation
    private double mutationFactor;
    private Random rd;

    /**
     * Constructor for objects of class Baggage_Solution
     */
    public Baggage_Solution()
    {
        dials = new Boolean[100];
    }

    
    public Candidates generate()
    {
        for(int i=0; i<dials.length; i++){
            dials[i] = rd.nextBoolean();
        }
        prevIndex = 0;
        prevDial = dials[prevIndex];
        
        //System.out.println(Arrays.toString(dials));
        return this;
    }
    public void mutate()
    {
        double offset[] = new double[20];
        int index = (int)(Math.random() * dials.length);
        boolean operand = rd.nextBoolean();
        int value = 30;
        
        if(fitnessValue < prevFitness){
            //If the fitness has moved away from convergence, then it will revert back to previous values
            dials[prevIndex] = prevDial;
        }
        //Stores the current values & index to the 'previous value'
        prevIndex = index;
        prevDial = dials[index];
        //If there is positive change, the mutation will still alter the previous dials value
        if(fitnessValue > prevFitness){
            index = prevIndex;
            prevIndex = index;
        }
        
        //Chooses whether to set dial value to true or false
        dials[index] = operand;
    }

    public Object[] getValues()
    {
        return dials;
    }
    
    public void setFitness(double fit)
    {
        prevFitness = fitnessValue;
        fitnessValue = fit;

        if(fitnessValue < 300)mutationFactor = 32;
        if(fitnessValue < 200)mutationFactor = 16;
        if(fitnessValue < 100)mutationFactor = 8;
        if(fitnessValue < 50)mutationFactor = 4;
        if(fitnessValue < 25)mutationFactor = 2;
        if(fitnessValue < 15)mutationFactor = 1;
    }
   
     public double getFitness()
    {
        return this.fitnessValue;
    }
    
    public void set(int index, double value)
    {
        dials[index] = value;
    }
    public Candidates crossOver(Candidates b)
    {
        Candidate_Solution newSolution = new Candidate_Solution();
        double bValues [] = b.getValues();
        for(int dial=0; dial<20; dial++){
            if(dial%2==1) newSolution.set(dial, this.dials[dial]);
            if(dial%2==0) newSolution.set(dial, bValues[dial]);
        }
        
        return newSolution;
    }
    
    public void setAll(int value)
    {
        for(int dial=0; dial<20; dial++) dials[dial]=value;
    }
    public void bigMutation()
    {
        if(Math.random()*10==1){
            mutationFactor = 4;
        }
    }
    
    public Boolean checkSimilarity(Candidates y, int similarities, int average)
    {
        
        return false;
    }
}
