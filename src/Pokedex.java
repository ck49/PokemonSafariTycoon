import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aditya on 6/27/2015.
 */
public class Pokedex {

    public List<PokemonObject> list_of_pokemon = new ArrayList<>();

    public Pokedex(){

        list_of_pokemon.add(new Magikarp(0));
        list_of_pokemon.add(new Tentacool(0));
        list_of_pokemon.add(new Vaporeon(0));
        list_of_pokemon.add(new Kingdra(0));
    }


}
