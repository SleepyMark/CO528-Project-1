# Genetic Algorithm V1
> <u>Points:</u>
> - Population is at 20
> - Population & Candidate Solution Class
> - Mutations became smaller, the closer it got to the global minima
> - 1000 x 5 Iterations
> 
> - Fitness value, would start at ~260, then drop:
>   1. Iteration 100: 135.99
>   2. Iteration 200: 152.26
>   3. Iteration 400: 145.27
>   4. Iteration 800: 157.82
>   5. Iteration 999: 153.47
>   
>Reasons:
>    - May have hit a local minima
>    - Dial inputs have to be so specific, rightup to the thuosands of a decimal
>       - Mutations may have to be smaller, the closer it gets to 0
>    - All points may have converged too early
>    
>    Solutions:
>    - Implement a decreasing mutation/more precise mutation
>    - Implement a diversifying candidate solution selection
