open class ElemId(open var id: Int, open var delete: Boolean)

open class Service<E: ElemId> {
    val listElem = mutableListOf<E>()

    open fun add(elem: E): E {
        var elemLastId = 0
        if (listElem.isNotEmpty()) {
            elemLastId = listElem.last().id
        }
        elem.id = elemLastId + 1
        listElem.add(elem)
        return listElem.last()
    }

    open fun delete(elem: E): Boolean {
        for (elemDel in listElem) {
            if (elemDel.id == elem.id) {
                if (elemDel.delete) {
                    throw NotFoundException("Объект уже удален")
                }
                elemDel.delete = true
                return true
            }
        }
        return false
    }

    open fun update(elem: E): Boolean {
        for ((index,elemUp) in listElem.withIndex()) {
            if (elemUp.id == elem.id) {
                if (elemUp.delete) {
                    throw NotFoundException("Объект удален")
                }
                listElem[index] = elem
                return true
            }
        }
        return false
    }

    open fun getById(id: Int): E? {
        for (elem in listElem) {
            if (elem.id == id) {
                if (elem.delete) {
                    throw NotFoundException("Объект удален")
                }
                return elem
            }
        }
        return null
    }
}


class NoteService: Service<Notes>() {
    fun delete(elem: Notes, comments: CommentService): Boolean {
        for (elemDel in listElem) {
            if (elemDel.id == elem.id) {
                if (elemDel.delete) {
                    throw NotFoundException("Объект уже удален")
                }
                elemDel.delete = true
                comments.deleteComments(elemDel.id)
                return true
            }
        }
        return false
    }
}


class CommentService: Service<Comments>() {
    fun add(elem: Comments, notes: NoteService): Comments {
        var elemLastId = 0
        val note = notes.getById(elem.noteId)
        if (note == null || note.delete) {
            throw NotFoundException("Комментарий не может быть добавлен. Заметка удалена.")
        }
        if (listElem.isNotEmpty()) {
            elemLastId = listElem.last().id
        }
        elem.id = elemLastId + 1
        listElem.add(elem)
        return listElem.last()
    }

    fun restore(elem: Comments): Boolean {
        for (elemDel in listElem) {
            if (elemDel.id == elem.id) {
                if (!elemDel.delete) {
                    throw NotFoundException("Объект не удален")
                }
                elemDel.delete = false
                return true
            }
        }
        return false
    }

    fun getComment(noteId: Int): CommentService {
        val returnComments = CommentService()
        for (elem in listElem) {
            if (elem.noteId == noteId) {
                returnComments.add(elem)
            }
        }
        return returnComments
    }

    fun deleteComments(noteId: Int): Boolean {
        for (elem in listElem) {
            if (elem.noteId == noteId) {
                elem.delete = true
            }
        }
        return true
    }
}
