
/**
 * using the List given from Input List, creating objects of each input. then make ArrayList of them.
 * 
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class PlayerList
{
    private ArrayList<Player> playerList;
    
    /**
     * list of players
     */
    
    public PlayerList(List<String> list){
        convertToArrayList(list);
        //sortByName();
        MergeSort ms = new MergeSort(playerList);
        ms.sortListByName();
        playerList = ms.getList();//sortListByName(playerList);
    }
    
    public ArrayList<Player> getIdList(){
        MergeSort ms = new MergeSort(playerList);
        ms.sortListByID();
        return ms.getList();
    }
    
    

    public void createInternets(String n, int i){
        //interList.add(new Internets(i, n));
        Internets tempIn = new Internets(i, n);
        int[] locArray = getALocArray(interList);
        outPrint("Adding Account");
        interList.add(findInternetsPlace(tempIn, interList, locArray),tempIn);
        outPrintln("done.");
    }
    
    /**
     * Pre-condition: ID already exists in database.
     */
    public Player findByID(int id, ArrayList<Player> list){
        if(list.size() > 1){
            int half = list.size()/2;
            if(list.get(half).getID() > id){
                return findByID(id, new ArrayList<Player>(list.subList(0,half));
            }
            else{
                return findByID(id, new ArrayList<Player>(list.subList(half, list.size())));
            }
        }
        return list.get(0);
    }
    
    
    private void convertToArrayList(List<String> list){
        outPrintln("Parsing File");
        playerList = new ArrayList<Player>();
        for(String s : list){
            int index = s.indexOf(":");
            String name = s.substring(0,index);
            int index2 = s.indexOf(":",index+1);
            String winsStr = s.substring(index+1,index2);
            index = s.indexOf(":",index2+1);
            String lossStr = s.substring(index2+1,index);
            index2 = s.indexOf(":",index+1);
            String drawStr = s.substring(index+1,index2);
            String id = s.substring(index2+1,s.length());
            outPrintln(name+":"+winsStr+":"+lossStr+":"+drawStr+":"+id);
            int wins = Integer.parseInt(winsStr);
            int loss = Integer.parseInt(lossStr);
            int draw = Integer.parseInt(drawStr);
            int iden = Integer.parseInt(id);
            playerList.add(new Player(name, wins, loss, draw, iden));
        }
        outPrintln("done.");
    }
    
    private int getSmallestNet(){
        int min = interList.get(0).getInternets();
        for(Internets i : interList){
            if(i.getInternets() < min){
                min = i.getInternets();
            }
        }
        return min;
    }
    
    
    public ArrayList<Player> getPlayerList(){
        return playerList;
    }
    
    /**
    public void setLocsArray(int[] nums){
        validNetsLoc = nums;
    }
    */

    

    public void subtractInternets(int sub, int[] ins){
        //getTotalNets();
        outPrint(".");
        int[] validsLoc = getValidInternets(ins);
        int valids = validsLoc.length;
        int threshold = sub/(valids);
        int smallestNet = getSmallestNetOf(ins);
        if(smallestNet <= threshold){
            for(int i = 0; i < valids; i++){
                interList.get(validsLoc[i]).subtractInters(smallestNet);
                //inList.get(i).subtractInters(smallestNet);
            }
            subtractInternets(sub-(smallestNet*valids), getValidInternets(ins));
        }
        else{
            for(int i = 0; i < valids; i++){
                interList.get(validsLoc[i]).subtractInters(threshold);
                // interList.get(validNetsLoc[i]).subtractInters(threshold);
            }
        }
    }
    
    public void subtractInternet(String name, int sub){
        int[] locArray = getALocArray(interList);
        outPrint("Subtracting from Account");
        interList.get(findInternets(name, interList, locArray)).subtractInters(sub);
        outPrintln("done.");
    }
    
    private void sortByName(){
        outPrint("Generating Sort List");
        String[] strs = new String[playerList.size()];
        for(int i = 0; i < playerList.size(); i++){
            outPrint(".");
            strs[i] = playerList.get(i).getName();
        }
        outPrintln("done.");
        MergeSort ms = new MergeSort(strs);
        strs = ms.getStrArray();
        playerList = convertStrToArrayList(strs);
    }
    
    public int findInternets(String name, ArrayList<Internets> inList, int[] locs){
        if(inList.size() > 1){
            int q = inList.size()/2;
            outPrint(".");
            outPrintln(""+q);
            printArray(locs);
            if(name.compareTo(inList.get(q).getName()) < 0){
                return findInternets(name, new ArrayList<Internets>(inList.subList(0,q)), Arrays.copyOfRange(locs, 0, q));
            }
            else if(name.compareTo(inList.get(q).getName()) > 0){
                return findInternets(name, new ArrayList<Internets>(inList.subList(q,inList.size())),Arrays.copyOfRange(locs, q, locs.length));
            }
            else{
                return locs[q];
            }
        }
        return locs[0];
    }
    
    private void printArray(int[] ar){
        for(int i : ar){
            outPrint(""+i);
        }
        outPrintln("");
    }
    
    public int[] getALocArray(ArrayList<Internets> list){
        outPrint("Getting List of Locations");
        int[] locArray = new int[list.size()];
        for(int i = 0; i < locArray.length; i++){
            outPrint(".");
            locArray[i] = i;
        }
        outPrintln("done.");
        return locArray;
    }
    
    public void addInternets(String name, int ins){
        int[] locArray = getALocArray(interList);
        outPrint("Adding internets");
        interList.get(findInternets(name, interList, locArray)).addInters(ins);
    }
    
    private int[] sortByInternets(int[] locs){
        //ArrayList<Internets> tempList = new ArrayList<Internets>();
        int[][] newLocsArray = new int[2][locs.length];
        outPrint("Generating List");
        for(int l = 0; l < locs.length; l++){
            //outPrint(""+l);
            newLocsArray[0][l] = locs[l];
            newLocsArray[1][l] = interList.get(locs[l]).getInternets();
            outPrint(""+locs[l]);
            outPrintln(""+newLocsArray[1][l]);
            //tempList.add(new Internets(interList.get(l)));
            //outPrint(".");
        }
        outPrintln("done.");
        MergeSort ms = new MergeSort(newLocsArray, true);
        //tempList = new ArrayList<Internets>(ms.getList());
        newLocsArray = ms.get2Array();
        //int[][] newLocs = new int[tempList.size()];
        int[] newLocs = new int[newLocsArray[0].length];
        for(int i = 0; i < newLocsArray[0].length; i++){
            newLocs[i] = newLocsArray[0][i];
        }
        return newLocs;
        /**
        int k = 0;
        outPrint("Getting Locations");
        for(Internets i : tempList){
            outPrint(".");
            int in = 0;
            boolean isFound = false;
            while(!isFound && in < interList.size()){
                if(i.getName().equals(interList.get(in))){
                    isFound = true;
                    newLocs[k] = in;
                    k++;
                }
                else{
                    in++;
                }
            }
        }
        outPrintln("done.");
        return newLocs;
        //*/
       //return newLocsArray;
    }
    
    private int getTotalNetsOf(int[] locs){
        int sum = 0;
        for(int l : locs){
            sum += interList.get(l).getInternets();
        }
        return sum;
    }
    
    public String calculatePayments(int[] locsArray, int ins){
        getTotalNets();
        int totals = getTotalNetsOf(locsArray);
        for(int l : locsArray){
            int tempPercent = (int) (((interList.get(l).getInternets()*1.0)/(1.0*totals))*10000);
            interList.get(l).setPercentage(tempPercent);
        }
        printArray(locsArray);
        int[] internetsLeastToGreat = sortByInternets(locsArray);
        printArray(internetsLeastToGreat);
        //ArrayList<Internets> newTempList = new ArrayList<Internets>();
        int[][] perLocsArray = new int[2][internetsLeastToGreat.length];
        outPrint("Finding Accounts");
        for(int i = 0; i < locsArray.length; i++){
            //newTempList.add(new Internets(interList.get(i)));
            perLocsArray[0][i] = locsArray[i];
            perLocsArray[1][i] = interList.get(locsArray[i]).getPercentage();
            outPrint(".");
        }
        outPrintln("done");
        //int[] percentagesOfLocsArray = getPercentages(newTempList);
        MergeSort ms = new MergeSort(perLocsArray, false);
        perLocsArray = ms.get2Array();
        //printArray(perLocsArray);
        outPrint("Subtract from Accounts");
        subtractInternets(ins, locsArray); //COOKIE BUTTER
        outPrintln("done.");
        
        /**
         * locsArray is SORTEDBYNAME //[]
         * perLocsArray is SORTEDBYPERCENTAGE //[][]
         * internetsLeastToGreat is array of locs SORTEDBYINTERNETS //[]
         */
        int[][] changes = doubleCheck(ins, perLocsArray, internetsLeastToGreat);
        int sum = 0;
        String result = "";
        if(changes == null){
            //String result = "";
            //int k = 0;
            outPrint("Preparing results");
            
            for(int i = 0; i < locsArray.length; i++){
                outPrint(".");
                //int percentResult = perLocsArray[1][*totals;
                //result = result + interList.get(i).getName() +":"+ getPercentFront(percentResult) +"."+ getPercentBack(percentResult) +"\n";
                int percentResult = ((perLocsArray[1][i]*ins)/10000);
                sum += percentResult;
                result = result + interList.get(internetsLeastToGreat[i]).getName() +":"+getPercentFront(percentResult)+"."+getPercentBack(percentResult)+"$"+"\n";
                //k++;
            }
            getTotalNets();
            outPrintln("done.");
        }
        else{
            
            //String result = "";
            //int k = 0;
            outPrint("Preparing results");
            for(int i = 0; i < locsArray.length; i++){
                outPrint(".");
                //int percentResult = perLocsArray[1][*totals;
                //result = result + interList.get(i).getName() +":"+ getPercentFront(percentResult) +"."+ getPercentBack(percentResult) +"\n";
                int percentResult = ((perLocsArray[1][i]*ins)/10000);
                percentResult += changes[2][i];
                sum += percentResult;
                result = result + interList.get(internetsLeastToGreat[i]).getName() +":"+getPercentFront(percentResult)+"."+getPercentBack(percentResult)+"$"+"\n";
                //k++;
            }
            getTotalNets();
            outPrintln("done.");
        }
        result = result + "Sum Total: "+getPercentFront(sum)+"."+getPercentBack(sum)+"$"+"\n";
        return result;
    }
    
    private int[][] doubleCheck(int aIn, int[][] pLArray, int[] iSLTGArray){
        int expectedValue = aIn;
        int returnedValue = 0;
        int diff = 0;
        int[][] dCheck = new int[3][iSLTGArray.length];
        for(int i = 0; i < pLArray[0].length; i++){
            returnedValue += ((pLArray[1][i]*aIn)/10000);
        }
        if(returnedValue < expectedValue){
            int iSL = 0;
            for(int i : iSLTGArray){
                dCheck[0][iSL] = i;
                iSL++;
            }
            diff = expectedValue-returnedValue;
            int l = 0;
            for(int k = 0; k < diff; k++){
                if(l >= dCheck[0].length){
                    l = 0;
                }
                dCheck[1][l] -= 1;
                dCheck[2][l] += 1;
            }
        }
        else if(returnedValue > expectedValue){
            int iSL = iSLTGArray.length-1;
            for(int i : iSLTGArray){
                dCheck[0][iSL] = i;
                iSL--;
            }
            diff = returnedValue-expectedValue;
            int l = 0;
            for(int k = 0; k < diff; k++){
                if(l >= dCheck[0].length){
                    l = 0;
                }
                dCheck[1][l] += 1;
                dCheck[2][l] -= 1;
            }
        }
        else{
            return null;
        }
        int[][] d2Check = new int[dCheck.length][dCheck[0].length];
        for(int i = 0; i < pLArray[0].length;i++){
            d2Check[0][i] = pLArray[0][i];
            boolean isIn = false;
            for(int k = 0; k < dCheck[0].length;k++){
                if(!isIn && d2Check[0][i] == dCheck[0][k]){
                    d2Check[1][i] = dCheck[1][k];
                    d2Check[2][i] = dCheck[2][k];
                    isIn = true;
                }
            }
        }
        return dCheck;
    }
    
    private String getPercentFront(int a){
        return ""+(a/100);
    }
    
    private String getPercentBack(int a){
        return ""+(a%100);
    }
    
    public String printOutText(boolean a, boolean i, boolean n, boolean p){
        percentagesMethods();
        String result = "name:internets:percentage of total"+"\n";
        outPrint("Preparing printout");
        for(Internets k : interList){
            outPrint(".");
            if(a){
                result = result + k.getName()+":"+getPercentFront(k.getInternets())+"."+getPercentBack(k.getInternets())+":"+getPercentFront(k.getPercentage())+"."+getPercentBack(k.getPercentage())+"\n";
            }
            else{
                if(n){
                    result = result + k.getName();
                }
                if(i){
                    if(n){
                        result = result + ":"+getPercentFront(k.getInternets())+"."+getPercentBack(k.getInternets());
                    }
                    else{
                        result = result + getPercentFront(k.getInternets())+"."+getPercentBack(k.getInternets());
                    }
                }
                if(p){
                    if(n || i){
                        result = result + ":"+getPercentFront(k.getPercentage())+"."+getPercentBack(k.getPercentage());
                    }
                    else{
                        result = result + getPercentFront(k.getPercentage())+"."+getPercentBack(k.getPercentage());
                    }
                }
                result = result + "\n";
            }
        }
        outPrintln("done.");
        return result;
    }
    
    public ArrayList<String> writeFile(){
        ArrayList<String> list = new ArrayList<String>();
        outPrint("Preparing Save");
        for(Internets k: interList){
            outPrint(".");
            //outPrintln(k.getName()+":"+getPercentFront(k.getInternets())+"."+getPercentBack(k.getInternets()));
            list.add(k.getName()+":"+getPercentFront(k.getInternets())+"."+getPercentBack(k.getInternets()));
        }
        outPrintln("done.");
        return list;
    }
    
    private void outPrintln(String text){
        System.out.println(text);
    }
    
    private void outPrint(String text){
        System.out.print(text);
    }
}
