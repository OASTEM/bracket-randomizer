
/**
 * Write a description of class Bracket here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.List;
public class Bracket
{
    private Player play1, play2;
    private int level;
    public Bracket(Player p1, Player p2, int lev){
        play1 = p1;
        play2 = p2;
        level = lev;
    }
    public void chooseWinner(String name){
        name = name.toLowerCase();
        if(play1.getName().toLowerCase().equals(name)){
            play1.addWin();
            play2.addLoss();
        }
        else if(play2.getName().toLowerCase().equals(name)){
            play2.addWin();
            play1.addLoss();
        }
        else {
            System.out.println("name not found, try again");
        }
    }
    public void setLevel(int n){
        level = n;
    }
    public int getLevel(){
        return level;
    }
    public List<Player> getPlayers(){
        List<Player> playerList = new List<Player>();
        playerList.add(play1);
        playerList.add(play2);
        return playerList;
    }
}
