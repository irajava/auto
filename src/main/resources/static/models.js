function f() {

    const makeSelect = document.getElementById("make_select");
    const nameInput = document.getElementById("input");
    const createBtn = document.getElementById("create-btn");

    const API_URL = 'http://localhost:8080';

    const modelsTable = document.getElementById("models_table");
    document.querySelector('.username-container').innerHTML = window.localStorage.getItem('username');

    const setActionOnUpdateButtonClick = () => {
        for (const el of document.getElementsByClassName('update-btn')) {
            el.addEventListener('click', (e) => {
                const id = e.target.getAttribute('data-id');
                let xhr = new XMLHttpRequest();
                xhr.open('GET', `${API_URL}/model/${id}`);
                // xhr.setRequestHeader('Authorization', `Bearer ${window.localStorage.getItem('autoToken')}`);
                xhr.onload = function () {
                    if (xhr.status !== 200) {
                        console.log('error', xhr);
                    } else {
                        const res = JSON.parse(xhr.response);
                        createBtn.setAttribute('data-id', id);
                        nameInput.value = res.name;
                        if (res.make) {
                            makeSelect.value = res.make.id;
                        }

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
                xhr.open('DELETE', `${API_URL}/model?id=${id}`);
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

    const getModelsFromApi = () => {
        let xhr = new XMLHttpRequest();
        xhr.open('GET', `${API_URL}/model`);
        xhr.setRequestHeader('Authorization', `Bearer ${window.localStorage.getItem('autoToken')}`);

        xhr.onload = function () {
            if (xhr.status !== 200) {
                console.log('error', xhr);
            } else {
                modelsTable.innerHTML = '';
                for (const model of JSON.parse(xhr.response)) {
                    let row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${model.id}</td>
                        <td>${model.name}</td>
                        <td>${model.makeName}</td>
                        <td>
                       <button class="delete-btn" data-id="${model.id}" >Delete</button>
                        <button class="update-btn" data-id="${model.id}">Update</button>
                        </td>
                    `;
                    modelsTable.appendChild(row);

                }
                setActionOnDeleteButtonClick();
                setActionOnUpdateButtonClick();
            }
        };
        xhr.send();
    };

    let xhr = new XMLHttpRequest();
    xhr.open('GET', `${API_URL}/make`);
    xhr.setRequestHeader('Authorization', `Bearer ${window.localStorage.getItem('autoToken')}`);
    xhr.onload = function () {
        if (xhr.status !== 200) {
            console.log('error', xhr);
        } else {
            console.log('ok', xhr.response);
            makeSelect.innerHTML = '';
            for (const make of JSON.parse(xhr.response)) {
                let option = document.createElement('option');
                option.setAttribute('value', make.id);
                option.innerText = make.name;
                makeSelect.appendChild(option);
            }
        }
    };
    xhr.send();

    createBtn.addEventListener('click', () => {
        const model = {
            makeId: makeSelect.value,
            name: nameInput.value,
        };
        const id = createBtn.getAttribute('data-id');
        const method = id ? 'PUT' : 'POST';
        let xhr = new XMLHttpRequest();
        xhr.open(method, `${API_URL}/model${id ? '?id=' + id : ''}`);
        xhr.setRequestHeader('Authorization', `Bearer ${window.localStorage.getItem('autoToken')}`);
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.onload = function () {
            if (xhr.status !== 200) {
                console.log('error', xhr);
            } else {
                console.log('ok', xhr.response);
                clearAllField();
                getModelsFromApi();
            }
        };
        xhr.send(JSON.stringify(model));
    });
    getModelsFromApi();

    const clearAllField = () => {
        makeSelect.value = '';
        nameInput.value = '';
        createBtn.setAttribute('data-id', '');
    };



}