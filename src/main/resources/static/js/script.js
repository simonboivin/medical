const deleteModal = document.getElementById('deleteModal')
deleteModal.addEventListener('show.bs.modal', function (event) {

    let button = event.relatedTarget

    let recipient = button.getAttribute('dataid')

    let modalMesssage = deleteModal.querySelector('.modal-message')

    modalMesssage.textContent = 'Voulez-vous supprimer le patient ' + recipient + '?'
    let modalForm = deleteModal.querySelector('.modal-form')
    let modalFormLink = '/patients/delete/' + recipient
    modalForm.setAttribute("action", modalFormLink)
})