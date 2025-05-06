package Trie;


import java.util.HashMap;

public class TrieWithHashMap {
    TrieNode root;
    public TrieWithHashMap(){
        root = new TrieNode();
    }
    private void insert(String word){
        TrieNode curr = root;
        for(int i = 0;i<word.length();i++){
            char c = word.charAt(i);
            curr.children.putIfAbsent(c, new TrieNode());
            curr = curr.children.get(c);
        }
        curr.endOfWord = true;
    }
    private boolean search(String word){
        TrieNode curr = root;
        for(char c: word.toCharArray()){
            if(curr.children.get(c) == null){
                return false;
            }
            curr = curr.children.get(c);
        }
        return curr.endOfWord;
    }
    private boolean startsWith(String preffix){
        TrieNode curr = root;
        for(char c : preffix.toCharArray()){
            if(curr.children.get(c) == null){
                return false;
            }
            curr = curr.children.get(c);
        }
        return true;
    }

    public static void main(String[] args) {
        TrieWithHashMap trieWithHashMap = new TrieWithHashMap();


        System.out.println("\n:::::::Implement Trie (Prefix Tree) with HashMap:::::::\n");

        trieWithHashMap.insert("mango");
        System.out.println("Word 'mango' is inserted");
        trieWithHashMap.insert("kiwi");
        System.out.println("Word 'kiwi' is inserted");


        System.out.println("\nWord 'apple' is available : " +trieWithHashMap.search("apple"));
        System.out.println("Word 'kiwi' is available : " +trieWithHashMap.search("kiwi"));
        System.out.println("\nAny Word Starts With  'mang' : "+trieWithHashMap.startsWith("mang"));
        System.out.println("Any Word Starts With  'wi' : "+trieWithHashMap.startsWith("wi"));
    }
    private class TrieNode{
        HashMap<Character, TrieNode> children = new HashMap<>();
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