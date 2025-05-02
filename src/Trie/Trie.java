package Trie;

class TrieNode{
    TrieNode[] children = new TrieNode[26];
    boolean endOfWord = false;
}
public class Trie {
    TrieNode root;
    public Trie(){
        root = new TrieNode();
    }

    private void insert(String word){
        TrieNode curr = root;
        for(int i = 0;i<word.length();i++){
            int idx = word.charAt(i) - 'a';
            if(curr.children[idx] == null){
                curr.children[idx] = new TrieNode();
            }
            curr = curr.children[idx];
        }
        curr.endOfWord = true;
    }
    private boolean search(String word){
        TrieNode curr = root;
        for(int i = 0;i<word.length();i++){
            int idx = word.charAt(i) - 'a';
            if(curr.children[idx] == null){
                return false;
            }
            curr = curr.children[idx];
        }
        return curr.endOfWord;
    }
    private boolean startsWith(String prefix){
        TrieNode curr = root;
        for(int i = 0;i<prefix.length();i++){
            int idx = prefix.charAt(i) - 'a';
            if(curr.children[idx] == null){
                return false;
            }
            curr = curr.children[idx];
        }
        return  true;
    }
}
