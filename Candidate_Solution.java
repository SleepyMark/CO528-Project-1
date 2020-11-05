import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
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
    /**
     * Constructor for objects of class Candidate_Solution
     */
    public Candidate_Solution()
    {
           dials =  new double[20];
           fitnessValue = 99;
           prevFitness = 1000;
           mutationFactor = 0;
    }
    public void generate()
    {
        for(int i=0; i<dials.length; i++){
            dials[i] = (Math.random() * 10)-5;
        }
        prevDial = dials[0];
        //System.out.println(Arrays.toString(dials));
    }
    public void mutate()
    {
        double offset[] = new double[20];
        int index = (int)(Math.random() * 20);
        int operand = (int)(Math.random() * 2);
        
        
        if(fitnessValue > prevFitness){
            //If the fitness has moved away from convergence, then it will revert back to previous values
            dials[prevIndex] = prevDial;
        }
        //Stores the current values & index to the 'previous value'
        prevIndex = index;
        prevDial = dials[index];
        //If there is positive change, the mutation will still alter the previous dials value
        if(fitnessValue < prevFitness){
            index = prevIndex;
            prevIndex = index;
        }
        //Chooses whether to add or subtract dial values
        if(operand == 1){
            dials[index] += (dials[index]/100)*((Math.random() * 10)*mutationFactor);
        }
        if(operand == 0){
            dials[index] -= (dials[index]/100)*(10*mutationFactor);
        }
        //Checks if the dial is between the range -5 to +5
        if(dials[index]>5 || dials[index] < -5){
            dials[index] = (Math.random() * 10)-5;
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
        if(fitnessValue < 1000){
            mutationFactor = 1;
        }
        if(fitnessValue < 900){
            mutationFactor = 0.9;
        }
        if(fitnessValue < 800){
            mutationFactor = 0.8;
        }
        if(fitnessValue < 700){
            mutationFactor = 0.7;
        }
        if(fitnessValue < 600){
            mutationFactor = 0.6;
        }
        if(fitnessValue < 500){
            mutationFactor = 0.5;
        }
        if(fitnessValue < 400){
            mutationFactor = 0.4;
        }
        if(fitnessValue < 300){
            mutationFactor = 0.3;
        }
        if(fitnessValue < 200){
            mutationFactor = 0.2;
        }
        if(fitnessValue < 100){
            mutationFactor = 0.1;
        }
        if(fitnessValue < 50){
            mutationFactor = 0.05;
        }
        if(fitnessValue < 25){
            mutationFactor = 0.025;
        }
        if(fitnessValue < 15){
            mutationFactor = 0.01;
        }
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
        for(int dial=0; dial<19; dial++){
            //if(dial<=9) newSolution.setDial(dial, dials[dial]);
            //if(dial>=10) newSolution.setDial(dial, bValues[dial]);
            if(dial%2==0) newSolution.setDial(dial, dials[dial]);
            if(dial%2==1) newSolution.setDial(dial, bValues[dial]);
        }
        
        return newSolution;
    }
}
