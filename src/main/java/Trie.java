public class Trie {
    public class TrieNode{
        TrieNode children[];
        boolean isEnd = false;

        TrieNode(){
            children = new TrieNode[26];
            isEnd = false;
        }
    }

    private TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    public void insert(String word){
        TrieNode node = root;

        for(char ch : word.toCharArray()){
            int index = ch - 'a';
            if(node.children[index] == null){
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()){
            if(node.children[ch-'a'] == null) {
                return false;
            }
            else {
                node = node.children[ch-'a'];
            }
        }

        return node.isEnd;
    }


}
