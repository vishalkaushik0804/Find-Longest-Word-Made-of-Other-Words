// Write a Program to Find the Longest Word Made of Other Words
package longestwordmadeofotherwords;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author Vishal Kaushik
 */

class Result {

    /*
     * Complete the 'getRequiredWord' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING_ARRAY wordList as parameter.
     */
    public static String getRequiredWord(List<String> wordList) {
        // Write your code here
        if (wordList == null || wordList.isEmpty()) {
            return null;
        }
        HashSet<String> map = new HashSet<>();
        for (String word : wordList) {
            map.add(word);
        }
        Collections.sort(wordList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return ((Integer) o2.length()).compareTo(o1.length());
            }
        });
        for (String word : wordList) {
            if (isBreakable(word, map)) {
                return word;
            }
        }
        return "";
    }

    private static boolean isBreakable(String word, HashSet<String> set) {
        boolean[] res = new boolean[word.length() + 1];
        res[0] = true;
        for (int i = 0; i < word.length(); i++) {
            StringBuilder str = new StringBuilder(word.substring(0, i + 1));
            for (int j = 0; j <= i; j++) {
                if (res[j] && set.contains(str.toString())) {
                    res[i + 1] = true;
                    break;
                }
                str.deleteCharAt(0);
            }
        }
        return res[word.length()];

    }
}

public class LongestWordMadeOfOtherWords {
   public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int wordListCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> wordList = IntStream.range(0, wordListCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .collect(toList());

        String result = Result.getRequiredWord(wordList);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
