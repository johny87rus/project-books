const save = document.querySelector('#deleteBtn');
save.addEventListener('click', evt => {
    const request = new XMLHttpRequest();
    const id = document.querySelector('#floatingId');
    request.open('DELETE', 'books/' + id.value);
    request.send();
    id.value = '';
    location.href = 'index.html';
});