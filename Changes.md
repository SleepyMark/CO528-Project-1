> <h2><u>Changes: to V4</u></h2>
</br>

> - <b>Complete</b> overhaul of Population.championOperator()
> - Fixed bug, where candidate solutions would grow exponentialy (N*2)
>    - This bug would increase runtime exponentially
> - Increased iterations from 20 to 1000
> - Increased population from 25 to 1000
> - Discarded random population crossover, in favor of completely new solutions (N/4)
