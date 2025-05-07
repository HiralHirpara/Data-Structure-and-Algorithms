package Trie;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PalindromePairs {
    TrieNode root;
    private List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        root = new TrieNode();

        for (int i = 0;i< words.length;i++){
            root.insert(words[i], i);
        }

        for (int i = 0;i< words.length;i++){
            String word1 = words[i];
            String reverse = new StringBuilder(word1).reverse().toString();
            int idx = root.search(reverse);
            if(idx != -1){
                res.add(Arrays.asList(i, idx));
            }
        }
        return res;
    }

    class TrieNode{
        HashMap<Character, TrieNode> children = new HashMap<>();
        boolean isWord = false;
        int index = -1;

        private void insert(String word, int idx){
            TrieNode curr = root;
            for(char c : word.toCharArray()){
                curr.children.putIfAbsent(c, new TrieNode());
                curr = curr.children.get(c);
            }
            curr.isWord = true;
            curr.index = idx;
        }

        private int search(String word){
            TrieNode curr = root;
            for(char c : word.toCharArray()){
                if(!curr.children.containsKey(c)){
                    return -1;
                }
                curr = curr.children.get(c);
            }
            return curr.isWord ? curr.index : -1;
        }
    }
    public static void main(String[] args) {

        PalindromePairs palindromePairs = new PalindromePairs();

        String words[] = {"abcd","dcba","lls","s","sssll"};

        System.out.println("\n:::::::Palindrome Pairs:::::::\n");
        System.out.println("List of words found in board: "+ palindromePairs.palindromePairs(words));

    }
}
