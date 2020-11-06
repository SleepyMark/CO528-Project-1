> ##### V2.2.1
> - Fixed dial[0] = 0 error
> - Faster convergence on the fitness value
> - Reduced iterations from 25 to 20
>    - Iterations after 20 took too long to process
> - Further decreased mutationFactor when reaching <50
>    - <b>More tweaking may be needed </b>

> ##### V3
> - Changed mutateFactor from ((Math.random() * 10) * mutationFactor) to Log10(fitnessValue)
>   - Mutation decreases, the closer it gets to 0
> - Changed setFitness(int fit) to allow smaller mutations, the closer it gets to zero
> - Decreased & balanced selection operators, to reduce premature convergence
>   1. champion = n/4
>   2. chooseRandom = n/4-champions
>   3. crossoverLosers = n/4-champions
>   4. 3. crosoverPrevPopulation = n/4
