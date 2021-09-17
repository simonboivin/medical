var exampleModal = document.getElementById('exampleModal')
exampleModal.addEventListener('show.bs.modal', function (event) {

    var button = event.relatedTarget

    var recipient = button.getAttribute('dataid')

    var modalMesssage = exampleModal.querySelector('.modal-message')

    modalMesssage.textContent = 'Voulez-vous supprimer le patient ' + recipient + '?'
    var modalForm = exampleModal.querySelector('.modal-form')
    var modalFormLink = '/patients/delete/' + recipient
    modalForm.setAttribute("action", modalFormLink)
})