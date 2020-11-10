import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/**
 * This class is very similar to the Population class, only difference is that it deals with booleans instead of doubles
 *
 * @author (Mark Cabrera)
 * @version (5.2.5)
 */
public class Luggage_Population
{
    private ArrayList <Luggage_Candidate> items;
    private double highest;

    /**
     * Initalises values for the class, and randomly generates @size solutions
     * @size Number of solutions
     */
    public Luggage_Population(int size)
    {
        highest = 0;
        items = new ArrayList<Luggage_Candidate>();
        for(int i=0; i<size; i++) items.add(new Luggage_Candidate());
    }
    /**
     * Returns an array containing all of the candiate solutions
     * @return Population of all the items
     */
    public ArrayList<Luggage_Candidate> getCandidates()
    {
        return this.items;
    }
    /**
     * Resets the highest fitness value to very low
     */
    public void resetHighest()
    {
        this.highest = 0;
    }
    
    /**
     * Sets & checks if the @x is the highest fitting value
     * @return True if the highest & x is to be the highest fitting value, else false
     */
    public boolean checkIfHighest(double x)
    {
        if(x > this.highest){
            highest = x;
            return true;
        }
        return false;
    }

    /**
     * @return The highest value
     */
    public double getHighest()
    {
        return this.highest;
    }
    /**
     * Returns the sum of all the fitness values candidates in the population
     * @return Sum of fitness for all candidates
     */
    public double getTotalFitness()
    {
        double total=0;
        for(Luggage_Candidate a : items){
            total += a.getFitness();
        }
        return total;
    }
    /**
     * Returns the total fitness of all the given candidates
     * @candidateArray Number of candidates wanted to be summed
     * @return Sum of fitness of of Luggage Solutions
     */
    private double getTotalFitness(ArrayList<Luggage_Candidate> candidateArray)
    {
        double total=0;
        for(Luggage_Candidate a : candidateArray){
            total += a.getFitness();
        }
        return total;
    }
    /**
     * Returns the best inputs of all the candidates, to give the best fitnes value
     * @return Array of booleans, whihc give the best fitness value
     */
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
    
    /**
     * Get the best candidate out of the whole population
     * @return Best Luggage_solution
     */
    public Luggage_Candidate getBestCandidate()
    {
        double num = 0;
        Luggage_Candidate out = null;
        for(Luggage_Candidate l : items){
            if(l.getWeight() < 500){
                if(l.getFitness() > num){
                    num = l.getFitness();
                    out = l;
                }
            }
        }
        return out;
    }

    /**
     * Returns solutions that are 'better' than the other candidates
     * @return Array of size @returnSize of the winning candidates
     */
    public ArrayList<Luggage_Candidate> championOperator(int returnSize)
    {
        ArrayList<Luggage_Candidate> players = (ArrayList<Luggage_Candidate>)items.clone();
        ArrayList<Luggage_Candidate> remainingPlayers = players;
        ArrayList<Luggage_Candidate> out = new ArrayList<Luggage_Candidate>();

        //Continues until the needed amount of champions are met
        for(int i=0; i<returnSize; i++){
            //Stores the candidates that haven't won
            remainingPlayers = (ArrayList<Luggage_Candidate>)players.clone();
            while(remainingPlayers.size()!=1){
                //Removed the worst fitting candidates. Continues until there is one candidate left
                for(int loop=1; loop<=remainingPlayers.size()-1; loop++){
                    if(remainingPlayers.get(loop).getFitness() < remainingPlayers.get(loop-1).getFitness()){
                        remainingPlayers.remove(loop);
                    } else {
                        remainingPlayers.remove(loop-1);
                    }
                }
            }
            //Winning candidate is removed from the gladitorial players, and added to the output
            out.add(remainingPlayers.get(0));
            players.remove(remainingPlayers.get(0));
        }

        return out;
    }
    /**
     * Returns random solutions from the population. Two solutions are chosen at random, and then randomly chosen
     * @return Array of 'winning' solutions
     */
    public ArrayList<Luggage_Candidate> randomChoiceOperator(int returnSize)
    {
        //Very similar to the championOperator()
        ArrayList<Luggage_Candidate> players = (ArrayList<Luggage_Candidate>)items.clone();
        ArrayList<Luggage_Candidate> remainingPlayers;
        ArrayList<Luggage_Candidate> out = new ArrayList<Luggage_Candidate>();
        int num;

        for(int i=0; i<returnSize; i++){
            while(players.size()!=1){
                remainingPlayers = (ArrayList<Luggage_Candidate>)players.clone();

                for(int loop=1; i<remainingPlayers.size()/2; i++){
                    num = (int)(Math.random()*2);
                    if(num==0){
                        remainingPlayers.remove(loop-1);
                    }
                    if(num==1){
                        remainingPlayers.remove(loop);
                    }
                }
                out.add(remainingPlayers.get(0));
                players.remove(remainingPlayers.get(0));
            }
        }

        return out;
    }

    /**
     * Chooses solutions, competly based on luck. Candidates can be chosen moer than once
     * @return ArrayList of chosen candidates
     */
    public ArrayList<Luggage_Candidate> chooseRandom(int returnSize, ArrayList<Luggage_Candidate> choices)
    {
        ArrayList<Luggage_Candidate> out = new ArrayList<Luggage_Candidate>();
        for(int loop=0; loop<returnSize; loop++) out.add(choices.get((int)(Math.random() * choices.size())));
        return out;
    }

    /**
     * Empties the popluation ArrayList
     */
    public void emptyPopulation()
    {
        items.clear();
    }
    /**
     * Adds candidate to the population & is checked whether or it contains the best fitness value
     */
    public void addCandidate(Luggage_Candidate a)
    {
        items.add(a);
        if(a.getFitness() > this.highest){
            highest = a.getFitness();
        }
    }
    /**
     * Adds candidates to the population & all are checked if they have a higher fitness, than the current population
     */
    public void addCandidates(ArrayList<Luggage_Candidate> newCandidates)
    {
        for(Luggage_Candidate a : newCandidates){
            items.add(a);
            if(a.getFitness() > this.highest){
                highest = a.getFitness();
            }
        }

    }
    /**
     * Mutates all of the candidates in the population
     */
    public void mutatePopulation()
    {
        for(Luggage_Candidate a : items){
            a.mutate();
        }
    }
    /**
     * Sets tnew population
     * @newPopulation Population to replace current population
     */
    public void setPopulation(ArrayList<Luggage_Candidate> newPopulation)
    {
        items.addAll(newPopulation);
    }
    /**
     * This method removes or reverts candidate solutions that are overmeet the criteria
     * @return No. of candidates removed from the population
     */
    public int killOff()
    {
        ArrayList <Luggage_Candidate> toRemove =  new ArrayList<Luggage_Candidate>();
        //If the solution greatly overmeets the critera, then they are eliminiated
        for(Luggage_Candidate b : items)if(b.getWeight() > 550)toRemove.add(b);
        //If the solution slightly overmeets the critera, then they reverted back to their previous mutated-state
        for(Luggage_Candidate a : items) if(a.getWeight() > 500)a.revert(); 
        //All the items that are >550 are removed
        for(Luggage_Candidate c : toRemove) items.remove(c);
        //Returns the number of candidates removed
        return toRemove.size();
    }
    /**
     * Method crosses over random solutions in the population
     * @return ArrayList of crossed-over solutions
     */
    public ArrayList<Luggage_Candidate> crossOver(int size, ArrayList<Luggage_Candidate> choices)
    {
        ArrayList<Luggage_Candidate> out = new ArrayList<Luggage_Candidate>();
        for(int loop=0; loop<size; loop++){
            out.add(choices.get((int)(Math.random() * choices.size())).crossOver(choices.get((int)(Math.random() * choices.size()))));
        }
        return out;
    }
}
