package maps

typealias BucketFactory<K, V> = () -> CustomMutableMap<K, V>

abstract class GenericHashMap<K, V>
(private val bucketFactory: BucketFactory<K, V>) : CustomMutableMap<K, V>
