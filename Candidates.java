import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
/**
 * Write a description of interface Candidates here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface Candidates
{
    public Candidates generate();
    public void mutate();
    public List<?>[] getValues();
    public void setFitness(double fit);
    public double getFitness();
    public void set(int index, double value);
    public Candidates crossOver(Candidates b);
    public void setAll(int value);
    public void bigMutation();
    public Boolean checkSimilarity(Candidates y, int similarities, int average);
}
