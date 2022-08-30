data class Notes(
    override var id: Int,
    val title: String,
    val text: String,
    val date: Int,
    val comment: Int,
    override var delete: Boolean = false
): ElemId(id, delete)

data class Comments(
    override var id: Int,
    val noteId: Int,
    val replyTo: Int?,
    val messege: String,
    override var delete: Boolean = false
): ElemId(id, delete)
