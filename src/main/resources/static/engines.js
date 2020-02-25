function f() {
    const volumeInput = document.getElementById("input");
    const createBtn = document.getElementById("create-btn");

    const API_URL = 'http://localhost:8080';

    const enginesTable = document.getElementById("engines_table");
    document.querySelector('.username-container').innerHTML = window.localStorage.getItem('username');

    const setActionOnUpdateButtonClick = () => {
        for (const el of document.getElementsByClassName('update-btn')) {
            el.addEventListener('click', (e) => {
                const id = e.target.getAttribute('data-id');
                let xhr = new XMLHttpRequest();
                xhr.open('GET', `${API_URL}/engine/${id}`);
                xhr.onload = function () {
                    if (xhr.status !== 200) {
                        console.log('error', xhr);
                    } else {
                        const res = JSON.parse(xhr.response);
                        createBtn.setAttribute('data-id', id);
                        volumeInput.value = res.volume;
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
                xhr.open('DELETE', `${API_URL}/engine?id=${id}`);
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

    const getEnginesFromApi = () => {
        let xhr = new XMLHttpRequest();
        xhr.open('GET', `${API_URL}/engine`);
        xhr.onload = function () {
            if (xhr.status !== 200) {
                console.log('error', xhr);
            } else {
                enginesTable.innerHTML = '';
                for (const engine of JSON.parse(xhr.response)) {
                    let row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${engine.id}</td>
                        <td>${engine.volume}</td>
                        <td>
                       <button class="delete-btn" data-id="${engine.id}" >Delete</button>
                        <button class="update-btn" data-id="${engine.id}">Update</button>
                        </td>
                    `;
                    enginesTable.appendChild(row);

                }
                setActionOnDeleteButtonClick();
                setActionOnUpdateButtonClick();
            }
        };
        xhr.send();
    };
    createBtn.addEventListener('click', () => {
        const engine = {
            volume: volumeInput.value,
        };
        const id = createBtn.getAttribute('data-id');
        const method = id ? 'PUT' : 'POST';
        let xhr = new XMLHttpRequest();
        xhr.open(method, `${API_URL}/engine${id ? '?id=' + id : ''}`);
        xhr.setRequestHeader('Authorization', `Bearer ${window.localStorage.getItem('autoToken')}`);
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.onload = function () {
            if (xhr.status !== 200) {
                console.log('error', xhr);
            } else {
                console.log('ok', xhr.response);
                clearAllField();
                getEnginesFromApi();
            }
        };
        xhr.send(JSON.stringify(engine));
    });
    getEnginesFromApi();

    const clearAllField = () => {
        volumeInput.value = '';
        createBtn.setAttribute('data-id', '');
    };
}