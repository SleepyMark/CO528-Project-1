import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/**
 * Write a description of class Population here.
 *
 * @author Mark Cabrera (mc967)
 * @version 1.0.0
 */
public class Population
{
    // instance variables - replace the example below with your own
    private ArrayList <Candidate_Solution> population;
    private double lowest;
    /**
     * Constructor for objects of class Population
     */
    public Population()
    {
        lowest = 0;
    }
    
    public void populateAndGenerate(int size)
    {
        population = new ArrayList<Candidate_Solution>(size);
        Candidate_Solution a;
        for(int i=0; i<size-1; i++){
            a = new Candidate_Solution();
            a.generate();
            population.add(a);
        }
    }
    
    public ArrayList<Candidate_Solution> getCandidates()
    {
        return this.population;
    }
    
    public void resetLowest()
    {
        lowest = 99999;
    }
    
    public Boolean checkIfLowest(double x)
    {
        if(x < lowest){
            lowest = x;
            return true;
        }
        return false;
    }
    
    public double getLowest()
    {
        return this.lowest;
    }
    /**
     * 
     * Choose 10/20 solutions championed, 5/10 solutions mutated & 5/10 solutions elited 
     */
}
