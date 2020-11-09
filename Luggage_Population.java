import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/**
 * Write a description of class Luggage_Population here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Luggage_Population
{
    private ArrayList <Luggage_Candidate> items;
    private double highest;

    public Luggage_Population(int size)
    {
        highest = 0;
        items = new ArrayList<Luggage_Candidate>();
        for(int i=0; i<size; i++) items.add(new Luggage_Candidate());
    }

    public ArrayList<Luggage_Candidate> getCandidates()
    {
        return this.items;
    }

    public void resetHighest()
    {
        this.highest = 0;
    }

    public boolean checkIfHighest(double x)
    {
        if(x > this.highest){
            highest = x;
            return true;
        }
        return false;
    }

    public double getHighest()
    {
        return this.highest;
    }

    public double getTotalFitness()
    {
        double total=0;
        for(Luggage_Candidate a : items){
            total += a.getFitness();
        }
        return total;
    }

    private double getTotalFitness(ArrayList<Luggage_Candidate> candidateArray)
    {
        double total=0;
        for(Luggage_Candidate a : candidateArray){
            total += a.getFitness();
        }
        return total;
    }

    public boolean[] getBestFit()
    {
        double num = 0;
        Luggage_Candidate out = null;
        for(Luggage_Candidate a : items){
            if(a.getFitness() > num){
                num = a.getFitness();
                out = a;
            }
        }
        return out.getValues();
    }
    
    public Luggage_Candidate getBestCandidate()
    {
        double num = 0;
        Luggage_Candidate out = null;
        for(Luggage_Candidate l : items){
            if(l.getFitness() > num && l.getWeight() < 500){
                num = l.getFitness();
                out = l;
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
    public ArrayList<Luggage_Candidate> championOperator(int returnSize)
    {
        ArrayList<Luggage_Candidate> A = (ArrayList<Luggage_Candidate>)items.clone();
        ArrayList<Luggage_Candidate> B = A;
        ArrayList<Luggage_Candidate> out = new ArrayList<Luggage_Candidate>();

        for(int i=0; i<returnSize; i++){
            B = (ArrayList<Luggage_Candidate>)A.clone();
            while(B.size()!=1){
                for(int loop=1; loop<=B.size()-1; loop++){
                    if(B.get(loop).getFitness() < B.get(loop-1).getFitness()){
                        B.remove(loop);
                    } else {
                        B.remove(loop-1);
                    }
                }
            }
            out.add(B.get(0));
            A.remove(B.get(0));
        }

        return out;
    }

    public ArrayList<Luggage_Candidate> randomChoiceOperator(int returnSize)
    {
        ArrayList<Luggage_Candidate> temp = (ArrayList<Luggage_Candidate>)items.clone();
        ArrayList<Luggage_Candidate> temp2;
        ArrayList<Luggage_Candidate> out = new ArrayList<Luggage_Candidate>();
        int num;

        for(int i=0; i<returnSize; i++){
            while(temp.size()!=1){
                temp2 = (ArrayList<Luggage_Candidate>)temp.clone();

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

    public ArrayList<Luggage_Candidate> chooseRandom(int returnSize, ArrayList<Luggage_Candidate> choices)
    {
        ArrayList<Luggage_Candidate> out = new ArrayList<Luggage_Candidate>();
        for(int loop=0; loop<returnSize; loop++) out.add(choices.get((int)(Math.random() * choices.size())));
        return out;
    }

    public void emptyPopulation()
    {
        items.clear();
    }

    public void addCandidate(Luggage_Candidate a)
    {
        items.add(a);
        if(a.getFitness() > this.highest){
            highest = a.getFitness();
        }
    }

    public void addCandidates(ArrayList<Luggage_Candidate> newCandidates)
    {
        for(Luggage_Candidate a : newCandidates){
            items.add(a);
            if(a.getFitness() > this.highest){
                highest = a.getFitness();
            }
        }

    }

    public void mutatePopulation()
    {
        for(Luggage_Candidate a : items){
            a.mutate();
        }
    }

    public void setPopulation(ArrayList<Luggage_Candidate> newPopulation)
    {
        items.addAll(newPopulation);
    }
    
    public int killOff()
    {
        int initialSize = items.size();
        ArrayList <Luggage_Candidate> toRemove =  new ArrayList<Luggage_Candidate>();
        for(Luggage_Candidate b : items)if(b.getWeight() > 550)toRemove.add(b);
        for(Luggage_Candidate a : items) if(a.getWeight() > 500)a.revert(); 
        for(Luggage_Candidate c : toRemove) items.remove(c);
     
        return initialSize - items.size();
    }

    public ArrayList<Luggage_Candidate> crossOver(int size, ArrayList<Luggage_Candidate> choices)
    {
        ArrayList<Luggage_Candidate> out = new ArrayList<Luggage_Candidate>();
        for(int loop=0; loop<size; loop++){
            out.add(choices.get((int)(Math.random() * choices.size())).crossOver(choices.get((int)(Math.random() * choices.size()))));
        }
        return out;
    }
}
