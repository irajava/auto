function F() {
    const nameInput = document.getElementById("input");
    const createBtn = document.getElementById("create-btn");

    const API_URL = 'http://localhost:8080';

    const coloursTable = document.getElementById("colours_table");
    document.querySelector('.username-container').innerHTML = window.localStorage.getItem('username');

    const setActionOnUpdateButtonClick = () => {
        for (const el of document.getElementsByClassName('update-btn')) {
            el.addEventListener('click', (e) => {
                const id = e.target.getAttribute('data-id');
                let xhr = new XMLHttpRequest();
                xhr.open('GET', `${API_URL}/colour/${id}`);
                xhr.setRequestHeader('Authorization', `Bearer ${window.localStorage.getItem('autoToken')}`);
                xhr.onload = function () {
                    if (xhr.status !== 200) {
                        console.log('error', xhr);
                    } else {
                        const res = JSON.parse(xhr.response);
                        createBtn.setAttribute('data-id', id);
                        nameInput.value = res.name;
                    }
                };
                xhr.send();
            });
        }
    };

    const setActionOnDeleteButtonClick = () => {
        for (const el of document.getElementsByClassName('delete-btn')) {
            el.addEventListener('click', (e) => {
                const id = e.target.getAttribute('data-id');

                let xhr = new XMLHttpRequest();
                xhr.open('DELETE', `${API_URL}/colour?id=${id}`);
                xhr.setRequestHeader('Authorization', `Bearer ${window.localStorage.getItem('autoToken')}`);
                xhr.onload = function () {
                    if (xhr.status !== 200) {
                        console.log('error', xhr);
                    } else {
                        e.target.parentElement.parentElement.outerHTML = '';
                    }
                };
                xhr.send();
            });
        }
    };

    const getColoursFromApi = () => {
        let xhr = new XMLHttpRequest();
        xhr.open('GET', `${API_URL}/colour`);
        xhr.onload = function() {
            if (xhr.status !== 200) {
                console.log('error', xhr);
            } else {
                coloursTable.innerHTML = '';
                for (const colour of JSON.parse(xhr.response)) {
                    let row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${colour.id}</td>
                        <td>${colour.name}</td>
                        <td>
                       <button class="delete-btn" data-id="${colour.id}" >Delete</button>
                        <button class="update-btn" data-id="${colour.id}">Update</button>
                        </td>
                    `;
                    coloursTable.appendChild(row);

                }
                setActionOnDeleteButtonClick();
                setActionOnUpdateButtonClick();
            }
        };
        xhr.send();
    };
    createBtn.addEventListener('click', () => {
        const colour = {
            name: nameInput.value,
        };
        const id = createBtn.getAttribute('data-id');
        const  method= id ? 'PUT' : 'POST';
        let xhr = new XMLHttpRequest();
        xhr.open(method, `${API_URL}/colour${id ? '?id='+id : ''}`);
        xhr.setRequestHeader('Authorization', `Bearer ${window.localStorage.getItem('autoToken')}`);
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.onload = function() {
            if (xhr.status !== 200) {
                console.log('error', xhr);
            } else {
                console.log('ok', xhr.response);
                clearAllField();
                getColoursFromApi();
            }
        };
        xhr.send(JSON.stringify(colour));
    });
    getColoursFromApi();

    const clearAllField = () => {
        nameInput.value = '';
        createBtn.setAttribute('data-id', '');
    };
}