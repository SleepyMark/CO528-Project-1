import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/**
 * Write a description of class Population here.
 *
 * @author Mark Cabrera (mc967)
 * @version 2.0.00
 */
public class Population
{
    private ArrayList <Candidate_Solution> population;
    private double lowest;
    /**
     * Constructor for objects of class Population
     */
    public Population(int size)
    {
        lowest = 99999;
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
    
    public double getTotalFitness()
    {
        double total=0;
        for(Candidate_Solution a : population){
            total += a.getFitness();
        }
        return total;
    }
    
    private double getTotalFitness(ArrayList<Candidate_Solution> candidateArray)
    {
        double total=0;
        for(Candidate_Solution a : candidateArray){
            total += a.getFitness();
        }
        return total;
    }
    public double[] getBestFit()
    {
        double num = 9999;
        Candidate_Solution out = null;
        for(Candidate_Solution a : population){
            if(a.getFitness()< num){
                num = a.getFitness();
                out = a;
            }
        }
        return out.getValues();
    }
    /**
     * 
     * Choose 10/20 solutions championed, 5/10 solutions mutated & 5/10 solutions elited 
     */
    /**
     * This method returns solutions that have dominated over other candidate solutions
     */
    public ArrayList<Candidate_Solution> championOperator(int returnSize)
    {
        ArrayList<Candidate_Solution> temp = (ArrayList<Candidate_Solution>)population.clone();
        ArrayList<Candidate_Solution> temp2;
        ArrayList<Candidate_Solution> out = new ArrayList<Candidate_Solution>();
        
        Candidate_Solution a;
        Candidate_Solution b;
        for(int i=0; i<returnSize-1; i++){
            while(temp.size()!=1){
                temp2 = (ArrayList<Candidate_Solution>)temp.clone();
                
                for(int loop=1; i<temp2.size()/2; i++){
                    if(temp2.get(loop).getFitness() > temp2.get(loop-1).getFitness()){
                        temp.remove(loop-1);
                    }
                    if(temp2.get(loop).getFitness() < temp2.get(loop-1).getFitness()){
                        temp.remove(loop);
                    }
                }
                out.add(temp2.get(0));
                temp.remove(temp2.get(0));
            }
        }
        
        return out;
    }
    
    public ArrayList<Candidate_Solution> chooseRandom(int size, ArrayList<Candidate_Solution> choices)
    {
        ArrayList<Candidate_Solution> out = new ArrayList<Candidate_Solution>();
        for(int loop=0; loop<size-1; loop++){
            out.add(choices.get((int)(Math.random() * choices.size())));
        }
        return out;
    }
    public void emptyPopulation()
    {
        population = null;
    }
    
    public void addCandidate(Candidate_Solution a)
    {
        population.add(a);
        if(a.getFitness() < this.lowest){
            lowest = a.getFitness();
        }
    }
    
    public void addCandidates(ArrayList<Candidate_Solution> newCandidates)
    {
        population.addAll(newCandidates);
    }
    public void mutatePopulation()
    {
        for(Candidate_Solution a : population){
            a.mutate();
        }
    }
    
    public void setPopulation(ArrayList<Candidate_Solution> newPopulation)
    {
        population = newPopulation;
    }
    
    public ArrayList<Candidate_Solution> crossOver(int size, ArrayList<Candidate_Solution> choices)
    {
        ArrayList<Candidate_Solution> out = new ArrayList<Candidate_Solution>();
        for(int loop=0; loop<size-1; loop++){
            out.add(choices.get((int)(Math.random() * choices.size())).crossOver(choices.get((int)(Math.random() * choices.size()))));
        }
        return out;
    }
}
