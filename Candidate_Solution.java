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
    private float mutationFactor;
    private Random rd;
    /**
     * Constructor for objects of class Candidate_Solution
     */
    public Candidate_Solution()
    {
           dials =  new double[20];
           fitnessValue = 999;
           prevFitness = 1000;
           mutationFactor = 5;
           prevDial = 1;
           rd = new Random();
    }
    public Candidate_Solution generate()
    {
        for(int i=0; i<dials.length; i++){
            dials[i] = (Math.random() * 10)-5;
        }
        prevIndex = 99;
        return this;
    }
    public void mutate()
    {
        int index = (int)(Math.random() * (dials.length));
        boolean operand = rd.nextBoolean();
        
        
        if(fitnessValue > prevFitness){
            //If the fitness has moved away from convergence, then it will revert back to previous values
            dials[prevIndex] = prevDial;
        }
        //Stores the current values & index to the 'previous value'
        prevIndex = index;
        prevDial = dials[index];
        
        if(prevDial == 0){
            dials[prevIndex] = (Math.random() * 10) -5;
            
        }
        //If there is positive change, the mutation will still alter the previous dials value
        if(fitnessValue < prevFitness){
            index = prevIndex;
            prevIndex = index;
        }
        //Chooses whether to add or subtract dial values
        if(operand == true){
            dials[index] += (dials[index]/(100/Math.abs( Math.log(fitnessValue)/Math.log(2) )+( 2/Math.log10(fitnessValue) )))*((fitnessValue/(Math.random() * 100)) * Math.abs(Math.log(fitnessValue*prevFitness)));
        }
        if(operand == false){
            dials[index] -= (dials[index]/(100/Math.abs( Math.log(fitnessValue)/Math.log(2) )+( 2/Math.log10(fitnessValue) )))*((fitnessValue/(Math.random() * 100)) * Math.abs(Math.log(fitnessValue*prevFitness)));
        }
        //Checks if the dial is between the range -5 to +5
        if(Math.abs(dials[index]) > 5){
            dials[index] = (Math.random() * 10) -5;
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
            if(dial%2==0 || dial == 0 ) newSolution.setDial(dial, bValues[dial]);
        }
        
        return newSolution;
    }
    
    public void bigMutation()
    {
        if(Math.random()*100==1){
            mutationFactor = 4;
        }
    }
    
    public Boolean checkSimilarity(Candidate_Solution y, int similarities, int average)
    {
        
        return false;
    }
    
    public Boolean checkIfZero()
    {
        for(double a: dials) if(a==0) return true;
        return false;
    }
}
