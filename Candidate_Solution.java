import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
/**
 * Write a description of class Candidate_Solution here.
 *
 * @author Mark Cabrera (mc967)
 * @version 1.5.0
 */
public class Candidate_Solution
{
    // instance variables - replace the example below with your own
    private double dials[];
    private double fitnessValue;
    private double prevFitness;
    private double prevDial;
    private int prevIndex;
    //Used when: fitness high(bad) = high mutation && fitness low(good) = low mutation
    private double mutationFactor;
    private Random rd;
    /**
     * Constructor for objects of class Candidate_Solution
     */
    public Candidate_Solution()
    {
           dials =  new double[20];
           fitnessValue = 999;
           prevFitness = 1000;
           mutationFactor = 0;
           rd = new Random();
    }
    public Candidate_Solution generate()
    {
        for(int i=0; i<dials.length; i++){
            dials[i] = (Math.random() * 10)-5;
        }
        prevIndex = 0;
        prevDial = dials[prevIndex];
        
        //System.out.println(Arrays.toString(dials));
        return this;
    }
    public void mutate()
    {
        double offset[] = new double[20];
        int index = (int)(Math.random() * 20);
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
        //Chooses whether to add or subtract dial values
        if(operand == true){
            dials[index] += (dials[index]/100)*(mutationFactor * Math.log10(fitnessValue));
        }
        if(operand == false){
            dials[index] -= (dials[index]/100)*(mutationFactor * Math.log10(fitnessValue));
        }
        //Checks if the dial is between the range -5 to +5
        if(dials[index]>5 || dials[index] < -5){
            dials[index] = (int)(Math.random() * 10) -5;
        }
    }

    public double[] getValues()
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
    
    protected void setDial(int index, double value)
    {
        dials[index] = value;
    }
    public Candidate_Solution crossOver(Candidate_Solution b)
    {
        Candidate_Solution newSolution = new Candidate_Solution();
        double bValues [] = b.getValues();
        for(int dial=0; dial<20; dial++){
            if(dial%2==1) newSolution.setDial(dial, this.dials[dial]);
            if(dial%2==0) newSolution.setDial(dial, bValues[dial]);
        }
        
        return newSolution;
    }
    
    public void bigMutation()
    {
        if(Math.random()*10==1){
            mutationFactor = 4;
        }
    }
    
    public Boolean checkSimilarity(Candidate_Solution y, int similarities, int average)
    {
        
        return false;
    }
}
