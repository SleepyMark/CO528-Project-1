import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/**
 * Write a description of class Candidate_Solution here.
 *
 * @author Mark Cabrera (mc967)
 * @version 1.0.0
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
           fitnessValue = 99999;
           prevFitness = 99990;
           mutationFactor = 0;
    }
    public void generate()
    {
        for(int i=0; i<dials.length; i++){
            dials[i] = Math.random() * 10-5;
        }
        //System.out.println(Arrays.toString(dials));
    }
    public void mutate()
    {
        double prev [] = new double[20];
        System.arraycopy(dials, 0, prev, 0, 20);
        double offset[] = new double[20];
        int index = (int)(Math.random() * 20);
        int operand = (int)(Math.random() * 2);
        
        if(fitnessValue > prevFitness){
            dials[prevIndex] = prevDial;
        }
        prevIndex = index;
        prevDial = dials[index];
        if(operand == 1){
            dials[index] += (dials[index]/100)*((Math.random() * 10)*mutationFactor);
        }
        if(operand == 0){
            dials[index] -= (dials[index]/100)*(10*mutationFactor);
        }
        if(dials[index]>5 || dials[index] > -5){
            generateNewValue(index);
        }
        for(int i=0; i<dials.length; i++){
            offset[i] = prev[i]- dials[i];
        }
        //System.out.println(Arrays.toString(offset));
    }
    
    private void generateNewValue(int index)
    {
        dials[index] = (Math.random() * 10)-5;
    }
    public double[] getValues()
    {
        return dials;
    }
    
    public void setFitness(double fit)
    {
        prevFitness = fitnessValue;
        fitnessValue = fit;
        if(fitnessValue > 1000){
            mutationFactor = 1;
        }
        if(fitnessValue > 900){
            mutationFactor = 0.9;
        }
        if(fitnessValue > 800){
            mutationFactor = 0.8;
        }
        if(fitnessValue > 700){
            mutationFactor = 0.7;
        }
        if(fitnessValue > 600){
            mutationFactor = 0.6;
        }
        if(fitnessValue > 500){
            mutationFactor = 0.5;
        }
        if(fitnessValue > 400){
            mutationFactor = 0.4;
        }
        if(fitnessValue > 300){
            mutationFactor = 0.3;
        }
        if(fitnessValue > 200){
            mutationFactor = 0.2;
        }
        if(fitnessValue > 100){
            mutationFactor = 0.1;
        }
    }
   
     public double getFitness()
    {
        return this.fitnessValue;
    }
}
