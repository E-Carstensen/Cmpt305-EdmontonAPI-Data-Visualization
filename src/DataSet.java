public class DataSet{
    Account[] accountList;
    int entries = 0;
    int arrayLen;

    DataSet(int len){
        Account[] accountList = new Account[len];
        arrayLen = len;
    }

    public void addEntry(String[] data){
        accountList[entries] = new Account();
        accountList[entries].assignData(data);
        entries++;
    }

    public int getHighestValue(){
        int max = 0;
        for (int i = 0; i <= arrayLen; i++){
            if (accountList[i].assessedValue > max){
                max = accountList[i].assessedValue;
            }
        }
        return max;
    }

    public int getLowestValue(){
        int min = 0;
        for (int i = 0; i <= arrayLen; i++){
            if (accountList[i].assessedValue > min){
                min = accountList[i].assessedValue;
            }
        }
        return min;
    }

    public int getUniqueWards(){
        int count = 0;
        return count;
    }

}
