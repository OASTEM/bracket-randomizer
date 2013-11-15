
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private String name;
    private int wins, loss, draw;
    private int identifier;
    
    public Player(String n, int id){
        name = n;
        wins = 0;
        loss = 0;
        draw = 0;
        identifier = id;
    }
    public Player(String n, int w, int l, int d, int id){
        name = n;
        wins = w;
        loss = l;
        draw = d;
        identifier = id;
    }
    public Player(Player p){
        name = p.getName();
        wins = p.getWins();
        loss = p.getLoss();
        draw = p.getDraw();
        identifier = p.getID();
    }
    public void addWin(){
        wins++;
    }
    public void addWins(int n){
        wins += n;
    }
    public void subWin(){
        wins--;
    }
    public void subWins(int n){
        wins -= n;
    }
    public void addLoss(){
        loss++;
    }
    public void addLosses(int n){
        loss += n;
    }
    public void subLoss(){
        loss--;
    }
    public void subLosses(int n){
        loss -= n;
    }
    public void addDraw(){
        draw++;
    }
    public void addDraws(int n){
        draw += n;
    }
    public void subDraw(){
        draw--;
    }
    public void subDraws(int n){
        draw -= n;
    }
    public String getName(){
        return name;
    }
    public void setName(String n){
        name = n;
    }
    public int getWins(){
        return wins;
    }
    public int getLoss(){
        return loss;
    }
    public int getDraw(){
        return draw;
    }
    public int getID(){
        return identifier;
    }
}
