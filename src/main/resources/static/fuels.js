function f() {
    const typeInput = document.getElementById("input");
    const createBtn = document.getElementById("create-btn");

    const API_URL = 'http://localhost:8080';

    const fuelsTable = document.getElementById("fuels_table");
    document.querySelector('.username-container').innerHTML = window.localStorage.getItem('username');

    const setActionOnUpdateButtonClick = () => {
        for (const el of document.getElementsByClassName('update-btn')) {
            el.addEventListener('click', (e) => {
                const id = e.target.getAttribute('data-id');
                let xhr = new XMLHttpRequest();
                xhr.open('GET', `${API_URL}/fuel/${id}`);
                xhr.onload = function () {
                    if (xhr.status !== 200) {
                        console.log('error', xhr);
                    } else {
                        const res = JSON.parse(xhr.response);
                        createBtn.setAttribute('data-id', id);
                        typeInput.value = res.type;
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
                xhr.open('DELETE', `${API_URL}/fuel?id=${id}`);
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

    const getFuelsFromApi = () => {
        let xhr = new XMLHttpRequest();
        xhr.open('GET', `${API_URL}/fuel`);
        xhr.onload = function () {
            if (xhr.status !== 200) {
                console.log('error', xhr);
            } else {
                fuelsTable.innerHTML = '';
                for (const fuel of JSON.parse(xhr.response)) {
                    let row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${fuel.id}</td>
                        <td>${fuel.type}</td>
                        <td>
                       <button class="delete-btn" data-id="${fuel.id}" >Delete</button>
                        <button class="update-btn" data-id="${fuel.id}">Update</button>
                        </td>
                    `;
                    fuelsTable.appendChild(row);

                }
                setActionOnDeleteButtonClick();
                setActionOnUpdateButtonClick();
            }
        };
        xhr.send();
    };
    createBtn.addEventListener('click', () => {
        const fuel = {
            type: typeInput.value,
        };
        const id = createBtn.getAttribute('data-id');
        const method = id ? 'PUT' : 'POST';
        let xhr = new XMLHttpRequest();
        xhr.open(method, `${API_URL}/fuel${id ? '?id=' + id : ''}`);
        xhr.setRequestHeader('Authorization', `Bearer ${window.localStorage.getItem('autoToken')}`);
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.onload = function () {
            if (xhr.status !== 200) {
                console.log('error', xhr);
            } else {
                console.log('ok', xhr.response);
                clearAllField();
                getFuelsFromApi();
            }
        };
        xhr.send(JSON.stringify(fuel));
    });
    getFuelsFromApi();

    const clearAllField = () => {
        typeInput.value = '';
        createBtn.setAttribute('data-id', '');
    };
}