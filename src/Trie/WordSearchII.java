package Trie;

import java.util.*;

public class WordSearchII {
    Set<String> result;
    TrieNode root;
    boolean[][] visit;
    int m;
    int n;
    private List<String> findWords(char[][] board, String[] words) {
        result = new HashSet<>();
        root = new TrieNode();
        m = board.length;
        n = board[0].length;
        visit = new boolean[m][n];

        for (String word: words){
            root.insert(word);
        }
        for(int i = 0;i<m;i++){
            for (int j = 0;j<n;j++){
                if(root.children.containsKey(board[i][j])) {
                    dfs(board, i, j, new StringBuilder(), root);
                }
            }
        }
        return new ArrayList<>(result);
    }
    private void dfs(char[][] board, int r, int c, StringBuilder str, TrieNode curr){
        if(r < 0 || c < 0 || r >= m || c >= n || visit[r][c] || !curr.children.containsKey(board[r][c])){
            return;
        }
        visit[r][c] = true;
        curr = curr.children.get(board[r][c]);
       str.append(board[r][c]);
        if(curr.isWord){
            result.add(str.toString());
        }
        dfs(board, r+1, c, str, curr);
        dfs(board, r-1, c, str, curr);
        dfs(board, r, c+1, str, curr);
        dfs(board, r, c-1, str, curr);
        visit[r][c] = false;
    }

    public static void main(String[] args) {
        WordSearchII wordSearchII = new WordSearchII();

        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain"};

        System.out.println("\n:::::::Word Search II:::::::\n");
        System.out.println("List of words found in board: "+ wordSearchII.findWords(board, words));
        /*
        T(n): O(W * L_max) + O(m * n * L_max)
            - O(W * L_max): Time to insert all words into the Trie.
            - O(m * n * L_max): Time for the DFS search through the board.

        S(n) : O(W * L_max + m * n)

        Where:
            - W = number of words
            - L_max = length of the longest word in words
            - m = number of rows in the board
            - n = number of columns in the board
         */
    }

    class TrieNode{
        HashMap<Character, TrieNode> children = new HashMap<>();
        boolean isWord= false;

        private void insert(String word){
            TrieNode curr = root;
            for(char c: word.toCharArray()){
                curr.children.putIfAbsent(c, new TrieNode());
                curr = curr.children.get(c);
            }
            curr.isWord = true;
        }
    }


}
