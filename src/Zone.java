import java.util.ArrayList;
import java.util.List;
/**
 * Created by Aditya on 6/27/2015.
 */
public class Zone {

    // 0 = grass, 1 = lake, 2 = mountain
    public int type;
    public List<PokemonObject> pokemonList;
    public int population;
    public int[] preyRankPop = new int[4];
    public int[] carnivoreRankPop = new int[4];
    public int entranceFee;
    public int visitors;
    public boolean didLastAddWork = false;
    public List<PokemonObject> deadPokemon = new ArrayList<PokemonObject>();


    public Zone(int setType){
        type = setType;
        pokemonList = new ArrayList<PokemonObject>();
        entranceFee = 5;
        visitors = 0;
    }


    //checks if a pokemon's living area matches this area
    private boolean checkIfPokemonCanLiveInArea(PokemonObject poke){
        if(type == 0){
            return poke.grass;
        }
        if(type == 1){
            return poke.lake;
        }
        return poke.mountain;
    }

    private void addNewPokeToPopulation(PokemonObject pokemon){
        population = population + pokemon.population;
        preyRankPop[pokemon.prey_rank-1] = preyRankPop[pokemon.prey_rank - 1] + pokemon.population;
    }

    public String addPokemon(PokemonObject newPoke){
        if(!checkIfPokemonCanLiveInArea(newPoke)){return "This area and pokemon are not compatible";}
        if(pokemonList.size() > 6){return "You already have 6 species living in this area!";}
        population = population + newPoke.population;
        addNewPokeToPopulation(newPoke);
        didLastAddWork = true;
        return newPoke.name + "was successfully added to the area!";
    }
    public boolean changeEntranceFee(int newFee){
        if(newFee > 10 || newFee < 2){
            return false;
        }
        entranceFee = newFee;
        return true;
    }
    //returns income from visitors
    private void calculateVisitors(){
        int avgRarity = 0;
        for(int i = 0; i < pokemonList.size(); i++){
            avgRarity = avgRarity + pokemonList.get(i).rarity;
        }
        avgRarity = avgRarity/pokemonList.size();
        visitors = avgRarity * 10 * pokemonList.size() / entranceFee;
        if(visitors > 100){visitors = 100;}
    }

    private List<PokemonObject> updateAllPokemonPopulation(){
            List<PokemonObject> pokemonThatDiedOut = new ArrayList<PokemonObject>();
            int eaten3 = carnivoreRankPop[3]/(preyRankPop[2] + preyRankPop[1] + preyRankPop[0]);
            int eaten2 = eaten3 + carnivoreRankPop[2]/(preyRankPop[1] + preyRankPop[0]);
            int eaten1 = eaten2 + carnivoreRankPop[1]/preyRankPop[0];
        for(int i = 0; i < pokemonList.size(); i++){
            PokemonObject pokemon = pokemonList.get(i);
            int caught = pokemon.catch_rate * pokemon.rarity * pokemon.population * visitors / 255000;
            int born = (1+(4/pokemon.hatch)-(4/81))*pokemon.population;
            int eaten = 0;
            int hungry = 0;
            if(pokemon.prey_rank == 4){
                hungry = pokemon.population - (int) ((.66 * preyRankPop[2]) + (.33 * preyRankPop[1]) + (22 * preyRankPop[0]));
            } else if(pokemon.prey_rank == 3){
                hungry = pokemon.population - (int) ((.66 * preyRankPop[1]) + (.33 * preyRankPop[0]));
                eaten = eaten3;
            } else if(pokemon.prey_rank == 2){
                hungry = pokemon.population - (int) ((.66 * preyRankPop[0]));
                eaten = eaten2;
            } else if(pokemon.prey_rank == 1){
                eaten = eaten1; //eaten stopped seeming like a real word around this point.
            }
            if(!pokemon.carnivore || hungry < 0){
                hungry = 0;
            }
            pokemon.population = pokemon.population - hungry - eaten - caught + born;
            if (pokemon.population < 0){
                pokemonList.remove(pokemon);
                pokemonThatDiedOut.add(pokemon);
            }

        }
        population = 0;
        for(int i = 0; i < 4; i++){
            preyRankPop[i] = 0;
            carnivoreRankPop[i] = 0;
        }
        for(int i = 0; i < pokemonList.size(); i++){
            PokemonObject pokemon = pokemonList.get(i);
            population = population + pokemon.population;
            preyRankPop[pokemon.prey_rank-1] = preyRankPop[pokemon.prey_rank - 1] + pokemon.population;
            if(pokemon.carnivore){
                carnivoreRankPop[pokemon.prey_rank-1] = carnivoreRankPop[pokemon.prey_rank - 1] + pokemon.population;
            }
        }
        if(population > 50){
            double ratio = 50.0 /((double) population);
            population = 0;
            for(int i = 0; i < 4; i++){
                preyRankPop[i] = 0;
                carnivoreRankPop[i] = 0;
            }
            for(int i = 0; i < pokemonList.size(); i++){
                PokemonObject pokemon = pokemonList.get(i);
                pokemon.population = (int) (pokemon.population * ratio);
                if(pokemon.population < 1){pokemon.population = 1;}
                population = population + pokemon.population;
                preyRankPop[pokemon.prey_rank-1] = preyRankPop[pokemon.prey_rank - 1] + pokemon.population;
                if(pokemon.carnivore){
                    carnivoreRankPop[pokemon.prey_rank-1] = carnivoreRankPop[pokemon.prey_rank - 1] + pokemon.population;
                }
            }
        }

        return pokemonThatDiedOut;
    }

    public void refresh(){
        calculateVisitors();
        deadPokemon = updateAllPokemonPopulation();
    }
    public void remove(PokemonObject pokemon){
        population = population - pokemon.population;
        preyRankPop[pokemon.prey_rank - 1] = preyRankPop[pokemon.prey_rank - 1] - pokemon.population;
        if(pokemon.carnivore){
            carnivoreRankPop[pokemon.prey_rank - 1] = carnivoreRankPop[pokemon.prey_rank - 1] - pokemon.population;
        }
    }


}
