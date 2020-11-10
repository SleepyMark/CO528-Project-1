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
    //Stores the dial values
    private double dials[];
    //Stores the candidates fitness value
    private double fitnessValue;
    //Stores the candidates previous fitness value
    private double prevFitness;
    //Stores the previous dials value
    private double prevDial;
    //Stores the previous dial index
    private int prevIndex;
    private Random rd;
    /**
     * Initalises the variables 
     */
    public Candidate_Solution()
    {
           dials =  new double[20];
           fitnessValue = 999;
           prevFitness = 1000;
           prevDial = 1;
           rd = new Random();
    }
    /**
     * Generates new dial value, for the candidate solution
     * @return The instance of this Solution
     */
    public Candidate_Solution generate()
    {
        for(int i=0; i<dials.length; i++) dials[i] = (Math.random() * 10)-5;
        prevIndex = 99;
        return this;
    }
    /**
     * This method mutates this solution, and is somewhat 'smart'
     * When the fitness value is high then the mutationRate, or 'accuracy' is very low, aka very big jumps
     * When the fitness value is low then the mutationRate, or 'accuracy' is very high, aka very small jumps
     */
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
        
        if(prevDial == 0) dials[prevIndex] = (Math.random() * 10) -5;

        //If there is positive change, the mutation will still alter the previous dials value
        if(fitnessValue < prevFitness){
            index = prevIndex;
            prevIndex = index;
        }
        
        //Chooses whether to add or subtract dial values
        //Used when: fitness high(bad) = high mutation && fitness low(good) = low mutation
        //Basic idea: d[i] = mutationValue(m) * mutationRate(offset)
        //Finding the right formula for this is hard, as lim(f->0), logE->-Infinity. That's why it was so hard for me to go past 1e-10, as mutations went from big leaps, to smaller leaps, then back to bigger leaps
        //It was also kinda frustrating, as I tried to find something else that worked. I spent 3-8 versions trying to find one that worked, but was left this original formula:
        if(operand == true) dials[index] += (dials[index]/(100/(Math.log(fitnessValue)/Math.log(3))))*((fitnessValue/(Math.random() * 100)) * (Math.log(fitnessValue*prevFitness)/Math.log(3)));
        if(operand == false) dials[index] -= (dials[index]/(100/(Math.log(fitnessValue)/Math.log(3))))*((fitnessValue/(Math.random() * 100)) * (Math.log(fitnessValue*prevFitness)/Math.log(3)));
        
        //Checks if the dial is between the range -5 to +5
        if(Math.abs(dials[index]) > 5){
            dials[index] = (Math.random() * 10) -5;
        }
        
    }
    /**
     * @return The 20 dial inputs of the candidate
     */
    public double[] getValues()
    {
        return dials;
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
     * Sets the dial value at the given index
     * @index The dial which is changed (0-20)'
     * @value The value wanting to be written0in
     */
    protected void setDial(int index, double value)
    {
        dials[index] = value;
    }
    /**
     * This solution crosses over the current & a given solution, in an alternating pattern
     * @return Crossover of the two solutions
     */
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
    

}
