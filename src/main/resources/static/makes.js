function f() {
    const nameInput = document.getElementById("input");
    const createBtn = document.getElementById("create-btn");

    const API_URL = 'http://localhost:8080';

    const makesTable = document.getElementById("makes_table");
    document.querySelector('.username-container').innerHTML = window.localStorage.getItem('username');

    const setActionOnUpdateButtonClick = () => {
        for (const el of document.getElementsByClassName('update-btn')) {
            el.addEventListener('click', (e) => {
                const id = e.target.getAttribute('data-id');
                let xhr = new XMLHttpRequest();
                xhr.open('GET', `${API_URL}/make/${id}`);
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
                xhr.open('DELETE', `${API_URL}/make?id=${id}`);
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

    const getMakesFromApi = () => {
        let xhr = new XMLHttpRequest();
        xhr.open('GET', `${API_URL}/make`);
        xhr.onload = function () {
            if (xhr.status !== 200) {
                console.log('error', xhr);
            } else {
                makesTable.innerHTML = '';
                for (const make of JSON.parse(xhr.response)) {
                    let row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${make.id}</td>
                        <td>${make.name}</td>
                        <td>
                       <button class="delete-btn" data-id="${make.id}" >Delete</button>
                        <button class="update-btn" data-id="${make.id}">Update</button>
                        </td>
                    `;
                    makesTable.appendChild(row);

                }
                setActionOnDeleteButtonClick();
                setActionOnUpdateButtonClick();
            }
        };
        xhr.send();
    };
    createBtn.addEventListener('click', () => {
        const make = {
            name: nameInput.value,
        };
        const id = createBtn.getAttribute('data-id');
        const method = id ? 'PUT' : 'POST';
        let xhr = new XMLHttpRequest();
        xhr.open(method, `${API_URL}/make${id ? '?id=' + id : ''}`);
        xhr.setRequestHeader('Authorization', `Bearer ${window.localStorage.getItem('autoToken')}`);
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.onload = function () {
            if (xhr.status !== 200) {
                console.log('error', xhr);
            } else {
                console.log('ok', xhr.response);
                clearAllField();
                getMakesFromApi();
            }
        };
        xhr.send(JSON.stringify(make));
    });
    getMakesFromApi();

    const clearAllField = () => {
        nameInput.value = '';
        createBtn.setAttribute('data-id', '');
    };
}