import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/**
 * Write a description of class Population here.
 *
 * @author Mark Cabrera (mc967)
 * @version 2.0.00
 */
public class Population implements StuffPopulation
{
    private ArrayList <Candidates> population;
    private double lowest;
    /**
     * Constructor for objects of class Population
     */
    public Population()
    {
        lowest = 999;
        
    }

    public ArrayList<Candidates> generate(int size)
    {
        ArrayList<Candidates> p = new ArrayList<Candidates>(size);
        Candidates a;
        for(int i=0; i<size-1; i++){
            a = new Candidate_Solution();
            a.generate();
            p.add(a);
        }
        return p;
    }
    public ArrayList<Candidates> generateBalanced(int size, int min, int max)
    {
        ArrayList<Candidates> p = new ArrayList<Candidates>(size);
        Candidates a;
        int inMin = Math.abs(min);
        int inMax = Math.abs(max);
        int space = (inMax + inMax) / size;
        for(int i=0; i<size-1; i++){
            a = new Candidate_Solution();
            a.setAll(min + (space * i));
            p.add(a);
        }
        return p;
    }
    public ArrayList<Candidates> getCandidates()
    {
        return this.population;
    }
    
    public void resetLowest()
    {
        lowest = 999;
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
        for(Candidates a : population){
            total += a.getFitness();
        }
        return total;
    }
    
    public double getTotalFitness(ArrayList<Candidates> candidateArray)
    {
        double total=0;
        for(Candidates a : candidateArray){
            total += a.getFitness();
        }
        return total;
    }
    
    public Candidates getBestFit()
    {
        double num = 9999;
        Candidates out = null;
        for(Candidates a : population){
            if(a.getFitness()< num){
                num = a.getFitness();
                out = a;
            }
        }
        return out;
    }
    /**
     * 
     * Choose 10/20 solutions championed, 5/10 solutions mutated & 5/10 solutions elited 
     */
    /**
     * This method returns solutions that have dominated over other candidate solutions
     */
    public ArrayList<Candidates> championOperator(int returnSize)
    {
        ArrayList<Candidates> temp = (ArrayList<Candidates>)population.clone();
        ArrayList<Candidates> temp2;
        ArrayList<Candidates> out = new ArrayList<Candidates>();
        
        Candidates a;
        Candidates b;
        for(int i=0; i<returnSize-1; i++){
            while(temp.size()!=1){
                temp2 = (ArrayList<Candidates>)temp.clone();
                
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
    
    public ArrayList<Candidates> championRandomOperator(int returnSize)
    {
        ArrayList<Candidates> temp = (ArrayList<Candidates>)population.clone();
        ArrayList<Candidates> temp2;
        ArrayList<Candidates> out = new ArrayList<Candidates>();
        
        Candidates a;
        Candidates b;
        int fighter1;
        int fighter2;
        for(int i=0; i<returnSize-1; i++){
            while(temp.size()!=1){
                temp2 = (ArrayList<Candidates>)temp.clone();
                fighter1 = (int)(Math.random() * temp2.size());
                fighter2 = (int)(Math.random() * temp2.size());
                if(fighter1 == fighter2) fighter2 = (int)(Math.random() * temp2.size());
                for(int loop=1; i<temp2.size()/2; i++){
                    if(temp2.get(fighter1).getFitness() > temp2.get(fighter2-1).getFitness()){
                        temp.remove(loop-1);
                    }
                    if(temp2.get(fighter1).getFitness() < temp2.get(fighter2-1).getFitness()){
                        temp.remove(loop);
                    }
                }
                out.add(temp2.get(0));
                temp.remove(temp2.get(0));
            }
        }
        
        return out;
    }
    
    public ArrayList<Candidates> randomChoiceOperator(int returnSize)
    {
        ArrayList<Candidates> temp = (ArrayList<Candidates>)population.clone();
        ArrayList<Candidates> temp2;
        ArrayList<Candidates> out = new ArrayList<Candidates>();
        int num;
        
        for(int i=0; i<returnSize-1; i++){
            while(temp.size()!=1){
                temp2 = (ArrayList<Candidates>)temp.clone();
                
                for(int loop=1; i<temp2.size()/2; i++){
                    num = (int)(Math.random()*2);
                    if(num==0){
                        temp.remove(loop-1);
                    }
                    if(num==1){
                        temp.remove(loop);
                    }
                }
                out.add(temp2.get(0));
                temp.remove(temp2.get(0));
            }
        }
        
        return out;
    }
    
    public ArrayList<Candidates> chooseRandom(int size, ArrayList<Candidates> choices)
    {
        ArrayList<Candidates> out = new ArrayList<Candidates>();
        for(int loop=0; loop<size-1; loop++){
            out.add(choices.get((int)(Math.random() * choices.size())));
        }
        return out;
    }
    public void emptyPopulation()
    {
        population = null;
    }
    
    public void addCandidate(Candidates a)
    {
        population.add(a);
        if(a.getFitness() < this.lowest){
            lowest = a.getFitness();
        }
    }
    
    public void addCandidates(ArrayList<Candidates> newCandidates)
    {
        population.addAll(newCandidates);
        for(Candidates a : newCandidates){
            if(a.getFitness() < this.lowest){
            lowest = a.getFitness();
        }
        }
        
    }
    public void mutatePopulation()
    {
        for(Candidates a : population){
            a.mutate();
        }
    }
    public void setPopulation(ArrayList<Candidates> newPopulation)
    {
        population = newPopulation;
    }
    
    public ArrayList<Candidates> crossOver(int size, ArrayList<Candidates> choices)
    {
        ArrayList<Candidates> out = new ArrayList<Candidates>();
        for(int loop=0; loop<size-1; loop++){
            out.add(choices.get((int)(Math.random() * choices.size())).crossOver(choices.get((int)(Math.random() * choices.size()))));
        }
        return out;
    }
}
