/**
 * Created by Aditya on 6/27/2015.
 */
public class Tentacool extends PokemonObject{

    public Tentacool(int pop){
        carnivore = true;
        prey_rank = 2;
        hatch = 21;
        catch_rate = 190;
        rarity = 1;
        population = pop;
        mountain = false;
        lake = true;
        grass = false;
        price = 12;
        name = "Tentacool";
        description = "These small gelatinous creatures feed on small sea critters, and reproduce just enough that they are easy to take care of. They are, however, exceedingly common.";
    }
}
