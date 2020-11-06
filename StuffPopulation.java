import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
/**
 * Write a description of interface StuffPopulation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface StuffPopulation
{
    public ArrayList<Candidates> generate(int size);
    public ArrayList<Candidates> generateBalanced(int size, int min, int max);
    public ArrayList<Candidates> getCandidates();
    public void resetLowest();
    public Boolean checkIfLowest(double x);
    public double getLowest();
    public double getTotalFitness();
    public double getTotalFitness(ArrayList<Candidates> candidateArray);
    public double[] getBestFit();
    public ArrayList<Candidates> championOperator(int returnSize);
    public ArrayList<Candidates> championRandomOperator(int returnSize);
    public ArrayList<Candidates> randomChoiceOperator(int returnSize);
    public ArrayList<Candidates> chooseRandom(int size, ArrayList<Candidates> choices);
    public void emptyPopulation();
    public void addCandidate(Candidates a);
    public void addCandidates(ArrayList<Candidates> newCandidates);
    public void mutatePopulation();
    public void setPopulation(ArrayList<Candidates> newPopulation);
    public ArrayList<Candidates> crossOver(int size, ArrayList<Candidates> choices);


}
