/**
 * Created by Aditya on 6/27/2015.
 */
public class Vaporeon extends PokemonObject{

    public Vaporeon(int pop){
        carnivore = true;
        prey_rank = 3;
        hatch = 36;
        catch_rate = 45;
        rarity = 8;
        population = pop;
        mountain = false;
        lake = true;
        grass = false;
        price = 800;
        name = "Vaporeon";
        description = "These friendly creatures are a popular attraction - rare, strong, and playful. However, they aren't easy to take of.";
    }
}
