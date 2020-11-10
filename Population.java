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
    //Stores the candidiate solutions
    private ArrayList <Candidate_Solution> population;
    //Stores the lowest fitness value
    private double lowest;
    /**
     * Initalises values for the class, and randomly generates @size solutions
     * @size Number of solutions
     */
    public Population(int size)
    {
        lowest = 999;
        population = new ArrayList<Candidate_Solution>(size);
        for(int i=0; i<size; i++) population.add(new Candidate_Solution().generate());

    }
    /**
     * Returns an array containing all of the canddiate solutions
     * @return Population of all the candidates
     */
    public ArrayList<Candidate_Solution> getCandidates()
    {
        return this.population;
    }
    
    /**
     * Resets the lowest fitness value to very high
     */
    public void resetLowest()
    {
        lowest = 999;
    }
    
    /**
     * Sets & checks if the @x is the lowest fitting value
     * @return True if the lowest & x is to be the lowest fitting value, else false
     */
    public Boolean checkIfLowest(double x)
    {
        if(x < lowest){
            lowest = x;
            return true;
        }
        return false;
    }
    /**
     * @return The lowest value
     */
    public double getLowest()
    {
        return this.lowest;
    }
    
    /**
     * Get the best candidate out of the whole population
     * @return Best Candidate_Solution
     */
    public Candidate_Solution getBestCandidate()
    {
        double num = 9999;
        Candidate_Solution out = null;
        for(Candidate_Solution c : population){
                if(c.getFitness() < num){
                    num = c.getFitness();
                    out = c;
            }
        }
        return out;
    }
    /**
     * Returns the sum of all the fitness values candidates in the population
     * @return Sum of fitness for all candidates
     */
    public double getTotalFitness()
    {
        double total=0;
        for(Candidate_Solution a : population){
            total += a.getFitness();
        }
        return total;
    }
    /**
     * Returns the total fitness of all the given candidates
     * @candidateArray Number of candidates wanted to be summed
     * @return Sum of fitness of of Candidate Solutions
     */
    private double getTotalFitness(ArrayList<Candidate_Solution> candidateArray)
    {
        double total=0;
        for(Candidate_Solution a : candidateArray){
            total += a.getFitness();
        }
        return total;
    }
    /**
     * Returns the best inputs of all the candidates, to give the best fitnes value
     * @return Array of Doubles, whihc give the best fitness value
     */
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
     * Returns solutions that are 'better' than the other candidates
     * @return Array of size @returnSize of the winning candidates
     */
    public ArrayList<Candidate_Solution> championOperator(int returnSize)
    {
        ArrayList<Candidate_Solution> players = (ArrayList<Candidate_Solution>)population.clone();
        ArrayList<Candidate_Solution> remainingPlayers = players;
        ArrayList<Candidate_Solution> out = new ArrayList<Candidate_Solution>();

        //Continues until the needed amount of champions are met
        for(int i=0; i<returnSize; i++){
            //Stores the candidates that haven't won
            remainingPlayers = (ArrayList<Candidate_Solution>)players.clone();
            while(remainingPlayers.size()!=1){
                //Removed the worst fitting candidates. Continues until there is one candidate left
                for(int loop=1; loop<=remainingPlayers.size()-1; loop++){
                    if(remainingPlayers.get(loop).getFitness() > remainingPlayers.get(loop-1).getFitness()){
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
    public ArrayList<Candidate_Solution> randomChoiceOperator(int returnSize)
    {
        ArrayList<Candidate_Solution> players = (ArrayList<Candidate_Solution>)population.clone();
        ArrayList<Candidate_Solution> remainingPlayers;
        ArrayList<Candidate_Solution> out = new ArrayList<Candidate_Solution>();
        int num;

        for(int i=0; i<returnSize; i++){
            while(players.size()!=1){
                remainingPlayers = (ArrayList<Candidate_Solution>)players.clone();

                for(int loop=1; i<remainingPlayers.size()/2; i++){
                    num = (int)(Math.random()*2);
                    if(num==0){
                        players.remove(loop-1);
                    }
                    if(num==1){
                        players.remove(loop);
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
    public ArrayList<Candidate_Solution> chooseRandom(int returnSize, ArrayList<Candidate_Solution> choices)
    {
        ArrayList<Candidate_Solution> out = new ArrayList<Candidate_Solution>();
        for(int loop=0; loop<returnSize; loop++) out.add(choices.get((int)(Math.random() * choices.size())));
        return out;
    }
    /**
     * Empties the popluation ArrayList
     */
    public void emptyPopulation()
    {
        population.clear();
    }
    /**
     * Adds candidate to the population & is checked whether or it contains the best fitness value
     */
    public void addCandidate(Candidate_Solution a)
    {
        population.add(a);
        if(a.getFitness() < this.lowest){
            lowest = a.getFitness();
        }
    }
    /**
     * Adds candidates to the population & all are checked if they have a lowerer fitness, than the current population
     */
    public void addCandidates(ArrayList<Candidate_Solution> newCandidates)
    {
        for(Candidate_Solution a : newCandidates){
            population.add(a);
            if(a.getFitness() < this.lowest){
                lowest = a.getFitness();
            }
        }

    }
    /**
     * Mutates all of the candidates in the population
     */
    public void mutatePopulation()
    {
        for(Candidate_Solution a : population) a.mutate();
    }
    /**
     * Sets tnew population
     * @newPopulation Population to replace current population
     */
    public void setPopulation(ArrayList<Candidate_Solution> newPopulation)
    {
        population.addAll(newPopulation);
    }
    /**
     * Method crosses over random solutions in the population
     * @return ArrayList of crossed-over solutions
     */
    public ArrayList<Candidate_Solution> crossOver(int size, ArrayList<Candidate_Solution> choices)
    {
        ArrayList<Candidate_Solution> out = new ArrayList<Candidate_Solution>();
        for(int loop=0; loop<size; loop++){
            out.add(choices.get((int)(Math.random() * choices.size())).crossOver(choices.get((int)(Math.random() * choices.size()))));
        }
        return out;
    }
    
}
