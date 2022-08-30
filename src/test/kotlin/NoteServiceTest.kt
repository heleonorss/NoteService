import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {

    @Test
    fun addNote() {
        //arrange
        val note = Notes(0, "привет", "Приветствую всех!",124598, 0)

        //act
        val serviceNotes = NoteService()
        val result = serviceNotes.add(note).id != 0

        //assert
        assertEquals(true, result)
    }

    @Test
    fun deleteNote_True() {
        //arrange
        val note1 = Notes(0, "привет", "Приветствую всех!",124598, 0)
        val note2 = Notes(0, "привет", "Это моя первая запись!",124598, 0)

        //act
        val serviceNotes = NoteService()
        serviceNotes.add(note1)
        serviceNotes.add(note2)

        val result = serviceNotes.delete(note2)

        //assert
        assertEquals(true, result)
    }

    @Test
    fun deleteNote_false() {
        //arrange
        val note1 = Notes(0, "привет", "Приветствую всех!",124598, 0)
        val note2 = Notes(0, "привет", "Это моя первая запись!",124598, 0)
        val note3 = Notes(0, "привет", "Я теперь тоже здесь!",124598, 0)

        //act
        val serviceNotes = NoteService()
        serviceNotes.add(note1)
        serviceNotes.add(note2)

        val result = serviceNotes.delete(note3)

        //assert
        assertEquals(false, result)
    }


    @Test(expected = NotFoundException::class)
    fun deleteNote_ShouldThrow() {
        //arrange
        val note1 = Notes(0, "привет", "Приветствую всех!",124598, 0)
        val note2 = Notes(0, "привет", "Это моя первая запись!",124598, 0)
        val note3 = Notes(0, "привет", "Я теперь тоже здесь!",124598, 0, true)

        //act
        val serviceNotes = NoteService()
        serviceNotes.add(note1)
        serviceNotes.add(note2)
        serviceNotes.add(note3)

        val result = serviceNotes.delete(note3)

        //assert
        assertEquals(false, result)
    }

    @Test
    fun deleteNoteComment_false() {
        //arrange
        val note1 = Notes(0, "привет", "Приветствую всех!",124598, 0)
        val note2 = Notes(0, "привет", "Это моя первая запись!",124598, 0)
        val note3 = Notes(0, "привет", "Я теперь тоже здесь!",124598, 0)

        val comment1 = Comments(0, 1, null, "Hello")
        val comment2 = Comments(0, 1, null, "Hello")
        val comment3 = Comments(0, 2, null, "Hello")

        //act
        val serviceNotes = NoteService()
        serviceNotes.add(note1)
        serviceNotes.add(note2)
    //    serviceNotes.add(note3)

        val serviceComm = CommentService()
        serviceComm.add(comment1)
        serviceComm.add(comment2)
        serviceComm.add(comment3)

        val result = serviceNotes.delete(note3, serviceComm)

        //assert
        assertEquals(false, result)
    }

    @Test
    fun deleteNoteComment_true() {
        //arrange
        val note1 = Notes(0, "привет", "Приветствую всех!",124598, 0)
        val note2 = Notes(0, "привет", "Это моя первая запись!",124598, 0)
        val note3 = Notes(0, "привет", "Я теперь тоже здесь!",124598, 0)

        val comment1 = Comments(0, 1, null, "Hello")
        val comment2 = Comments(0, 1, null, "Hello")
        val comment3 = Comments(0, 2, null, "Hello")

        //act
        val serviceNotes = NoteService()
        serviceNotes.add(note1)
        serviceNotes.add(note2)
        serviceNotes.add(note3)

        val serviceComm = CommentService()
        serviceComm.add(comment1)
        serviceComm.add(comment2)
        serviceComm.add(comment3)

        val result = serviceNotes.delete(note2,serviceComm)

        //assert
        assertEquals(true, result)
    }

    @Test
    fun updateNote_false() {
        //arrange
        val note1 = Notes(0, "привет", "Приветствую всех!",124598, 0)
        val note2 = Notes(0, "привет", "Это моя первая запись!",124598, 0)
        val note3 = Notes(3, "привет", "Я теперь тоже здесь!",124598, 0)

        //act
        val serviceNotes = NoteService()
        serviceNotes.add(note1)
        serviceNotes.add(note2)

        val result = serviceNotes.update(note3)

        //assert
        assertEquals(false, result)
    }

    @Test
    fun updateNote_true() {
        //arrange
        val note1 = Notes(0, "привет", "Приветствую всех!",124598, 0)
        val note2 = Notes(0, "привет", "Это моя первая запись!",124598, 0)

        //act
        val serviceNotes = NoteService()
        serviceNotes.add(note1)
        serviceNotes.add(note2)
        val note = Notes(2, "привет", "Привет. Это моя первая запись!",124998, 0)

        val result = serviceNotes.update(note)

        //assert
        assertEquals(true, result)
    }

    @Test
    fun getByIdNote() {
        //arrange
        val note1 = Notes(0, "привет", "Приветствую всех!",124598, 0)
        val note2 = Notes(0, "привет", "Это моя первая запись!",124598, 0)

        //act
        val serviceNotes = NoteService()
        serviceNotes.add(note1)
        serviceNotes.add(note2)

        val result = serviceNotes.getById(2)

        //assert
        assertEquals(note2, result)
    }

    @Test(expected = NotFoundException::class)
    fun getByIdNote_ShouldThrow() {
        //arrange
        val note1 = Notes(0, "привет", "Приветствую всех!",124598, 0)
        val note2 = Notes(0, "привет", "Это моя первая запись!",124598, 0)

        //act
        val serviceNotes = NoteService()
        serviceNotes.add(note1)
        serviceNotes.add(note2)
        serviceNotes.delete(note2)

        val result = serviceNotes.getById(2)

        //assert
        assertEquals(false, result)
    }

    @Test
    fun getByIdNote_null() {
        //arrange
        val note1 = Notes(0, "привет", "Приветствую всех!",124598, 0)
        val note2 = Notes(0, "привет", "Это моя первая запись!",124598, 0)

        //act
        val serviceNotes = NoteService()
        serviceNotes.add(note1)
        serviceNotes.add(note2)

        val result = serviceNotes.getById(3)

        //assert
        assertEquals(null, result)
    }

    @Test
    fun addComment() {
        //arrange
        val note = Notes(0, "привет", "Приветствую всех!",124598, 0)
        val comment = Comments(0, 1, null, "Hello")
        //act
        val serviceNotes = NoteService()
        val serviceComm = CommentService()
        serviceNotes.add(note)
        val result = serviceComm.add(comment, serviceNotes).id != 0

        //assert
        assertEquals(true, result)
    }

    @Test(expected = NotFoundException::class)
    fun restoreComment() {
        //arrange
        val note = Notes(0, "привет", "Приветствую всех!",124598, 0)
        val comment = Comments(0, 1, null, "Hello")
        //act
        val serviceNotes = NoteService()
        val serviceComm = CommentService()
        serviceNotes.add(note)
        serviceComm.add(comment, serviceNotes)
        val result = serviceComm.restore(comment)

        //assert
        assertEquals(false, result)
    }

    @Test
    fun restoreComment_true() {
        //arrange
        val note = Notes(0, "привет", "Приветствую всех!",124598, 0)
        val comment = Comments(0, 1, null, "Hello")
        //act
        val serviceNotes = NoteService()
        val serviceComm = CommentService()
        serviceNotes.add(note)
        serviceComm.add(comment, serviceNotes)
        serviceComm.delete(comment)
        val result = serviceComm.restore(comment)

        //assert
        assertEquals(true, result)
    }

    @Test
    fun getComment() {
        //arrange
        val note = Notes(0, "привет", "Приветствую всех!",124598, 0)
        val comment = Comments(0, 1, null, "Hello")
        //act
        val serviceNotes = NoteService()
        val serviceComm = CommentService()
        serviceNotes.add(note)
        serviceComm.add(comment)
        val result = serviceComm.getComment(1).getById(1)

        //assert
        assertEquals(comment, result)
    }

    @Test
    fun deleteComment() {
        //arrange
        val note = Notes(0, "привет", "Приветствую всех!",124598, 0)
        val comment = Comments(0, 1, null, "Hello")
        //act
        val serviceNotes = NoteService()
        val serviceComm = CommentService()
        serviceNotes.add(note)
        serviceComm.add(comment)
        val result = serviceComm.deleteComments(1)

        //assert
        assertEquals(true, result)
    }

}