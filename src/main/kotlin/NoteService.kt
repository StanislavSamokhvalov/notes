data class Note(
    var id: Int = 0,
    val ownerId: Int,
    var title: String,
    var text: String,
    var comments: Int = 0
)

data class Comment(
    var id: Int = 0,
    var noteId: Int,
    val ownerId: Int,
    var message: String,
    val replyTo: Int,
    var delete: Boolean = false
)

interface CrudService<T> {
    fun add(element: T): Int
    fun delete(id: Int): Int
    fun get()
    fun edit(id: Int, title: String, text: String): Int
}

class NotesService : CrudService<Note> {
    var notes: MutableList<Note> = mutableListOf()
    var comments: MutableList<Comment> = mutableListOf()

    override fun add(element: Note): Int {
        element.id = notes.size
        notes.add(element)
        return notes.last().id
    }

    override fun delete(id: Int): Int {
        return try {
            notes.removeAt(id)
            1
        } catch (e: IndexOutOfBoundsException) {
            180
        }
    }

    override fun get() {
        for ((index) in notes.withIndex()) {
            println(notes[index])
        }
    }

    override fun edit(id: Int, title: String, text: String): Int {
        return try {
            notes[id].text = text
            notes[id].title = title
            1
        } catch (e: IndexOutOfBoundsException) {
            180
        }
    }

    fun getById(id: Int): String {
        return try {
            notes[id].toString()
        } catch (e: IndexOutOfBoundsException) {
            "180"
        }
    }

    fun createComment(element: Comment): Int {
        element.id = comments.size
        comments.add(element)
        return comments.last().id
    }


    fun deleteComment(element: Comment): Int {
        if (notes.isNotEmpty())
            element.delete = true
        return 1
    }

    fun editComment(element: Comment, message: String): Int {
        if (notes.isEmpty() && !element.delete)
            comments[element.id].message = message
        return 1
    }

    fun getComments() {
        for ((index) in comments.withIndex()) {
            if (!comments[index].delete)
                println(comments[index])
        }
    }

    fun restoreComments(element: Comment): Int {
        if (notes.isNotEmpty())
            element.delete = false
        return 1
    }
}

