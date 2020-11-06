//This is my example Solution
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
class Example {
    public static void main(String[] args) {
        //Do not delete/alter the next line
        long startT = System.currentTimeMillis();

        //Edit this according to your name and login
        String name = "Mark Cabrera";
        String login = "MC967";

        System.out.println("These are the instructions of how to use the problem library.  Please make sure you follow the instructions carefully.");

        System.out.println("For the first problem, you need to use Assess.getTest1(double[]).");
        //An example solution consists of an array  of doubles of size 20 
        //Allowed values are between -5 and +5 (this is not actually checked, but no point in going beyond these values)
        //Lower fitness is better. The optimal fitness is 0 (i.e. no negative fitness values). 
        /*int solSize = 1;
        //generate a sample solution like so:

        double[] sol1 = new double[solSize];
        for (int j = 0; j < solSize; j++) {
        sol1[j] = Math.random() * Math.round(5.12 * (Math.random() - Math.random()));
        }*/

        //My solution
        int size = 500;      
        double [] candidates = new double[size];
        Population p = new Population(size);
        
        ArrayList<Candidate_Solution> currentPopulation;
        ArrayList<Candidate_Solution> newPopulation = new ArrayList<Candidate_Solution>();
        ArrayList<Candidate_Solution> tempPopulation = new ArrayList<Candidate_Solution>();
        Candidate_Solution A;
        double num;
        int loop=1;
        
        for(int i=0; i<5000; i++){
            
            p.resetLowest();
            for(Candidate_Solution n : p.getCandidates()){
                //System.out.println("Before: " + Arrays.toString(n.getValues()));
                num = Assess.getTest1(n.getValues());
                n.setFitness(num);
                p.checkIfLowest(num);
                //System.out.println("After: " + Arrays.toString(n.getValues()));
            }   
            currentPopulation = (ArrayList<Candidate_Solution>)p.getCandidates().clone();
            newPopulation.clear();
            
            
            tempPopulation = p.championOperator(size/4);
            newPopulation.addAll(tempPopulation);
            //Winning candites gets removed from the list
            for(Candidate_Solution a : tempPopulation) currentPopulation.remove(a);

            tempPopulation = p.chooseRandom((size/4) , currentPopulation);
            newPopulation.addAll(tempPopulation);
            
            
            /*tempPopulation = p.crossOver(size/4, tempPopulation);
            newPopulation.addAll(tempPopulation);*/
            tempPopulation.clear();
            for(int newCan=0; newCan<size/4; newCan++) tempPopulation.add(new Candidate_Solution().generate());
            newPopulation.addAll(tempPopulation);
            
            tempPopulation = p.crossOver(size/4, p.getCandidates());
            newPopulation.addAll(tempPopulation);
            p.emptyPopulation();
            p.setPopulation(newPopulation);
            p.mutatePopulation();
            
            
            
            if(loop%100==0){
                System.out.print("Iteration " + loop + ": ");
                System.out.print(p.getLowest() + ", " + "Population:" + p.getCandidates().size() + ", ");
                System.out.println("Time: " + (System.currentTimeMillis() - startT) / 1000.0 + " ");
            }
            loop++;
            //System.out.println("Time: " + (System.currentTimeMillis() - startT) / 1000.0 + " ");
        }

        //get the fitness for a candidate solution in problem 1 like so
        double fit = p.getLowest();

        //System.out.println("The fitness of your example Solution is: " + fit);
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Now let us turn to the second problem:");
        System.out.println("A sample solution in this case is a boolean array of size 100.");
        System.out.println("I now create a random sample solution and get the weight and utility:");

        //Creating a sample solution for the second problem
        //The higher the fitness, the better, but be careful of  the weight constraint!
        boolean[] sol2 = new boolean[100];
        for (int i = 0; i < sol2.length; i++) {
            sol2[i] = (Math.random() > 0.5);
        }

        //Now checking the fitness of the candidate solution
        double[] tmp = (Assess.getTest2(sol2));

        //The index 0 of tmp gives the weight. Index 1 gives the utility
        System.out.println("The weight is: " + tmp[0]);
        System.out.println("The utility is: " + tmp[1]);

        //Once completed, your code must submit the results you generated, including your name and login: 
        //Use and adapt  the function below:
        Assess.checkIn(name, login, p.getBestFit(), sol2);

        //Do not delete or alter the next line
        long endT = System.currentTimeMillis();
        System.out.println("Total execution time was: " + ((endT - startT) / 1000.0) + " seconds");

    }
}