function F() {
    const typeInput = document.getElementById("input");
    const createBtn = document.getElementById("create-btn");
    const API_URL = 'http://localhost:8080';
    const conditioningsTable = document.getElementById("conditionings_table");
    document.querySelector('.username-container').innerHTML = window.localStorage.getItem('username');

    const setActionOnUpdateButtonClick = () => {
        for (const el of document.getElementsByClassName('update-btn')) {
            el.addEventListener('click', (e) => {
                const id = e.target.getAttribute('data-id');
                let xhr = new XMLHttpRequest();
                xhr.open('GET', `${API_URL}/conditioning/${id}`);
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
                xhr.open('DELETE', `${API_URL}/conditioning?id=${id}`);
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

    const getConditioningsFromApi = () => {
        let xhr = new XMLHttpRequest();
        xhr.open('GET', `${API_URL}/conditioning`);
        xhr.onload = function () {
            if (xhr.status !== 200) {
                console.log('error', xhr);
            } else {
                conditioningsTable.innerHTML = '';
                for (const conditioning of JSON.parse(xhr.response)) {
                    let row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${conditioning.id}</td>
                        <td>${conditioning.type}</td>
                        <td>
                       <button class="delete-btn" data-id="${conditioning.id}" >Delete</button>
                        <button class="update-btn" data-id="${conditioning.id}">Update</button>
                        </td>
                    `;
                    conditioningsTable.appendChild(row);

                }
                setActionOnDeleteButtonClick();
                setActionOnUpdateButtonClick();
            }
        };
        xhr.send();
    };
    createBtn.addEventListener('click', () => {
        const conditioning = {
            type: typeInput.value,
        };
        const id = createBtn.getAttribute('data-id');
        const method = id ? 'PUT' : 'POST';
        let xhr = new XMLHttpRequest();
        xhr.open(method, `${API_URL}/conditioning${id ? '?id=' + id : ''}`);
        xhr.setRequestHeader('Authorization', `Bearer ${window.localStorage.getItem('autoToken')}`);
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.onload = function () {
            if (xhr.status !== 200) {
                console.log('error', xhr);
            } else {
                console.log('ok', xhr.response);
                clearAllField();
                getConditioningsFromApi();
            }
        };
        xhr.send(JSON.stringify(conditioning));
    });
    getConditioningsFromApi();

    const clearAllField = () => {
        typeInput.value = '';
        createBtn.setAttribute('data-id', '');
    };
}