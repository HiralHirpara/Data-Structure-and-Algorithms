package Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordSquares {
    TrieNode root;
    List<List<String>> res;
    private List<List<String>> wordSquares(String[] words) {
        root = new TrieNode();
        root.inset(words);

        res = new ArrayList<>();

        for (String word : words){
            List<String> currRes = new ArrayList<>();
            currRes.add(word);
            backtrack(currRes, word.length());
        }
        return  res;
    }
    private void backtrack(List<String> currRes, int wordLength){
        if(currRes.size() == wordLength){
            res.add(new ArrayList<>(currRes));
            return;
        }
        int row = currRes.size();
        StringBuilder sb = new StringBuilder();
        for(String w : currRes){
            sb.append(w.charAt(row));
        }
        TrieNode curr = root;
        for(char c : sb.toString().toCharArray()){
            if(!curr.children.containsKey(c)){
                return;
            }
            curr = curr.children.get(c);
        }
        for (String w : curr.words){
            currRes.add(w);
            backtrack(currRes, w.length());
            currRes.remove(currRes.size()-1);
        }
    }

    class TrieNode{
        HashMap<Character, TrieNode> children = new HashMap<>();
        List<String> words = new ArrayList<>();

        private void inset(String[] words){

            for(String word : words){
                TrieNode curr = root;
                for (char c : word.toCharArray()){
                    curr.children.putIfAbsent(c, new TrieNode());
                    curr = curr.children.get(c);
                    curr.words.add(word);
                }
            }
        }
    }
    public static void main(String[] args) {

        WordSquares wordSquares = new WordSquares();
        String[] words = {"area","lead","wall","lady","ball"};

        System.out.println("\n:::::::Word Squares:::::::\n");
        System.out.println("List of words found in board: "+ wordSquares.wordSquares(words));

        /*
        T(n) :O(N×L + N^L)
            - Trie Construction: O(N * L)
            - Backtracking: O(N^L)

         S(n) : O(N×L)
            - Trie Space: O(N * L)
            - Backtracking Space: O(L)
            - Result Storage : O(N * L)
         */

    }
}
