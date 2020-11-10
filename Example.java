
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

        //My solution
        //Population Size
        int size = 25;
        //Stores & manipulates the candidate solutions
        Population p = new Population(size);
        
        //Stores the current population
        ArrayList<Candidate_Solution> currentPopulation;
        //Stores the new population going to be added
        ArrayList<Candidate_Solution> newPopulation = new ArrayList<Candidate_Solution>();
        //Stores...what is says
        ArrayList<Candidate_Solution> tempPopulation = new ArrayList<Candidate_Solution>();
        //Holds the fitness value, for a given candidate
        double num;
        //Used for debugging purposes: see line 71
        int loop=1;
        
        while(true){
            
            p.resetLowest();
            for(Candidate_Solution n : p.getCandidates()){
                //System.out.println("Before: " + Arrays.toString(n.getValues()));
                num = Assess.getTest1(n.getValues());
                n.setFitness(num);
                p.checkIfLowest(num);
                //System.out.println("After: " + Arrays.toString(n.getValues()));
            }   
            
            //Capped myself at 10 marks, due to the program taking too long to get any lower
            if(p.getBestCandidate().getFitness()<1E-10){
                break;
            }
            ///////////////////////////////////////////////////////////////////////////
            currentPopulation = (ArrayList<Candidate_Solution>)p.getCandidates().clone();
            newPopulation.clear();
            ///////////////////////////////////////////////////////////////////////////
            // N/4 candidates are chosen, to move onto the new population
            tempPopulation = p.championOperator(size/4);
            newPopulation.addAll(tempPopulation);
            //Winning candidates gets removed from the list
            for(Candidate_Solution a : tempPopulation) currentPopulation.remove(a);
            ///////////////////////////////////////////////////////////////////////////
            // Championing cadidates, are then crossed-over to create possibly better solutions
            tempPopulation = p.crossOver(size/4, tempPopulation);
            newPopulation.addAll(tempPopulation);
            ///////////////////////////////////////////////////////////////////////////
            // N/4 Completly new candidates are generated, to make sure the fitness landscape is explored thoroughly
            tempPopulation.clear();
            for(int newCan=0; newCan<size/2; newCan++) tempPopulation.add(new Candidate_Solution().generate());
            newPopulation.addAll(tempPopulation);
            ///////////////////////////////////////////////////////////////////////////
            //The old population is emptied, and the new population is added
            p.emptyPopulation();
            p.setPopulation(newPopulation);
            //Every candiadte is mutated, to help get to the global minimum faster
            //The biggest problem I had was my mutation rate: See Candidate_Solution.mutate()
            p.mutatePopulation();
            ///////////////////////////////////////////////////////////////////////////
            
            //This part is used to track the populations best candidate
            /*if(loop%10000==0){
                System.out.print("Iteration " + loop + ": ");
                System.out.print(p.getBestCandidate().getFitness() + ", " + "Population:" + p.getCandidates().size() + ", ");
                System.out.println("Time: " + (System.currentTimeMillis() - startT) / 1000.0 + " ");
            }
            loop++;*/

        }


        //Population size
        int itemsSize = 50;
        //Stores & manipulates candidate solutions
        Luggage_Population l = new Luggage_Population(itemsSize);
        //Stores the new population going to be added
        ArrayList<Luggage_Candidate> currentItems;
        //Stores the new population going to be added
        ArrayList<Luggage_Candidate> newItems = new ArrayList<Luggage_Candidate>();
        ArrayList<Luggage_Candidate> tempItems = new ArrayList<Luggage_Candidate>();
        //Resets the loop & used for debugging: see line 129=
        loop=1;
        Luggage_Candidate b = null;
        double stuff[] =  new double[2];
        //Stores the number of new candidates needed to be added
        int newCan;
        
        /*
         * This algorithm for problem 2 is more biased towards elitism & championing. The point is to build-up the solutions, close to the threshold
         */
        
        
        //Checks & sets the fitness value of each solution
        for(Luggage_Candidate i : l.getCandidates()){
            stuff = Assess.getTest2(i.getValues());

            i.setWeight(stuff[0]);
            i.setFitness(stuff[1]);
            l.checkIfHighest(stuff[1]);
        }   
        //Candidates that are overmeet the critera, and are eliminated from the population
        newCan = l.killOff();
        while(true){
            l.resetHighest();
            /////////////////////////////////////////////
            //Resets the arraylists 
            currentItems = (ArrayList<Luggage_Candidate>)l.getCandidates().clone();
            newItems.clear();
            /////////////////////////////////////////////
            //Championing operators that meet the requirements,are then chosen against eachother
            // N/2-(candidatesRemoved) are chosen & added
            tempItems = l.championOperator((itemsSize/2)-(newCan));
            newItems.addAll(tempItems);
            //Winning candidates gets removed from the list
            for(Luggage_Candidate a : tempItems) currentItems.remove(a);
            /////////////////////////////////////////////
            // N/4 candiates are generated from the championing solutions
            tempItems = l.crossOver((itemsSize/4)-(newCan), tempItems);
            newItems.addAll(tempItems);
            tempItems.clear();
            /////////////////////////////////////////////
            //New canddiates are generates, to make sure the fitness landscape is explored
            for(int newLug=0; newLug<newCan; newLug++) tempItems.add(new Luggage_Candidate());
            newItems.addAll(tempItems);
            /////////////////////////////////////////////
            //This bit used for tracking the best fitness in each iteration
            /*if(loop%1000==0){
                b = l.getBestCandidate();
                System.out.print("Iteration " + loop + ": ");
                System.out.print(b.getFitness()+ ", Weight: " + b.getWeight()  + ", " + "No. of Items: " + (l.getCandidates().size()) + ", ");
                System.out.println("Time: " + (System.currentTimeMillis() - startT) / 1000.0 + " ");
            }
            loop++;*/
            
            
            // The new canddiates are added to he new population
            l.emptyPopulation();
            l.setPopulation(newItems);
            //The enw poopulation is then mutated, to give a better variety in solutions
            l.mutatePopulation();

            //Candidate solutions are then checked for their fitness & weight
            for(Luggage_Candidate i : l.getCandidates()){
                stuff = Assess.getTest2(i.getValues());

                i.setWeight(stuff[0]);
                i.setFitness(stuff[1]);
                l.checkIfHighest(stuff[1]);
            }
            //Candidates that overmeet the requiements are 'killed'
            newCan = l.killOff();
            
            b = l.getBestCandidate();
            //This algorithm is supirising efficent, compared to the first one which had problems with a smaller & smaller mutation rate
            if(b.getFitness() > 206){
                break;
            }

        }


        //Once completed, your code must submit the results you generated, including your name and login: 
        //Use and adapt  the function below:
        Assess.checkIn(name, login, p.getBestCandidate().getValues(),l.getBestCandidate().getValues());

        //Do not delete or alter the next line
        long endT = System.currentTimeMillis();
        System.out.println("Total execution time was: " + ((endT - startT) / 1000.0) + " seconds");

    }
}