
/**
 * list of brackets
 */
import java.util.ArrayList;
public class BracketList
{
    private ArrayList<Bracket> bracketList;   
    
    public BracketList(List<String> list, PlayerList pl){
        convertToArrayList(list, pl);
    }
    
    private void convertToArrayList(List<String> list, PlayerList pl){
        outPrintln("Parsing File");
        bracketList = new ArrayList<Bracket>();
        //ArrayList<Player> playerIDList = pl.getIDList();
        
        for(String s : list){
            int index = s.indexOf(":");
            String round = s.substring(0,index);
            int index2 = s.indexOf(":",index+1);
            String playerOne = s.substring(index+1,index2);
            String playerTwo = s.substring(index2+1,s.length());
            outPrintln(playerOne+":"+playerTwo+":Round-"+round);
            int play1 = Integer.parseInt(playerOne);
            int play2 = Integer.parseInt(playerTwo);
            int level = Integer.parseInt(round);
            bracketList.add(pl.findByID(play1), pl.findByID(play2),level);
        }
        outPrintln("done.");
    }
}
