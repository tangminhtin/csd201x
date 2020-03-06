/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author TangMinhTin TinTMCEFX02373
 */
public class Main {

    // Contains a list of MyFile
    private MyFile[] files;
    private MyFile[] textFiles;

    // Ctor
    public Main() {
        files = null;
        textFiles = null;
    }
    

    /////////// LOAD FILE ///////////
    
    // Get information of all text files under given folder name
    public void loadFiles(String folder) {
        // Create a list to store list of object MyFile
        List<MyFile> listFiles = new ArrayList<>();
        loadFiles(folder, listFiles);
        files = listFiles.stream().toArray(MyFile[]::new);
        loadTextFile();
    }
    
    // Get information of text files
    private void loadTextFile() {
        List<MyFile> listTextFiles = new ArrayList<>();
        
        for(MyFile f: files) {
            if(f.getName().toLowerCase().endsWith(".txt")) {
                listTextFiles.add(new MyFile(f.getName(), f.getSize(), f.getFullPath()));
            }
        }
        textFiles = listTextFiles.stream().toArray(MyFile[]::new);
    }

    // Read all file with input folder name and add it to a list of object
    public void loadFiles(String folder, List<MyFile> listFiles) {
        /* Insert the code for listing all text files under given folder here */

        File currentFolder = new File(folder);              // Get current folder 
        File[] allFiles = currentFolder.listFiles();        // Get all files from a current folder

        if (allFiles != null) {                             // If current folder has file or subfolder
            for (File fileIn : allFiles) {                  // Read all file and subfolder

                // If reading is a file, then add it to listFiles (list is a object MyFile)
                // Ex: /Q11/myFile.txt
                if (fileIn.isFile()) {
                    listFiles.add(new MyFile(fileIn.getName(), fileIn.length(), fileIn.getAbsolutePath()));

                } else if (fileIn.isDirectory()) {
                    // If reading is a folder, then using recursion algorithm to read file in subfolder
                    // Ex1: /Q11/Testcase/   Ex2: /Q11/Testcase/subfolder
                    loadFiles(fileIn.getAbsolutePath(), listFiles);
                }
            }
        }
    }

    // List information of all loaded files
    public void list(MyFile[] files) {
        if (files != null && files.length > 0) {
            // Output heading
            System.out.println(String.format("%-20s%-10s", "Name", "Size(in byte)"));
            for (MyFile f : files) {
                System.out.println(f);
            }
        } else {
            System.out.println("List of files is empty...");
        }
    }

    
    /////////// SORT FILE ///////////
    
    // Sort the list of files ascending by size (use selection sort)
    public void selectionSort() {

        // Read all element in array
        for (int i = 0; i < textFiles.length - 1; i++) {
            int minIndex = i;   // Set current index of the first element as a minimum

            /* Check current minimum value with each element in an array
            by compare size of files. If size is less then, set index of j as minIndex */
            for (int j = i + 1; j < textFiles.length; j++) {
                if (textFiles[j].getSize() < textFiles[minIndex].getSize()) {
                    minIndex = j;
                }
            }

            // Swap minimum element with current element
            // With mean smaller value move to the left hand side
            MyFile temp = textFiles[minIndex];
            textFiles[minIndex] = textFiles[i];
            textFiles[i] = temp;
        }
    }

    // Sort the list of files ascending by size (use insertion sort)
    public void insertionSort() {

        // Read all element in array
        for (int i = 0; i < textFiles.length; i++) {
            MyFile current = textFiles[i];  // Current element is selected
            int j = i - 1;

            /* Check if the next element(size) in array is greater than element 
            in a sublist of sorted (also check all element) to find the suitable location */
            while (j >= 0 && textFiles[j].getSize() >= current.getSize()) {
                textFiles[j + 1] = textFiles[j]; // Move from the left to the right
                j--;    // Move from the right to the left
            }

            // Set current element in suitable location
            textFiles[j + 1] = current;
        }
    }

    // Sort and output sorted list of text files
    public void sort(SortType st) {
        if (null != st) {
            switch (st) {
                case INSERTTIONSORT:
                    insertionSort();
                    break;
                case SELECTIONSORT:
                    selectionSort();
                    break;
                case QUICKSORT:
                    quickSort(0, textFiles.length - 1);
                    break;
                default:
                    break;
            }
        }
        // Output result after sorting
        list(textFiles);
    }

    // Sort the list of files ascending by name (use quick sort)
    public void quickSort(int start, int end) {
        if (start < end) {
            /* Put pivot at right place, and the pivot value divides the list into two part */
            MyFile pivot = textFiles[end];      // Hold the last element as a pivot
            int iPart = start;              // Index of smaller element 
            for (int j = start; j < end; j++) {
                // If current file name is less than pivot file name, then move to left of pivot
                if ((textFiles[j].getName().toLowerCase().compareTo(pivot.getName().toLowerCase()) < 0)) {
                    // Swap files[iPart] and file[j]
                    MyFile tmp = textFiles[iPart];
                    textFiles[iPart] = textFiles[j];
                    textFiles[j] = tmp;
                    iPart++;    // Move partition by increase 1
                }
            }

            // Swap pivot (files[end]) at suitable location (iPart)
            MyFile tmp = textFiles[end];
            textFiles[end] = textFiles[iPart];
            textFiles[iPart] = tmp;

            // Recursively sort elements before partition and after partition 
            quickSort(start, iPart - 1);
            quickSort(iPart + 1, end);
        }
    }

    
    /////////// SEARCH FILE ///////////
    
    // Return true if given MyFile contains given keyword, otherwise return false
    public boolean searchFile(MyFile mf, String keyword) throws FileNotFoundException, IOException {
        if (!mf.getName().toLowerCase().endsWith(".txt")) {
            return false;
        }

        // Creates a new line number reader
        LineNumberReader lnr = new LineNumberReader(new FileReader(mf.getFullPath()));

        String line;
        // Reads a line of text and check and check the text is contains keyword or not
        while ((line = lnr.readLine()) != null) {
            if (line.contains(keyword.toUpperCase())) {
                return true;
            }
        }

        return false;   // Default not contains keyword
    }

    // Output information of all files which content has given keyword
    public void searchFile(String keyword) throws IOException {
        // Save all files which matched given keyword to the list and output the list
        List<MyFile> listFiles = new ArrayList<>();
        for (MyFile f : files) {
            if (searchFile(f, keyword)) {
                listFiles.add(f);
            }
        }

        MyFile[] foundFiles = listFiles.stream().toArray(MyFile[]::new);
        list(foundFiles);
    }

    
    /////////// READ CONTENT ///////////
    
    // Output content of file which content has given filename
    public void readContent(String filename) throws IOException {
        boolean isExist = false;

        // Put each file to check and read content of file
        for (MyFile file : files) {
            if (readContent(file, filename)) {
                isExist = true;
            }
        }

        if (!isExist) {
            System.out.println("File '" + filename + "' doesn't exist in any folder!");
        }
    }

    // Show context of file and return true if file is exist, otherwise return false
    public boolean readContent(MyFile mf, String filename) throws FileNotFoundException, IOException {

        // Check file has end with .txt and match or not
        if (!mf.getName().toLowerCase().endsWith(".txt") || (!mf.getName().toLowerCase().equals(filename.toLowerCase()))) {
            return false;
        }

        // Creates a new line number reader
        LineNumberReader lnr = new LineNumberReader(new FileReader(mf.getFullPath()));

        // Show all content of file
        String content;
        System.out.println("Context in file: ");
        while ((content = lnr.readLine()) != null) {
            System.out.println(content);
        }

        return true;
    }
    

    /////////// CHECK INPUT ///////////
    
    // Check input integer value and retype
    public int checkInput() {
        Scanner scanner = new Scanner(System.in);
        int input = 0;

        while (true) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                break;
            } else {
                System.out.print("Your inputted is invalid! \nEnter your choice again: ");
                scanner.next();
            }
        }

        return input;
    }

    
    /////////// MAIN PROGRAM ///////////
    
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        Main myProgram = new Main();

        while (true) {
            try {
                while (true) {
                    // Show menu for user choice
                    System.out.println("Menu");
                    System.out.println("1. Load files");
                    System.out.println("2. Sort files");
                    System.out.println("3. Search files");
                    System.out.println("4. Read content of file ");
                    System.out.println("0. Exit");
                    System.out.print("Enter your choice: ");

                    // Get user choice and remove enter key
                    int choice = myProgram.checkInput();

                    switch (choice) {
                        case 1:
                            // Get name of folder from user
                            System.out.print("Enter a folder: ");
                            String folderName = scanner.nextLine();

                            // Call method to load file and show result
                            myProgram.loadFiles(folderName);
                            myProgram.list(myProgram.files);

                            break;

                        case 2:
                            // Show menu for user choice
                            System.out.println("Sort the list of files by using");
                            System.out.println("1. Selection sort");
                            System.out.println("2. Insertion sort");
                            System.out.println("3. Quick sort");
                            System.out.print("Your choice: ");

                            int sortType = myProgram.checkInput();

                            // Get user choice
                            switch (sortType) {
                                case 1:
                                    myProgram.sort(SortType.SELECTIONSORT);
                                    break;
                                case 2:
                                    myProgram.sort(SortType.INSERTTIONSORT);
                                    break;
                                case 3:
                                    myProgram.sort(SortType.QUICKSORT);
                                    break;
                                default:
                                    System.out.println("Please choice from 1 to 3!");
                                    break;
                            }

                            break;

                        case 3:
                            // Get keyword from user
                            System.out.print("Enter any keyword to search: ");
                            String keyword = scanner.nextLine();

                            // Searching file
                            myProgram.searchFile(keyword);

                            break;

                        case 4:
                            // Get file from user
                            System.out.print("Enter a filename to read: ");
                            String filename = scanner.nextLine();

                            // Read content of file
                            myProgram.readContent(filename);

                            break;

                        case 0:
                            // Exit program
                            System.exit(0);
                        default:
                            System.out.println("Please choice from 0 to 4!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Please choose the first option!");
            }
        }
    }

}
