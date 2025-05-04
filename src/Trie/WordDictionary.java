package Trie;

class WordDictionary {
    TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }

    private void addWord(String word) {
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

    private boolean search(String word) {
        return dfs(word, root);
    }

    private boolean dfs(String word,  TrieNode root){
        TrieNode curr = root;
        for(int i = 0;i<word.length();i++) {
            char c = word.charAt(i);
            if(c == '.'){
                for(TrieNode t : curr.children){
                    if(t != null && dfs(word.substring(i+1),  t)){
                        return true;
                    }
                }
                return  false;
            }else {
                int idx = c - 'a';
                if (curr.children[idx] == null) {
                    return false;
                }
                curr = curr.children[idx];
            }
        }
        return  curr.endOfWord;
    }


    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();

        System.out.println("\n:::::::Design Add and Search Words Data Structure:::::::\n");
        wordDictionary.addWord("bad");
        System.out.println("Word 'bad' is inserted");
        wordDictionary.addWord("dad");
        System.out.println("Word 'dad' is inserted");
        wordDictionary.addWord("mad");
        System.out.println("Word 'mad' is inserted");

        System.out.println("\nWord 'pad' is available : " +wordDictionary.search("pad")); // return False
        System.out.println("Word 'bad' is available : " +wordDictionary.search("bad")); // return True
        System.out.println("Word '.ad' is available : " +wordDictionary.search(".ad")); // return True
        System.out.println("Word 'b..' is available : " +wordDictionary.search("b..")); // return True
    }
    private class TrieNode{
        TrieNode[] children = new TrieNode[26];
        boolean endOfWord = false;
    }
}
