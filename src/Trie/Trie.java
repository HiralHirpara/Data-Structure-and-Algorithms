package Trie;


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

    public static void main(String[] args) {
        Trie obj = new Trie();
        System.out.println("\n:::::::Implement Trie (Prefix Tree) with Array:::::::\n");
        obj.insert("apple");
        System.out.println("Word 'apple' is inserted");
        obj.insert("banana");
        System.out.println("\nWord 'banana' is inserted");
        System.out.println("Word 'apple' is available : " +obj.search("apple"));
        System.out.println("Word 'appl' is available : " +obj.search("appl"));
        System.out.println("\nAny Word Starts With 'app' : "+obj.startsWith("app"));
        System.out.println("Any Word Starts With 'nana' : "+obj.startsWith("nana"));
    }

    private class TrieNode{
        TrieNode[] children = new TrieNode[26];
        boolean endOfWord = false;
    }
}
/*
    Insert  T(n) : O(L), S(n) : O(t)
    Search  T(n) : O(L)
    StartsWith  T(n) : O(L)
    L is Length of word
    t is TrieNode created
 */
