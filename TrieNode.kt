import java.util.HashMap

class TrieNode {
    val children = HashMap<Char, TrieNode>()
    var isEndOfWord = false
}

class Trie {
    private val root = TrieNode()

    fun insert(word: String) {
        var current = root
        for (ch in word) {
            if (!current.children.containsKey(ch)) {
                current.children[ch] = TrieNode()
            }
            current = current.children[ch]!!
        }
        current.isEndOfWord = true
    }

    fun search(word: String): Boolean {
        var current = root
        for (ch in word) {
            if (!current.children.containsKey(ch)) return false
            current = current.children[ch]!!
        }
        return current.isEndOfWord
    }

    fun startsWith(prefix: String): Boolean {
        var current = root
        for (ch in prefix) {
            if (!current.children.containsKey(ch)) return false
            current = current.children[ch]!!
        }
        return true
    }
}

fun main() {
    val trie = Trie()

    trie.insert("cat")
    trie.insert("car")

    println(trie.search("cat"))      // true
    println(trie.search("cap"))      // false
    println(trie.startsWith("ca"))   // true
}
