/**********************************************************************************************************************
 * Eric Carstensen - 3070801
 * CMPT 305 - X01L - Milestone 1
 **********************************************************************************************************************/

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class for Lab 2
 * Takes path to a csv file, creates a DataSet object, and displays various information using the Menu class
 */
public class Lab2Main {

    public static void main(String[] args){

        String filePath =  Menu.getFileName();
        if (filePath.isBlank()){return;}

        DataSet dataSet = new DataSet(filePath);

        Menu.displayStats(dataSet, dataSet.accountList);

        Menu.searchByAccount(dataSet);

        Menu.searchByNeighborhood(dataSet);

    }




}
