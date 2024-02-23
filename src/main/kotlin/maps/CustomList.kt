package maps

import java.lang.IllegalStateException
import java.lang.UnsupportedOperationException
import java.util.NoSuchElementException

interface Node<T> {
    var next: ValNode<T>?
}

data class ValNode<T>(var value: T, override var next: ValNode<T>? = null) :
    Node<T>

data class RootNode<T>(override var next: ValNode<T>? = null) : Node<T>

class CustomLinkedList<T>(val root: RootNode<T>) : MutableIterable<T> {
    var size = 0

    fun isEmpty(): Boolean = this.size == 0

    fun head() = this.root.next

    fun add(newElem: T) {
        this.root.next = ValNode(newElem, this.root.next)
        this.size++
    }

    fun remove(): T {
        if (this.isEmpty()) {
            throw UnsupportedOperationException()
        } else {
            val removedNode = this.root.next!!
            this.root.next = removedNode.next

            return removedNode.value
        }
    }

    override fun iterator(): MutableIterator<T> {
        return object : MutableIterator<T> {
            var current: Node<T>? = root
            var lastReturn: Node<T>? = null

            override fun hasNext(): Boolean = current?.next != null

            override fun next(): T {
                if (!hasNext()) {
                    throw NoSuchElementException()
                } else {
                    lastReturn = current?.next
                    current = current?.next
                    return (current as ValNode<T>?)?.value!!
                }
            }

            override fun remove() {
                if (lastReturn == null || lastReturn == root) {
                    throw UnsupportedOperationException()
                } else {
                    var tempNode: Node<T> = root
                    while (tempNode.next != lastReturn) {
                        tempNode =
                            tempNode.next ?: throw IllegalStateException()
                    }
                    tempNode.next = lastReturn!!.next
                    lastReturn
                }
            }
        }
    }
}
