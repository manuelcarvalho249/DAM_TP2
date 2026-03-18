package dam.exer_2

class Cache<K : Any, V : Any> {

    private val storage = mutableMapOf<K, V>()

    fun put(key: K, value: V) {
        storage[key] = value
    }
    fun get(key: K): V? {
        return storage[key]
    }
    fun evict(key: K) {
        storage.remove(key)
    }
    fun size(): Int {
        return storage.size
    }

    // getOrPut: Se existir, retorna. Se não, executa o lambda 'default', guarda o resultado e retorna-o
    fun getOrPut(key: K, default: () -> V): V {
        val existingValue = storage[key]
        if (existingValue != null) {
            return existingValue
        }
        val newValue = default()
        storage[key] = newValue
        return newValue
    }

    // transform: Aplica uma ação ao valor existente. Se a chave não existir, não faz nada e retorna false
    fun transform(key: K, action: (V) -> V): Boolean {
        val existingValue = storage[key]
        if (existingValue != null) {
            storage[key] = action(existingValue)
            return true
        }
        return false
    }

    // snapshot: Retorna uma cópia imutável (Map em vez de MutableMap)
    // O toMap() do Kotlin cria nativamente uma nova instância de leitura.
    fun snapshot(): Map<K, V> {
        return storage.toMap()
    }

}

fun main() {
    //Testar com Cache<String, Int> para frequências de palavras
    println("Word frequency cache")
    val wordCache = Cache<String, Int>()
    wordCache.put("kotlin", 1)
    wordCache.put("scala", 1)
    wordCache.put("haskell", 1)

    println("Size: ${wordCache.size()}")
    println("Frequency of \"kotlin\": ${wordCache.get("kotlin")}")
    println("getOrPut \"kotlin\": ${wordCache.getOrPut("kotlin") { 0 }}")
    println("getOrPut \"java\": ${wordCache.getOrPut("java") { 0 }}")
    println("Size after getOrPut: ${wordCache.size()}")
    println("Transform \"kotlin\" (+1): ${wordCache.transform("kotlin") { it + 1 }}")
    println("Transform \"cobol\" (+1): ${wordCache.transform("cobol") { it + 1 }}")
    println("Snapshot: ${wordCache.snapshot()}") // [cite: 120, 121, 122, 123, 124, 125, 126, 127]

    println("\nId registry cache")
    // 5. Testar com Cache<Int, String> para um registo de ids[cite: 118].
    val idCache = Cache<Int, String>()
    idCache.put(1, "Alice")
    idCache.put(2, "Bob")

    println("Id 1 ${idCache.get(1)}")
    println("Id 2 ${idCache.get(2)}")
    idCache.evict(1)
    println("After evict id 1, size: ${idCache.size()}")
    println("Id 1 after evict -> ${idCache.get(1)}") // [cite: 128, 129, 130, 131, 132]
}