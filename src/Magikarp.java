/**
 * Created by Aditya on 6/27/2015.
 */
public class Magikarp extends PokemonObject{
    public Magikarp(int pop){
        carnivore = false;
        prey_rank = 1;
        hatch = 6;
        catch_rate = 255;
        rarity = 1;
        population = pop;
        mountain = false;
        lake = true;
        grass = false;
        price = 10;
        name = "Magikarp";
        description = "This seemingly useless Pokemon is found everywhere, and is thus is the primary food for many sea creatures. Beware, they breed like wild, and could crowd out other species in their pond";
    }
}
