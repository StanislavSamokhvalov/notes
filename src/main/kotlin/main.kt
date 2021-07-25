fun main() {
    val note1 = Note(
        ownerId = 1,
        title = "Title",
        text = "Text"
    )

    val note2 = Note(
        id = 1,
        ownerId = 1,
        title = "Title 2",
        text = "Text 2"
    )

    val comment1 = Comment(
        noteId = 1,
        ownerId = 1,
        message = "message",
        replyTo = 1
    )

    val comment2 = Comment(
        noteId = 1,
        ownerId = 1,
        message = "message",
        replyTo = 1
    )

    val noteService = NotesService()

    println(noteService.add(note1))
    println(noteService.add(note2))
    println(noteService.createComment(comment1))
    println(noteService.createComment(comment2))
    println(noteService.deleteComment(comment1))
    println(noteService.getById(1))
    noteService.getComments()
    noteService.get()
    println(noteService.editComment(comment1, "editComment"))
    println(noteService.restoreComments(comment2))
}