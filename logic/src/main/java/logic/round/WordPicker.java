package logic.round;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordPicker {
    private List<String> words;
    private static final Logger LOGGER = Logger.getLogger( WordPicker.class.getName() );
    private String wordlist = "/wordlist.txt";

    public WordPicker(){
        words = new ArrayList<>();
        loadWordList();
    }

    public String getNewWord()
    {
        int index = ThreadLocalRandom.current().nextInt(words.size());
        return words.get(index);
    }

    public String selectNextWord(String currentWord){
        String nextWord = currentWord;
        while(currentWord.equals(nextWord)){
            int index = ThreadLocalRandom.current().nextInt(words.size());
            nextWord = words.get(index);
        }
        return nextWord;
    }

    private void loadWordList(){
        String fileName = wordlist;
        String line;

        try {
            InputStream in = getClass().getResourceAsStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            while((line = reader.readLine()) != null) {
                words.add(line);
            }

            reader.close();
        }
        catch(IOException ex) {
            LOGGER.log( Level.SEVERE, ex.toString(), ex );
        }
    }

    public List<String> getWords(){
        return words;
    }
}
