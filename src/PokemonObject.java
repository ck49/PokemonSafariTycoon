public abstract class PokemonObject{
    public boolean carnivore;
    public int prey_rank;
    public int hatch;
    public int catch_rate;
    public int rarity;
    public int population = 0;
    public boolean mountain = false;
    public boolean lake = false;
    public boolean grass = false;
    public int price;
    public String description;
    public String name;

    public PokemonObject(){}
    public PokemonObject(int pop){
        population = pop;
    }
}