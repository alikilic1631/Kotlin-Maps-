package maps

import java.lang.UnsupportedOperationException

class ListBasedMap<K, V> : CustomMutableMap<K, V> {

    override var entries = CustomLinkedList<Entry<K, V>>(RootNode(null))

    // Provides read access to all keys of the map
    override var keys: Iterable<K> = entries.map { it.key }

    // Provides read access to all values of the map
    override var values: Iterable<V> = entries.map { it.value }

    override fun get(key: K): V? {
        if (this.entries.isEmpty()) {
            throw UnsupportedOperationException()
        } else {
            return this.entries.first { it.key == key }.value
        }
    }

    override fun set(key: K, value: V): V? {
        return if (this.entries.isEmpty() || key !in keys) {
            this.entries.add(Entry(key, value))
            this.values.plus(value)
            this.keys.plus(value)

            null
        } else {
            val old = this[key]
            this.entries.first { it.key == key }.value = value
            this.values.filter { it == old }.plus(value)
            old
        }
    }

    override fun put(key: K, value: V): V? {
        return this.set(key, value)
    }

    override fun put(entry: Entry<K, V>): V? {
        return this.set(entry.key, entry.value)
    }

    override fun contains(key: K): Boolean = key in keys

    override fun remove(key: K): V? {
        val removed = this[key]
        this.entries.filter{it.key == key }
        return removed
    }
}
