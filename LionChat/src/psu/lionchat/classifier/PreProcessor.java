/**
 * Class PreProcessor - package: preProcessing
 * This object takes in a string of text turns it into an arraylist of arraylists containing
 * processed words.  I.e., stemming, removing stopwords, removing punctuation, etc..
 * @author William Hemminger
 * 14 October 2020
 */

package psu.lionchat.classifier;

import java.util.ArrayList;
import java.util.Scanner;
import weka.core.stemmers.IteratedLovinsStemmer;

public class PreProcessor {

    private String processString;
    private ArrayList<String> wordList;
    private IteratedLovinsStemmer stemmer;

    // default constructor
    public PreProcessor()
    {
        this.processString = "";
        this.wordList = new ArrayList<String>();
        stemmer = new IteratedLovinsStemmer();
    }
    //constructor that takes string to be processed
    public PreProcessor(String process)
    {
        this.processString = process;
        this.wordList = new ArrayList<String>();
        this.stemmer = new IteratedLovinsStemmer();
    }

    /**
     * function setProcessString can be used to manually set string value
     * @param s
     */
    public void setProcessString(String s)
    {
        this.processString = s;
    }

    /**
     * function getProcessString returns processed string value
     * @return processString
     */
    public String getProcessString() {
        return this.processString;
    }

    /**
     * function process will process an input string, fill in the processString field and populate
     * wordList with the separate words from the string. Processed string will have no extraneous punctuation and
     * will be stemmed
     * @param s the input to be processed
     * @return the processed string
     */
    public String process(String s)
    {
        s = s.toLowerCase();

        //opens stopWords.txt file and replaces all stopwords with ""
        try(Scanner reader = new Scanner(getClass().getResourceAsStream("stopwords.txt")))
        {
            while(reader.hasNext())
            {
                String regexString = reader.next();
                s = s.replaceAll("\\b" + regexString + "\\b", "");
            }
        }
        catch(NullPointerException e)
        {
            System.err.println("stopwords.txt missing");
            return "An Error Has Occurred";
        }

        s = s.replaceAll("\\-", ""); //removes hyphens
        s = s.replaceAll("[^a-zA-Z0-9.?\\s]", ""); //removes other punctuation except '.' & '?'

        String [] wordArr = s.split("\\s|\\?|\\."); //splits words

        this.processString = ""; //resets process string

        for(int i = 0; i < wordArr.length; i++)
        {
            if(wordArr[i].matches("\\w+"))
            {
                wordArr[i] = stemmer.stem(wordArr[i]);
                wordList.add(wordArr[i]); //stems each word
                this.processString += wordArr[i] + " "; //adds each stemmed word to processString
            }
        }

        return this.processString;
    }


    /**
     * process function to be used if processString was set with unprocessed value e.g., second constructor was used
     * or set function for string
     */
    public void process()
    {
        this.process(this.processString);
    }

    /**
     * function getStringList returns the list of words that have been processed
     * @return
     */
    public ArrayList<String> getStringList()
    {
        return this.wordList;
    }

    /**
     * prints words currently stored in arrayList wordList separates with |
     */
    public void printWords()
    {
        for(String s : this.wordList)
        {
            System.out.print(s + "|");
        }
        System.out.println();
    }
}
